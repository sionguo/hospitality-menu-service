#!/bin/bash
#
# Starts minikube Kubernetes default cluster with optional recreation
set -euo pipefail

K8S_VERSION=v1.23.7

err() {
  echo "[$(date +'%Y-%m-%dT%H:%M:%S%z')]: $*" >&2
}

if ! [ -x "$(command -v minikube)" ]; then
  err "Minikube is not installed, follow README instructions. Exiting"
  exit 1
fi

while getopts ":recreate" opt; do
  case $opt in
    a)
      echo "Recreating minikube" >&2
      minikube delete
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
  esac
done

echo "Starting minikube with kubernetes-version ${K8S_VERSION}..."
minikube start --kubernetes-version="${K8S_VERSION}"

echo "Retrieving namespaces..."
minikube kubectl get namespaces
