# Using the latest version of Ubuntu.
FROM ubuntu:20.04
LABEL \
Maintainer="Shahid Karim <skarim3000@gmail.com>" \
Description="Apache, PHP, Java, Maven, and Jenkins running on Ubuntu" \
BuildCommand="docker build -t h1ddengames/server:v2.0.0 -t h1ddengames/server:latest -f Docker/Dockerfile . --no-cache" \
RunCommand="docker run --name server -dit -p 80:80 -p 443:443 -p 8080:8080 h1ddengames/server" \
ExecCommand="docker exec -it server /bin/bash" \
BuildTime="382 seconds ~ 6.3 minutes" \
BuildSize="940.44 MB" \
Version="2.0.0"

# Start in /tmp folder.
WORKDIR /tmp

# Allow connections to port 80, 443, and 8080.
# For HTTP, HTTPS, and Jenkins respectively.
EXPOSE 80
EXPOSE 443
EXPOSE 8080

# Copy the website folder to the image.
# Copy the tools folder to the image.
COPY website /tmp/website
COPY tools /tmp/tools

# Setup Apache, PHP, Java 11, Jenkins, and Maven.
# 1. Install required software dependencies + Apache, PHP.
# 2. Add repository for Jenkins and AdoptOpenJDK.
# 3. Clean cache and update cache. -> Required before running apt-get install and after adding repo.
# 4. Install Java 11, Maven, and Jenkins. -> Required: openjdk-11-jre-headless otherwise Jenkins runs into AWT headless error.
# 5. Create directories to serve javadocs, reports, and jar.
RUN \
export DEBIAN_FRONTEND=noninteractive && \
apt-get update && \
apt-get -y --no-install-recommends install gnupg2 software-properties-common wget curl zip gzip tar apache2 php libapache2-mod-php && \
rm -rf /var/lib/apt/lists/*

RUN \
export DEBIAN_FRONTEND=noninteractive && \
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | apt-key add - && \
sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > \
        /etc/apt/sources.list.d/jenkins.list' && \
wget -q -O - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - && \
add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ && \
apt-get update && \
apt-get -y install adoptopenjdk-11-hotspot openjdk-11-jre-headless git maven && \
apt-get -y install jenkins && \
rm -rf /var/lib/apt/lists/* && \
cp -R /tmp/website/* /var/www/html && \
wget https://github.com/allure-framework/allure2/releases/download/2.13.8/allure_2.13.8-1_all.deb && \
dpkg -i allure_2.13.8-1_all.deb

# Set environment variables
ENV \
JAVA_HOME=/usr/bin/java \
JAVA_VERSION=jdk11u \
PATH="/usr/bin/java/bin:$PATH"

# Apache and Jenkins autostart on container start.
# Call to tail -f /dev/null in order to keep the container running.
CMD \
service apache2 start && \
service jenkins start && \
tail -f /dev/null
