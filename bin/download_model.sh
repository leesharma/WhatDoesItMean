#!/bin/bash

# downloads a zipped copy of our underlying model
curl -L -o model.zip \
  https://www.dropbox.com/s/1q7cwmewooi18s0/model.zip?dl=0

# unzips the compressed file
unzip model.zip -d src/main/resources/