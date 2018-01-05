#!/bin/bash
# Purpose: This script shows the functionality of the REST API of RocketChat

HOST=http://localhost:8080
RESPONSE=$(curl $HOST/api/v1/login -d "username=admin&password=password")
TOKEN=$(echo $RESPONSE | jq -r '.data.authToken')
USER=$(echo $RESPONSE | jq -r '.data.userId')

curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/me

curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/chat.postMessage \
     -d '{ "channel": "#general", "text": "This is a test!" }' -H "Content-type:application/json"

curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/channels.list
curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/groups.list

curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/integrations.list -H "Content-type: application/json"

curl -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" $HOST/api/v1/integrations.create \
     -d '{ "type": "webhook-outgoing", "event": "sendMessage", "enabled": true, "name": "biblebot", "channel": "all_public_channels,all_private_groups,all_direct_messages", "username": "rocket.cat", "urls": ["http://biblebot:8081/chat"], "scriptEnabled": false }' -H "Content-type: application/json"