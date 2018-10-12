#!/usr/bin/env bash

echo "This script assumes that you have the Homebrew package manager installed."
echo "If you don't, please either install it (https://brew.sh/) or manually"
echo "install Bazel (https://bazel.build/) and Maven (https://maven.apache.org/)."
echo ""
echo "If you want to use a python virtual environment, stop this script and"
echo "activate it now."
echo ""

echo "=========================="
echo "Downloading model files..."
echo "=========================="
curl -L https://www.dropbox.com/s/vz7ydmtpnyb8cs1/full_model.zip?dl=0 > \
  src/main/resources/full_model.zip
unzip src/main/resources/full_model.zip -d src/main/resources/

echo "============================="
echo "Installing bazel and maven..."
echo "============================="
brew install bazel
brew install maven

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