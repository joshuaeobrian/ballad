-- User table
CREATE TABLE ballad_users(
  id SERIAL PRIMARY KEY NOT NULL,
  first_name VARCHAR(25) NOT NULL ,
  last_name VARCHAR(25) NOT NULL,
  email VARCHAR(150) NOT NULL,
  usename VARCHAR(20) NOT NULL,
  password VARCHAR(100) NOT NULL,
  creation_date TIMESTAMP DEFAULT now(),
  active BOOLEAN DEFAULT TRUE

);
CREATE UNIQUE INDEX ballad_users_id_unique on ballad_users(id);
CREATE UNIQUE INDEX ballad_users_username_unique on ballad_users(username);

-- makes up user profile and info
CREATE TABLE ballad_user_profile(
  ballad_user_id INTEGER REFERENCES ballad_users(id),
  profile_photo bytea NULL,
  about VARCHAR(500) NULL

);
CREATE UNIQUE INDEX ballad_user_profile_ballad_user_id_unique ON ballad_user_profile(ballad_user_id);

-- table controller the ability of following people
CREATE TABLE following(
  ballad_user_id INTEGER NOT NULL REFERENCES  ballad_users(id),
  ballad_follower_id INTEGER NOT NULL REFERENCES ballad_users(id),
  following BOOLEAN DEFAULT FALSE
);
CREATE UNIQUE INDEX following_users_unique ON following(ballad_user_id, ballad_follower_id);


-- Ballads
CREATE TABLE ballads(
  id SERIAL PRIMARY KEY NOT NULL ,
  title VARCHAR(150) NOT NULL ,
  ballad VARCHAR(5000),
  public BOOLEAN DEFAULT FALSE,
  creation_date TIMESTAMP DEFAULT now()

);
CREATE UNIQUE INDEX ballads_id_unique ON ballads(id);


-- controls who can edit ballad
CREATE TABLE collaborators(
  ballad_id INTEGER NOT NULL REFERENCES ballads(id),
  ballad_user_id INTEGER NOT NULL REFERENCES ballad_users(id),
  creator BOOLEAN DEFAULT FALSE

);
CREATE UNIQUE INDEX collaborators_ballad_id_ballad_user on collaborators(ballad_id, ballad_user_id);


-- maintains the interactions amongst users
CREATE TABLE ballad_interaction(
  ballad_id INTEGER REFERENCES ballads(id),
  ballad_user_id INTEGER REFERENCES ballad_users(id),
  favorite BOOLEAN DEFAULT FALSE,
  liked BOOLEAN DEFAULT FALSE
);
CREATE UNIQUE INDEX ballad_interaction_ballad_id_user_id_unique ON ballad_interaction(ballad_id, ballad_user_id);


-- notes table for ballads
CREATE TABLE notes(
  id SERIAL PRIMARY KEY NOT NULL,
  ballad_use_id INTEGER NOT NULL REFERENCES ballad_users(id),
  notes VARCHAR(500) NOT NULL,
  creation_date TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX notes_id_unique ON notes(id);


-- roles of actions on logs
CREATE TABLE log_roles(
  id SERIAL PRIMARY KEY NOT NULL,
  action VARCHAR(30) NOT NULL
);
CREATE UNIQUE INDEX  log_roles_id_unique ON log_roles(id);
CREATE UNIQUE INDEX  log_roles_action_unique ON log_roles(action);


-- Store items that make up the ballad page that the user might interact with
CREATE TABLE ballad_items(
  id SERIAL PRIMARY KEY NOT NULL,
  item_type VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX ballad_items_id_unique ON ballad_items(id);
CREATE UNIQUE INDEX ballad_items_item_type_unique ON ballad_items(item_type);


-- logs for ballads
CREATE TABLE ballad_logs(
  ballad_id INTEGER NOT NULL REFERENCES ballads(id),
  ballad_user_id INTEGER NOT NULL REFERENCES ballad_users(id),
  action_id INTEGER NOT NULL REFERENCES log_roles(id),
  item_id INTEGER NOT NULL REFERENCES ballad_items(id),
  datetime TIMESTAMP DEFAULT now()
);

-- Controls Cookie sessions  Maybe
CREATE TABLE user_session(
  ballad_user_id INTEGER NOT NULL REFERENCES ballad_users(id),
  session_key VARCHAR(200) NOT NULL,
  datetime TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX user_session_user_id_unique ON user_session(ballad_user_id);

-- Main static data
INSERT INTO log_roles(action)VALUES ('Create');
INSERT INTO log_roles(action)VALUES ('Save');
INSERT INTO log_roles(action)VALUES ('Edit');
INSERT INTO log_roles(action)VALUES ('Delete');

INSERT INTO ballad_items(item_type)VALUES ('Ballad');
INSERT INTO ballad_items(item_type)VALUES ('Notes');
-- test user
INSERT INTO ballad_users
(first_name, last_name, email, username, password)
VALUES(
  'Josh',
  'OBrina',
  'test@gmail.com',
  'test12',
  'test123'
);
-- Inserts a ballad and inserts the creator into the
-- collaborators
WITH ballad_insert AS (
  INSERT INTO ballads(title, ballad)
  VALUES ('My First Ballad','yo yo yo yo')
  RETURNING id
)
INSERT INTO collaborators(ballad_id, ballad_user_id,creator)
VALUES (
  (SELECT id FROM ballad_insert),
  1,
  TRUE
);
