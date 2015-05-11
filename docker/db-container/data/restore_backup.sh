#!/bin/bash
set -e

DBPATH="/data/db"
MONGO_RESTORE="/usr/bin/mongorestore"

bkp_file=$1
echo "=> restoring backup file ${bkp_file}..."

cp $bkp_file /tmp/backup.tar.gz
mkdir -p /tmp/backup
tar -xvf /tmp/backup.tar.gz -C /tmp/backup --strip-components 1

$MONGO_RESTORE --dbpath $DBPATH /tmp/backup

rm -rf /tmp/backup*
echo "=> backup restored"


