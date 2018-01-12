#!/bin/bash
# Purpose: This script shows the functionality of the REST API of RocketChat

HOST=http://rocketchat:8080

echo "wait until ${HOST} is reachable"
echo $(curl --output /dev/null --silent --head --fail ${HOST})
TIME=0
until $(curl --output /dev/null --silent --head --fail ${HOST}); do
    printf ".$TIME"
    sleep 1
    TIME=$[$TIME+1]
    if [ ${TIME}  -ge 100 ]; then
        echo "Host not reachable"
        echo "Tests failed!"
        exit 1
    fi
done

RESPONSE=$(curl --silent $HOST/api/v1/login -d "username=admin&password=password")
echo $RESPONSE

TOKEN=$(echo $RESPONSE | jq -r '.data.authToken')
USER=$(echo $RESPONSE | jq -r '.data.userId')



INTEGRATIONS=$(curl --silent -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/integrations.list -H "Content-type: application/json")
echo $INTEGRATIONS

POST=$(curl --silent -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/chat.postMessage \
     -d '{ "channel": "#general", "text": "Psalm 1" }' -H "Content-type:application/json")
echo $POST

#if curl web | grep -q '<b>Visits:</b> '; then
#  echo "Tests passed!"
#  exit 0
#else
#  echo "Tests failed!"
#  exit 1
#fi