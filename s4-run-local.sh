#!/bin/bash

# export SPRING_PROFILES_ACTIVE="local"

# mvn clean install
# java -jar target/tech-challenge-3-v*.jar debug

mvn spring-boot:run -Dspring-boot.run.profiles=local
