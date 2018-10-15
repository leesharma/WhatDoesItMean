#!/usr/bin/env bash

mvn clean
mvn compile

cd src/main/python
bazel clean
bazel build -c opt //im2txt:run_inference
cd ../../..