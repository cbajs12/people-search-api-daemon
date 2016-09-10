#!/bin/sh

JAR="peope-search-api-daemon.jar"

#real
LOG_DIR=/home/ubuntu/peope-search-api-daemon/logs/
#real
ROOT_PATH=/home/ubuntu/peope-search-api-daemon/bin

export APP_LIBRARY=${ROOT_PATH}/commons-dbcp-1.2.2.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/commons-pool-1.3.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/ibatis-2.3.0.677.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/ibatis-sqlmap-2.3.4.726.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/ojdbc14.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/${JAR}

export LD_LIBRARY_PATH=.:${ROOT_PATH}
export JAVA_HOME=/usr/lib/jvm/java-8-oracle
export CLASSPATH=${ROOT_PATH}
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
                echo "FilesetManager Deamon STOP Done."
             fi
          fi
     done

     echo $APP_LIBRARY
     ${JAVA_HOME}/bin/java -Xms256m -Xmx512m -Dpeope-search-api-daemon=${JAR}  -cp ${APP_LIBRARY} Start >> ${LOG_DIR}/crawler.$(date +%y-%m-%d).log 2>&1 &
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
