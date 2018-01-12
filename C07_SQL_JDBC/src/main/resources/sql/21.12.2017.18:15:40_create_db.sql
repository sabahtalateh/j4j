
--- DOWN
DROP TABLE IF EXISTS order_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS order_comment_attachment;
DROP TABLE IF EXISTS order_comment;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS role_permission;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS "user";

--- UP
CREATE TABLE "user" (
  id       SERIAL PRIMARY KEY,
  login    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE role (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE user_role (
  id        SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES "user" (id) ON DELETE CASCADE,
  role_id   INTEGER REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE permissions (
  id   SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL
);

CREATE TABLE role_permission (
  id            SERIAL PRIMARY KEY,
  role_id       INTEGER REFERENCES role (id) ON DELETE CASCADE,
  permission_id INTEGER REFERENCES permissions (id) ON DELETE CASCADE
);

CREATE TABLE order_status (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE "order" (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  description TEXT,
  user_id   INTEGER REFERENCES "user" (id),
  status_id   INTEGER REFERENCES order_status (id)
);

CREATE TABLE order_comment (
  id        SERIAL PRIMARY KEY,
  text      TEXT,
  order_id  INTEGER REFERENCES "order" (id) ON DELETE SET NULL,
  user_id INTEGER REFERENCES "user" (id) ON DELETE SET NULL
);

CREATE TABLE order_comment_attachment (
  id               SERIAL PRIMARY KEY,
  order_comment_id INTEGER REFERENCES order_comment (id) ON DELETE SET NULL,
  path             VARCHAR(2048) NOT NULL
);

CREATE TABLE category (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(1024) NOT NULL
);

CREATE TABLE order_category (
  id          SERIAL PRIMARY KEY,
  order_id    INTEGER REFERENCES "order" (id) ON DELETE CASCADE,
  categoty_id INTEGER REFERENCES category (id) ON DELETE CASCADE
);
