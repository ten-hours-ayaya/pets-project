-- file: 10-create-user-and-db.sql
CREATE DATABASE petsproject;
CREATE ROLE petsproject WITH PASSWORD 'petsproject';
GRANT ALL PRIVILEGES ON DATABASE petsproject TO petsproject;
ALTER ROLE petsproject WITH LOGIN;