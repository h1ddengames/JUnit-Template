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
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCmNV9dIIgnBGG7px0c7V9b0zs14UazrqaNYOaDPDMa6iqMfX/FSKyhvY2lU7DTjUxyHodMt0raQjKoa0Et9C14KxEiEnTwb7cXHNXrIKNckaq19gmVwFcnQLTzdqrXxYJDkcFv8em9X6pP+UcwJ14rrgRQCUZ9ZtMTOBmR0zEwgZebVjDc3gJIPcNP5SVTcH39ZgO8vDMP/izvb7At04mKJMm2BjMXkF97aUP/AUqnc0fb75nBb7pkFsEY2v0Xa5U9NGULD6D9xNLKE/U0tV+ZLihPz9pev8T/22zcE54D+F/kH3wN0+6AJgemO8frfFqNajVBUS0I2OwU5sJLy/djMqZqz5c+zuSp1JEoeNYDncCTceHP9fFPOvj8j/JEqRKe/2SmJdy5PeVzZBmU0dPiTAOsQopaorKP6R9/SUAw8EeEkgh0aBARPJE6yXAiQO/T7NcWywSoF7Je4RG0XsCjUpr3po94kUgsGVZEVQO2xSEgf5QS2Q1CgRPfZM2PQD8= hiddengames@DESKTOP-NS5O6HM"
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

  # Copies the docker directory from .docker to the new EC2 instance.
  provisioner "file" {
    source      = "../.docker"
    destination = "/home/ubuntu/docker"

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/ssh-key")
      host        = self.public_dns
    }
  }

  # Copies the website directory from .website to the new EC2 instance.
  provisioner "file" {
    source      = "../.website"
    destination = "/home/ubuntu/website"

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/ssh-key")
      host        = self.public_dns
    }
  }

  # Copies the tools directory from .tools to the new EC2 instance.
  provisioner "file" {
    source      = "../.tools"
    destination = "/home/ubuntu/tools"

    connection {
      type        = "ssh"
      user        = "ubuntu"
      private_key = file("~/.ssh/ssh-key")
      host        = self.public_dns
    }
  }
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
    description = "SSH from VPC"
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
