#!/bin/bash
# Purpose: This script shows the functionality of the REST API of RocketChat

HOST=http://rocketchat:8080

echo "wait until ${HOST} is reachable"
echo $(curl --output /dev/null --silent --head --fail ${HOST})
TIME=0
until $(curl --output /dev/null --silent --head --fail ${HOST}); do
    printf "."
    sleep 1
    TIME=$[$TIME+1]
    if [ ${TIME}  -ge 300 ]; then
        echo "Host not reachable"
        echo "Tests failed!"
        exit 1
    fi
done

echo "login on ${HOST}"
TIME=0
isSuccess=false
until ${isSuccess} ; do
    printf "."
    sleep 1
    TIME=$[$TIME+1]
    if [ ${TIME}  -ge 300 ]; then
        echo "Can not log in reachable"
        echo "Tests failed!"
        exit 1
    fi

    RESPONSE=$(curl --silent ${HOST}/api/v1/login -d "username=admin&password=password")
    echo ${RESPONSE}
    STATUS=$(echo ${RESPONSE} | jq -r '.status')
    TOKEN=$(echo ${RESPONSE} | jq -r '.data.authToken')
    USER=$(echo ${RESPONSE} | jq -r '.data.userId')

    if [ "${STATUS}" == "success" ]; then
        echo "Logged in"
        isSuccess=true
    fi
done

echo "wait for biblebot integration on ${HOST}"
TIME=0
isSuccess=false
until ${isSuccess} ; do
    printf "."
    sleep 1
    TIME=$[$TIME+1]
    if [ ${TIME}  -ge 20 ]; then
        echo "Did not find the integration"
        echo "Tests failed!"
        exit 1
    fi

    INTEGRATIONS=$(curl --silent -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" ${HOST}/api/v1/integrations.list -H "Content-type: application/json")
    echo ${INTEGRATIONS}
    SUCCESS_STATUS=$(echo ${INTEGRATIONS} | jq -r '.success')
    if [ "${SUCCESS_STATUS}" == "true" ]; then
        echo "request successful"
        for row in $(echo "${INTEGRATIONS}" | jq -r '.integrations[] | @base64'); do
            _jq() {
                echo ${row} | base64 --decode | jq -r ${1}
            }
            INTEGRATION_NAME=$(_jq '.name')
            echo "found ${INTEGRATION_NAME}"
            if [ "biblebot" == "${INTEGRATION_NAME}" ]; then
                echo "Found integration biblebot"
                isSuccess=true
            fi
        done
    fi
done


POST=$(curl --silent -H "X-Auth-Token: $TOKEN" -H "X-User-Id: $USER" ${HOST}/api/v1/chat.postMessage \
     -d '{ "channel": "#general", "text": "Psalm 1" }' -H "Content-type:application/json")
echo ${POST}
SUCCESS_STATUS=$(echo ${POST} | jq -r '.success')
if [ "${SUCCESS_STATUS}" != "true" ]; then
    echo "post message did not work"
    echo "Tests failed!"
    exit 1
fi

echo "All tests passed."
exit 0