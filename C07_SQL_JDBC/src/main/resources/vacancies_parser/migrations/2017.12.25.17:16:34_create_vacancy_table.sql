CREATE TABLE IF NOT EXISTS vacancy (
  id            SERIAL PRIMARY KEY,
  vacancy_id    INTEGER       NOT NULL UNIQUE,
  title         VARCHAR(2048) NOT NULL,
  link          VARCHAR(2048) NOT NULL,
  author_name   VARCHAR(2048) NOT NULL,
  answers_count INTEGER       NOT NULL DEFAULT 0,
  views_count   INTEGER       NOT NULL DEFAULT 0,
  status        VARCHAR(255)  NOT NULL,
  updated_at    TIMESTAMP
);
