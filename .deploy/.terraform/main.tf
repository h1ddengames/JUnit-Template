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

# Copies the Dockerfile in the .docker directory to the new EC2 instance.
provisioner "file" {
  source      = "../.docker/Dockerfile"
  destination = "/tmp/Dockerfile"
}

# Copies the website directory from .website to the new EC2 instance.
provisioner "file" {
  source      = "../.website"
  destination = "/tmp/website"
}

# Copies the tools directory from .tools to the new EC2 instance.
provisioner "file" {
  source      = "../.tools"
  destination = "/tmp/tools"
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
