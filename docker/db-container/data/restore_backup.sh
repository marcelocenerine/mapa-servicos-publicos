#!/bin/bash
set -e

DBPATH="/data/db"
LOGPATH="/var/log/mongodb.log"
MONGOD="/usr/bin/mongod"
MONGO_RESTORE="/usr/bin/mongorestore"


function start_mongod() {
  echo "=> starting mongod..."
  $MONGOD --dbpath $DBPATH --logpath $LOGPATH --fork
  echo "=> mongod is running..."	
}

function stop_mongod() {
  echo "=> stopping mongod..."
  $MONGOD --shutdown
  echo "=> mongod is stopped."
}

function restore_backup() {
  local bkp_file=$1
  echo "=> restoring backup file ${bkp_file}..."

  cp $bkp_file /tmp/backup.tar.gz
  mkdir -p /tmp/backup
  tar -xvf /tmp/backup.tar.gz -C /tmp/backup --strip-components 1

  $MONGO_RESTORE /tmp/backup

  rm -rf /tmp/backup*
  echo "=> backup restored"
}

start_mongod
restore_backup $1
stop_mongod



