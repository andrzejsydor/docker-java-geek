#!/bin/bash

mkdir -p prime
curl https://start.spring.io/starter.zip -d dependencies=web -d bootVersion=2.1.5.RELEASE -d applicationName=Prime -d baseDir=prime -o prime.zip
unzip prime.zip
rm prime.zip
