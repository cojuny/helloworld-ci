#!/bin/bash

sudo apt update
sudo apt install openjdk-11-jdk -y

sudo wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
sudo tar -xvf apache-maven-3.6.3-bin.tar.gz
sudo mv apache-maven-3.6.3 /opt/

echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64' >> ~/.bashrc
echo 'M2_HOME=/opt/apache-maven-3.6.3' >> ~/.bashrc
source ~/.bashrc