#!/bin/bash

# Return if an error occurs
set -e

if [ -z "$1" ]; then
  echo "Error: Migration file name missing"
  echo "e.g. create_migration.sh migration_name"
  exit 1
fi

MIGRATION_DIR="./src/main/resources/db/migration"

if [ ! -d "${MIGRATION_DIR}" ]; then
    echo "Migration directory not found. Creating: ${MIGRATION_DIR}"
    mkdir -p "${MIGRATION_DIR}"
fi

TIMESTAMP=$(date +"%Y%m%d%H%M%S")

FILENAME="V${TIMESTAMP}__${1}.sql"

FILEPATH="${MIGRATION_DIR}/${FILENAME}"


touch "${FILEPATH}"


echo "-- Write your SQL migration here" > "${FILEPATH}"

echo "Successfully created a migration: ${FILEPATH}"