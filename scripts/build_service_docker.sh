#!/bin/bash
#
# Creates a docker image for the service SpringBoot App
set -euo pipefail
source $(dirname "${BASH_SOURCE[0]}")/common_functions.sh

cd_project_root
./gradlew clean
docker build -t service-app -f service/Dockerfile .