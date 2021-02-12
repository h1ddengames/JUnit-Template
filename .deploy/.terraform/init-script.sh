#!/bin/bash

# Update the package list and upgrade any outdated packages
sudo apt-get update
sudo apt-get -y upgrade

# Create directories for Docker volume mounts.
mkdir -p /home/ubuntu/mount/jenkins /home/ubuntu/mount/apache/

# Set ownership from root to ubuntu so that 'Wrong volume permissions?' error does not happen.
sudo chown -R ubuntu /home/ubuntu/mount

# Install Docker dependencies.
sudo apt-get -y install \
    apt-transport-https \
    ca-certificates \
    software-properties-common \
    gnupg2 \
    gnupg-agent \
    wget \
    curl \
    jq

# Add Docker’s official GPG key.
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Set up the stable repository.
sudo add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) \
    stable"

# Install the latest version of Docker.
sudo apt-get update && \
sudo apt-get -y install docker-ce docker-ce-cli containerd.io

# Install the latest version of Docker-Compose
VERSION=$(curl --silent https://api.github.com/repos/docker/compose/releases/latest | jq .name -r)
DESTINATION=/usr/local/bin/docker-compose

sudo curl -L https://github.com/docker/compose/releases/download/${VERSION}/docker-compose-$(uname -s)-$(uname -m) -o $DESTINATION
sudo chmod 755 $DESTINATION

sudo docker-compose -f /home/ubuntu/docker/docker-compose.yml up -d



























#    zip \
#    gzip \
#    tar \
#    apache2 \
#    php \
#    libapache2-mod-php

#curl -L -o allure.deb https://github.com/allure-framework/allure2/releases/download/2.13.8/allure_2.13.8-1_all.deb && \
#dpkg -i allure.deb

#wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
#sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > \
#    /etc/apt/sources.list.d/jenkins.list'
#sudo apt-get update
#sudo apt-get -y install \
#    adoptopenjdk-11-hotspot \
#    openjdk-11-jre-headless \
#    git \
#    maven \
#    jenkins

#sudo systemctl start jenkins
#sudo systemctl start apache2