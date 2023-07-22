#!/bin/bash

# Backup file path
BACKUP_PATH="/root/backups"

BACKUP_FILE_NAME="$(date +"backup-%d-%m-%y-%H%M%S.sql")"

BACKUP_CLOUD_PATH="backups"

RCLONE_CLOUD_REMOTE="backups"

# Run mysqldmp command
docker exec mysql-db bash -c 'exec mysqldump -u root -p"$MYSQL_ROOT_PASSWORD" --databases "$MYSQL_DATABASE"'> $BACKUP_PATH/$BACKUP_FILE_NAME ;

rclone -v copy $BACKUP_PATH $RCLONE_CLOUD_REMOTE:$BACKUP_CLOUD_PATH; 

rm $BACKUP_PATH/$BACKUP_FILE_NAME;