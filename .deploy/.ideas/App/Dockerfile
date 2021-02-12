FROM ubuntu:20.04

RUN \
export DEBIAN_FRONTEND=noninteractive && \
apt-get update && \
apt-get -y --no-install-recommends install \
    gnupg2 \
    software-properties-common \
    wget \
    curl \
    zip \
    gzip \
    tar \
    nano \
    apache2 \
    php && \
rm -rf /var/lib/apt/lists/* && \
a2enmod proxy && \
a2enmod proxy_http && \
a2enmod headers && \
echo "PS1=\"\[\033[38;5;81m\]\u\[$(tput sgr0)\]\[\033[38;5;14m\]@:\[$(tput sgr0)\]\[\033[38;5;6m\][\w]\[$(tput sgr0)\]\[\033[38;5;214m\]>\[$(tput sgr0)\] \[$(tput sgr0)\]\"" >> /etc/bash.bashrc

CMD \
service apache2 start && \
tail -f /dev/null