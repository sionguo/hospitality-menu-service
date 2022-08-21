Describe 'build_service_docker.sh'
  PROJECT_ROOT=$(pwd)
  docker() {
    echo "--Mock--[docker]: ${*}"
  }
  It 'should perform a gradle clean'
    When run source "${SCRIPTS_SRC_DIR}"/build_service_docker.sh
    The output should include '--Mock--[gradlew]: clean'
  End
  It 'should build the expected docker image'
    When run source "${SCRIPTS_SRC_DIR}"/build_service_docker.sh
    The output should include '--Mock--[docker]: build -t service-app -f service/Dockerfile .'
  End
    It 'should do gradle clean before building the docker image'
      When run source "${SCRIPTS_SRC_DIR}"/build_service_docker.sh
      The line 1 of stdout should equal '--Mock--[gradlew]: clean'
      The line 2 of stdout should equal '--Mock--[docker]: build -t service-app -f service/Dockerfile .'
    End
End