#!/usr/bin/env bash

echo "This script assumes that you have the apt-get package manager installed."
echo "If you don't, please either install it or manually install the dependencies"
echo "Bazel (https://bazel.build/) and Maven (https://maven.apache.org/)."
echo ""
echo "If you want to use a python virtual environment, stop this script and"
echo "activate it now."
echo ""

echo "============================="
echo "Installing bazel and maven..."
echo "============================="
sudo apt-get update -y
sudo apt-get upgrade -y
# Bazel
sudo apt-get install pkg-config zip g++ zlib1g-dev unzip python
chmod +x bazel-0.17.2-installer-linux-x86_64.sh
./bazel-0.17.2-installer-linux-x86_64.sh --user
export PATH="$PATH:$HOME/bin"

# Maven
cd /opt/
wget http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
sudo tar -xvzf apache-maven-3.3.9-bin.tar.gz
rm apache-maven-3.3.9-bin.tar.gz
sudo mv apache-maven-3.3.9 maven
# env vars
sudo echo "export M2_HOME=/opt/maven" > /etc/profile.d/mavenenv.sh
sudo echo "export PATH=\${M2_HOME}/bin:\${PATH}" > /etc/profile.d/mavenenv.sh
sudo chmod +x /etc/profile.d/mavenenv.sh
sudo source /etc/profile.d/mavenenv.sh

echo ""
echo "============================="
echo "Installing python packages..."
echo "============================="
pip install tensorflow
pip install numpy
pip install nltk

echo ""
echo "============================"
echo "Installing java packages..."
echo "============================"
mvn validate

echo ""
echo "=========================="
echo "Verifying installations..."
echo "=========================="
bazel version
mvn --version
pip --version