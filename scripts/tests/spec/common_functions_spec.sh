Describe 'common_functions.sh'
  Include "${SCRIPTS_SRC_DIR}"/common_functions.sh
  cd() {
    echo "--Mock--[cd]: ${*}"
  }
  It 'should cd into root relative to current caller'
    When call cd_project_root
    # ShellSpec tests are running 2 levels under root
    The output should equal '--Mock--[cd]: ../..'
  End
End