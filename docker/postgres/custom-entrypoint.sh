#!/bin/bash
# Run your SQL script regardless of DB state
psql -U uptime -d uptime -f /docker-entrypoint-initdb.d/tables_postgres.sql
# Start PostgreSQL normally
exec docker-entrypoint.sh postgres