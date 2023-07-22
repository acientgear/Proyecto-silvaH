#!/bin/bash

# Backup file path
BACKUP_PATH="/root/backups"
BACKUP_NAME="backup3.sql"

BACKUP_FILE_NAME="$(date +"backup-%d-%m-%y-%H%M%S.sql")"

# Run mysqldmp command
docker exec mysql-db bash -c 'exec mysqldump -u root -p"$MYSQL_ROOT_PASSWORD" --databases "$MYSQL_DATABASE"'> $BACKUP_PATH/$BACKUP_FILE_NAME ;