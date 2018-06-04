#!/bin/bash
set -e
if [ -n "$2" ]
then
  echo "Stopping $1's app instance $2"
  echo "cf curl /v2/apps/`cf app $1 --guid`/instances/$2 -X 'DELETE'"
  cf curl /v2/apps/`cf app $1 --guid`/instances/$2 -X 'DELETE'
  for i in {1..5}
  do
    cf app $1
    sleep 5s
  done
else
  echo "Usage kill_app_instance <app_name> <instance_id>"
fi
