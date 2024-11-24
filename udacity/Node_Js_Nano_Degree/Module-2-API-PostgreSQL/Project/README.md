## Dependencies


### GLOBALS:
`npm i -g yarn`
`npm i -g nodemon`
`npm i -g dotenv dotenv-cli`
`npm i -g db-migrate db-migrate-pg`

### EXPRESS:
`npm i express`
`npm i --save-dev @types/express`
`npm i cors`
`npm i @types/cors`
`npm i express-validator`
`npm i express-async-errors`
`npm i body-parser` (for <= express@4.16.0 or older)


### POSTGRES:
`npm i pg`
`npm i db-migrate db-migrate-pg`
`npm i --save-dev @types/pg`

`db-migrate --config src/database/database.json create create-user-table --sql-file`  XXXXXXXXXXXXXX-migrationName.js
`db-migrate --config src/database/database.json up -e dev`
`db-migrate --config src/database/database.json down -e dev`

`db-migrate db:create databaseName`
`db-migrate db:drop databaseName`

`db-migrate reset`

### SECURITY:
`npm i bcrypt`
`npm i jsonwebtoken`

### OTHER:
`npm i --save-dev dotenv`
`npm i --save-dev nodemon`

### TypeScript:

`npm i --save-dev @types/<package-name>  or  npm i --save-dev @typescript/<package-name>`

`npm i --save-dev typescript`
`npm i --save-dev tsc-watch`
`npm i --save-dev ts-node`
`npm i --save-dev @types/node`

### ESLINT & PRETTIER:

`npm i --save-dev prettier eslint @eslint/js eslint-config-prettier eslint-plugin-node eslint-plugin-prettier`
`npm i --save-dev @typescript-eslint/eslint-plugin @typescript-eslint/parser`

### TESTING:

`npm i --save-dev jasmine jasmine-spec-reporter jasmine-ts supertest`
`npm i --save-dev @types/jasmine @types/supertest`

### DATABASE:

CREATE DATABASE IF NOT EXISTS store_db_dev;
DROP DATABASE IF EXISTS store_db_dev;


CREATE USER magical_user WITH PASSWORD 'password123';

GRANT ALL PRIVILEGES ON DATABASE store_db_dev TO magical_user;

GRANT ALL ON SCHEMA public TO magical_user; 
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO magical_user; 
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO magical_user; 
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO magical_user; 


`psql -U postgres -h localhost`
`psql -U magical_user postgres  -h localhost`

username: postgres
password: admin

----------------------
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY, 
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    firstName VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL
);

DROP TABLE IF EXISTS users;

----------------------
ALTER TABLE users ADD COLUMN dob date NULL;
ALTER TABLE users DROP COLUMN dob
----------------------
\l           (list all db)
\dt                                       (display table)
\c USERS  or psql  USERS     (connecting)
\q                                        (quit)
\dt\di+
---------------------
INSERT INTO USERS (name, age, job) VALUES ('Adam', 20, 'software engineer');
SELECT * FROM USERS;
SELECT COUNT(*) FROM USERS;

SELECT * FROM USERS WHERE age BETWEEN 20 AND 30;
SELECT * FROM USERS ORDER BY age DESC; 

SELECT name, id FROM USERS WHERE name LIKE '%engineer%'
SELECT name, id FROM USERS WHERE age IS NOT NULL;
UPDATE USERS SET age=30 WHERE id=1;
DELETE FROM USERS WHERE id=1;

JOINS
-----
SELECT * FROM products INNER JOIN order_products ON product.id = order_products.id;

## TODO:

-   [x] install dependencies
-   [ ] configs
