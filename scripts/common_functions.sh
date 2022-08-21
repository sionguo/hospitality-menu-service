#!/bin/bash
#
# Shared functions
set -euo pipefail

cd_project_root() {
  if [[ -z ${PROJECT_ROOT:-} ]]; then
    cd $(dirname "${BASH_SOURCE[0]}")/..;
  else
    cd "${PROJECT_ROOT}"
  fi
}
