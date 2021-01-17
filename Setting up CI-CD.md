# Setting up a CI-CD Pipeline

Return to main Readme [here](README.md)

## Installations

1. [Install AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html)
2. Verify that AWS CLI has been installed: `aws --version`

3. [Install Terraform](https://www.terraform.io/downloads.html)
4. On Windows: Add the Terraform executable to PATH.
5. Verify that Terraform has been installed: `terraform.exe -version`

6. [Install Docker](https://hub.docker.com/?ref=login&overlay=onboarding)
7. On Windows: enable Hyper-V, Windows Subsystem for Linux, and Containers under Windows Features.
8. Verify that Docker has been installed: `docker --version`

## AWS

1. Create an AWS account.
2. Please add two-factor authentication to the AWS account.
3. Visit the [IAM Management Console](https://console.aws.amazon.com/iam/home?#security_credential) to get your AWS Access Key ID and Secret Access Key.
4. Log into AWS Console and make sure that you have a default VPC, Subnet, and Security Group.
5. Run `aws configure` to store your AWS Access Key ID and Secret Access Key.

## Terraform

If there are any issues in the next steps use the [following](https://learn.hashicorp.com/tutorials/terraform/aws-build#troubleshooting)

1. Create a directory for AWS Terraform configuration and change into the created directory: `mkdir aws-instance && cd $_`
2. Create a Terraform configuration: `touch aws.tf`
3. Create an SSH key to login into the EC2 instance that will be created: `ssh-keygen` using the name: `ssh-key`
4. Update the below configuration file with the contents of ssh-key.pub file under `resource "aws_key_pair" "ssh-key" { key_name = "ssh-key" public_key = "theValueOfYourPubKeyHere"}`
5. Create a script named init-script.sh and put the following in the file:

   ```bash
    #!/bin/bash

    sudo apt-get update

    # Install Docker dependencies.
    sudo apt-get -y install \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common

    # Add Docker’s official GPG key.
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

    # Set up the stable repository.
    sudo add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) \
    stable"

    # Install the latest version of Docker.
    sudo apt-get update &&
        sudo apt-get -y install docker-ce docker-ce-cli containerd.io
   ```

6. Add the following to the configuration file:

   ```terraform
    # Use AWS module.
    terraform {
        required_providers {
            aws = {
            source  = "hashicorp/aws"
            version = "3.23.0"
            }
        }
    }

    # In us-west-1 region.
    provider "aws" {
        profile = "default"
        region  = "us-west-1"
    }

    # With public key to SSH into the instance.
    # Connect to the instance with: ssh -i ssh-key ubuntu@public_ip_address_here
    resource "aws_key_pair" "ssh-key" {
        key_name   = "ssh-key"
        public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDqIFNG2vUc91G/feuPIFwuxg09aO170ztgJKA+E3jdtDFk6I26QzIQ31kzr5TBowaH2989jLaGV41A7Tx85kfX/dvOXoExQU7eQy6IYFXuD+rErsDMhDAdfLUqoI2YB59owypO+zItk/jl7bLcu3t0C2Fts+NeKlyXVc0/R9oFfNxJSDKeTsiidDLxiZ5qz4aKeUryrM0XXZQnnWPTcBhBfqyPcjY9uUXRinwWwdhxFquh+BdsQSeRFjHUFrGbGOoDWT+poYiaD5uhwIdr7VJFNrj56ZgpbMX4BUSUQETdVTz6U1hjsljguiseLeuGOufDRV6mQjd0huVqDL8D/eKnDIwz6PIv7pap2+FUcZdFtldN5ZadE5wIOCs+H79uel501uGyVFN6Wqfe4jH8nOVfV9OLbP12GV6fkHJUUIf44XtwN9EDn6jhXxMdO/ztOGcHQxthTKbulnzKf/pWJOb6vphblLhDd3zk8b2u+vK0ERrHPq8G0MuFvFXALw3F3lE= hiddengames@DESKTOP-NS5O6HM"
    }

    # Copies the Dockerfile in this directory to the new EC2 instance.
    provisioner "file" {
        source      = "../.docker/Dockerfile"
        destination = "/tmp/Dockerfile"
    }

    # With Ubuntu 20.04 AMI and a t2.micro (1 vCPU + 1 GB RAM) server.
    resource "aws_instance" "CI-CD" {
        ami                    = "ami-00831fc7c1e3ddc60"
        instance_type          = "t2.micro"
        user_data              = file("init-script.sh")
        vpc_security_group_ids = [aws_default_security_group.default-security-group.id]

        tags = {
            Name = "CI-CD"
        }

        key_name = "ssh-key"
    }

    # Use the default vpc defined by AWS.
    resource "aws_default_vpc" "default-vpc" {
        tags = {
            Name = "Default VPC"
        }
    }

    # Use the default subnet defined by AWS.
    resource "aws_default_subnet" "default-subnet" {
        availability_zone = "us-west-1b"

        tags = {
            Name = "Default subnet for us-west-1b"
        }
    }

    # Use the default security group defined by AWS
    # but modify it so that it accepts incoming connections
    # from ports 22, 80, 443, and 8080
    resource "aws_default_security_group" "default-security-group" {
        vpc_id = aws_default_vpc.default-vpc.id

        ingress {
            description = "HTTPS from VPC"
            from_port   = 22
            to_port     = 22
            protocol    = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }

        ingress {
            description = "HTTP from VPC"
            from_port   = 80
            to_port     = 80
            protocol    = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }

        ingress {
            description = "HTTPS from VPC"
            from_port   = 443
            to_port     = 443
            protocol    = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }

        ingress {
            description = "Jenkins"
            from_port   = 8080
            to_port     = 8080
            protocol    = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }

        egress {
            from_port   = 0
            to_port     = 0
            protocol    = "-1"
            cidr_blocks = ["0.0.0.0/0"]
        }

        tags = {
            Name = "security-group"
        }
    }

    # Display public IP in the console after successfully creating the EC2 instance.
    output "instance_ip" {
        description = "The public IP for SSH access"
        value       = aws_instance.CI-CD.public_ip
    }
   ```

7. Run `terraform init && terraform fmt && terraform validate` to:
    1. Initialize the directory with the above configuration.
    2. Check the configuration's formatting.
    3. Check that the syntax is valid.
8. Run `terraform apply` to create the EC2 instance on AWS.(This can take at least 30 seconds as it waits for the EC2 instance to start.)
9. Run `terraform show` to verify the configuration details of the EC2 instance that was created.

## Docker

1. Create a file called `Dockerfile` in the same directory as the Terraform files.
2. Add the following lines to the Dockerfile:

   ```Dockerfile
    # Using the latest version of Ubuntu.
    FROM ubuntu:20.04
    LABEL \
    Maintainer="Shahid Karim <skarim3000@gmail.com>" \
    Description="Apache, PHP, Java, Maven, and Jenkins running on Ubuntu" \
    BuildCommand="docker build -t h1ddengames/server -f Docker/Dockerfile . --no-cache" \
    RunCommand="docker run --name server -dit -p 80:80 -p 443:443 -p 8080:8080 h1ddengames/server" \
    ExecCommand="docker exec -it server /bin/bash" \
    BuildTime="382 seconds ~ 6.3 minutes" \
    BuildSize="940.44 MB" \
    Version="1.0.0"

    # Start in /tmp folder.
    WORKDIR /tmp

    # Allow connections to port 80, 443, and 8080.
    # For HTTP, HTTPS, and Jenkins respectively.
    EXPOSE 80
    EXPOSE 443
    EXPOSE 8080

    # Setup Apache, PHP, Java 11, Jenkins, and Maven.
    # 1. Install required software dependencies + Apache, PHP.
    # 2. Add repository for Jenkins and AdoptOpenJDK.
    # 3. Clean cache and update cache. -> Required before running apt-get install and after adding repo.
    # 4. Install Java 11, Maven, and Jenkins. -> Required: openjdk-11-jre-headless otherwise Jenkins runs into AWT headless error.
    # 5. Create directories to serve javadocs, reports, and jar.
    RUN \
    export DEBIAN_FRONTEND=noninteractive && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get update && \
    apt-get -y --no-install-recommends install gnupg2 software-properties-common wget curl zip gzip tar apache2 php libapache2-mod-php

    RUN \
    wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | apt-key add - && \
    sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > \
            /etc/apt/sources.list.d/jenkins.list' && \
    wget -q -O - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - && \
    add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get update && \
    apt-get -y install adoptopenjdk-11-hotspot openjdk-11-jre-headless git maven && \
    apt-get -y install jenkins && \
    mkdir /var/www/html/javadocs /var/www/html/reports /var/www/html/jar && \
    wget https://github.com/allure-framework/allure2/releases/download/2.13.8/allure_2.13.8-1_all.deb && \
    dpkg -i allure_2.13.8-1_all.deb

    # Apache and Jenkins autostart on container start.
    # Call to tail -f /dev/null in order to keep the container running.
    CMD service apache2 start && service jenkins start && tail -f /dev/null
   ```

3. Build the image.
4. Create a container based off the image.
5. Exec into the container and get the Jenkins admin password ```cat /var/lib/jenkins/secrets/initialAdminPassword```
6. Install the default plugins.
7. Create first admin user.
8. Set ownership of allure executable to the admin user: ```chown username /bin/allure```
9. Add admin user to www-data group ```usermod -aG www-data username```
10. Create a new Item on the Jenkins dashboard. Note: following steps can be converted to a Jenkinsfile.
11. Add Github Project URL.
12. Set Source Code Management to Git and add the repository URL.
13. Set Branches to build to blank to track all branches.
14. Turn on build triggers ``Github hook trigger for GITScm polling`` and ``Poll SCM``
15. Turn on ``Delete workspace before build starts`` and ``Add timestamps to the Console Output``
16. Add Build > Execute shell script:
    ```
    mvn -B clean test package --file pom.xml && \
    zip -r docs/javadocs.zip docs/javadocs && \
    zip -r docs/allure-reports.zip docs/allure-reports
    ```
17. Add Post-build Actions > Archive the artifacts:
    ```
    target/Project-Template-*.jar, docs/javadocs.zip, docs/allure-reports.zip, docs/index.html, docs/styles.css
    ```

## Useful snippets for Docker

1. Running container:

   ```bash
   # Using containers on Docker Hub
   sudo docker run --name [easy to type name] -dit -p 80:80 -p 443:443 -p 8080:8080 ubuntu:20.04

   sudo docker run --name ubuntuServer -dit -v $(pwd):/tmp -p 80:80 -p 443:443 -p 8080:8080 ubuntu:20.04

   # Using containers built from Dockerfile
   sudo docker build -t hiddengames/server -f Dockerfile .

   sudo docker run --name ubuntuServer -dit -v $(pwd):/var/www/html -p 80:80 -p 443:443 -p 8080:8080 hiddengames/server
   ```

2. List running containers:

   ```bash
   sudo docker ps -a
   ```

3. Run command on running container:

   ```bash
   sudo docker exec -it [container-id OR container-name] [command]

   sudo docker exec -it ubuntuServer /bin/bash
   ```

4. Stopping container:

   ```bash
   sudo docker stop [container-id OR container-name]

   sudo docker stop ubuntuServer
   sudo docker stop a604
   ```

5. Delete all stopped containers:
   ```bash
   sudo docker rm $(sudo docker ps -a -q)
   ```
