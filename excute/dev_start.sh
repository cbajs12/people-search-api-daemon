#!/bin/sh

JAR="peope-search-api-daemon.jar"

#real
LOG_DIR=/home/daemon/logs/
#real
ROOT_PATH=/home/daemon

export JAVA_HOME=/usr/lib/jvm/java-8-oracle
export PATH=${JAVA_HOME}/bin:$PATH
export LANG=ko_KR.eucKR

#java Run

if [ "$1" ]
then
  if [ $1 = "start" ]
  then
     echo "The argument was  start!"
     PID=`ps ax | grep java | grep ${JAR} | awk '{ print $1 }'`
     # echo $PID
     for JAR_PID in $PID
        do
          if [ $JAR_PID ] && [ $JAR_PID -gt 0 ]; then
           kill -9 $JAR_PID

             if [ $? -eq 0 ]; then
                #echo " ${JAR} $PID  STOP Done."
                echo "PeopleSearchCrawler Deamon STOP Done."
             fi
          fi
     done

     ${JAVA_HOME}/bin/java -Xms256m -Xmx512m -Dpeope-search-api-daemon=${JAR}  -cp /home/daemon/people-search-api-daemon.jar Start >> ${LOG_DIR}/crawler.$(date +%y-%m-%d).log 2>&1 &
     echo "PeopleSearchCrawler Deamon Started"

  else
    echo "The argument was  stop!"
    PID=`ps ax | grep java | grep ${JAR} | awk '{ print $1 }'`
   # echo $PID
    for JAR_PID in $PID
        do
          if [ $JAR_PID ] && [ $JAR_PID -gt 0 ]; then
           kill -9 $JAR_PID

             if [ $? -eq 0 ]; then
                #echo " ${JAR} $PID  STOP Done."
                echo "PeopleSearchCrawler Deamon STOP Done."
             fi
          fi
       done
  fi
else
  echo "This script needs one argument"
fi
