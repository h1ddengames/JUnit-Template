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
sudo apt-get update && \
sudo apt-get -y install docker-ce docker-ce-cli containerd.io
