#!/usr/bin/env bash

#쉬고 있는 profile 찾기: real1사용 중이면 real2가 쉬고있는 방식

function find_idle_profie() {

    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400] #400보다 크면
    then
      CURRENT_PROFILE = real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}"

}

#쉬고있는 profile port찾기
function find_idle_port() {

  IDLE_PROFILE=$(find_idle_profie)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi

}