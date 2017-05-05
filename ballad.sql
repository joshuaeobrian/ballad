--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ballad_interaction; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE ballad_interaction (
    ballad_id integer,
    ballad_user_id integer,
    favorite boolean DEFAULT false,
    liked boolean DEFAULT false
);


ALTER TABLE ballad_interaction OWNER TO josh;

--
-- Name: ballad_items; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE ballad_items (
    id integer NOT NULL,
    item_type character varying(100) NOT NULL
);


ALTER TABLE ballad_items OWNER TO josh;

--
-- Name: ballad_items_id_seq; Type: SEQUENCE; Schema: public; Owner: josh
--

CREATE SEQUENCE ballad_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ballad_items_id_seq OWNER TO josh;

--
-- Name: ballad_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josh
--

ALTER SEQUENCE ballad_items_id_seq OWNED BY ballad_items.id;


--
-- Name: ballad_logs; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE ballad_logs (
    ballad_id integer NOT NULL,
    ballad_user_id integer NOT NULL,
    action_id integer NOT NULL,
    item_id integer NOT NULL,
    datetime date DEFAULT now()
);


ALTER TABLE ballad_logs OWNER TO josh;

--
-- Name: ballad_users; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE ballad_users (
    id integer NOT NULL,
    first_name character varying(25) NOT NULL,
    last_name character varying(25) NOT NULL,
    email character varying(150) NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(100) NOT NULL,
    creation_date date DEFAULT now(),
    active boolean DEFAULT true,
    profile_image bytea,
    about character varying(1000)
);


ALTER TABLE ballad_users OWNER TO josh;

--
-- Name: ballad_users_id_seq; Type: SEQUENCE; Schema: public; Owner: josh
--

CREATE SEQUENCE ballad_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ballad_users_id_seq OWNER TO josh;

--
-- Name: ballad_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josh
--

ALTER SEQUENCE ballad_users_id_seq OWNED BY ballad_users.id;


--
-- Name: ballads; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE ballads (
    id integer NOT NULL,
    title character varying(150) NOT NULL,
    ballad character varying(5000),
    public boolean DEFAULT false,
    creation_date date DEFAULT now(),
    creator_id integer
);


ALTER TABLE ballads OWNER TO josh;

--
-- Name: ballads_id_seq; Type: SEQUENCE; Schema: public; Owner: josh
--

CREATE SEQUENCE ballads_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ballads_id_seq OWNER TO josh;

--
-- Name: ballads_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josh
--

ALTER SEQUENCE ballads_id_seq OWNED BY ballads.id;


--
-- Name: collaborators; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE collaborators (
    ballad_id integer NOT NULL,
    ballad_user_id integer NOT NULL,
    creator boolean DEFAULT false
);


ALTER TABLE collaborators OWNER TO josh;

--
-- Name: following; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE following (
    ballad_user_id integer NOT NULL,
    ballad_follower_id integer NOT NULL,
    following boolean DEFAULT false
);


ALTER TABLE following OWNER TO josh;

--
-- Name: log_roles; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE log_roles (
    id integer NOT NULL,
    action character varying(30) NOT NULL
);


ALTER TABLE log_roles OWNER TO josh;

--
-- Name: log_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: josh
--

CREATE SEQUENCE log_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE log_roles_id_seq OWNER TO josh;

--
-- Name: log_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josh
--

ALTER SEQUENCE log_roles_id_seq OWNED BY log_roles.id;


--
-- Name: notes; Type: TABLE; Schema: public; Owner: josh
--

CREATE TABLE notes (
    id integer NOT NULL,
    ballad_use_id integer NOT NULL,
    notes character varying(500) NOT NULL,
    creation_date date DEFAULT now(),
    ballad_id integer
);


ALTER TABLE notes OWNER TO josh;

--
-- Name: notes_id_seq; Type: SEQUENCE; Schema: public; Owner: josh
--

CREATE SEQUENCE notes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notes_id_seq OWNER TO josh;

--
-- Name: notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: josh
--

ALTER SEQUENCE notes_id_seq OWNED BY notes.id;


--
-- Name: ballad_items id; Type: DEFAULT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_items ALTER COLUMN id SET DEFAULT nextval('ballad_items_id_seq'::regclass);


--
-- Name: ballad_users id; Type: DEFAULT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_users ALTER COLUMN id SET DEFAULT nextval('ballad_users_id_seq'::regclass);


--
-- Name: ballads id; Type: DEFAULT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballads ALTER COLUMN id SET DEFAULT nextval('ballads_id_seq'::regclass);


--
-- Name: log_roles id; Type: DEFAULT; Schema: public; Owner: josh
--

ALTER TABLE ONLY log_roles ALTER COLUMN id SET DEFAULT nextval('log_roles_id_seq'::regclass);


--
-- Name: notes id; Type: DEFAULT; Schema: public; Owner: josh
--

ALTER TABLE ONLY notes ALTER COLUMN id SET DEFAULT nextval('notes_id_seq'::regclass);


--
-- Data for Name: ballad_interaction; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY ballad_interaction (ballad_id, ballad_user_id, favorite, liked) FROM stdin;
1	1	f	f
7	4	f	f
10	1	f	f
11	4	t	f
8	4	t	f
9	4	t	f
9	1	t	f
9	3	t	f
6	4	t	f
5	4	t	f
\.


--
-- Data for Name: ballad_items; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY ballad_items (id, item_type) FROM stdin;
1	Ballad
2	Notes
\.


--
-- Name: ballad_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: josh
--

SELECT pg_catalog.setval('ballad_items_id_seq', 2, true);


--
-- Data for Name: ballad_logs; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY ballad_logs (ballad_id, ballad_user_id, action_id, item_id, datetime) FROM stdin;
1	1	2	1	2017-04-30
4	4	1	1	2017-04-30
5	4	1	1	2017-04-30
6	4	1	1	2017-04-30
7	4	1	1	2017-04-30
8	4	1	1	2017-04-30
9	4	1	1	2017-04-30
10	1	1	1	2017-04-30
11	4	1	1	2017-04-30
11	4	3	1	2017-04-30
11	4	3	1	2017-04-30
11	4	3	1	2017-04-30
5	4	3	1	2017-04-30
5	4	3	1	2017-04-30
7	4	3	1	2017-05-01
\.


--
-- Data for Name: ballad_users; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY ballad_users (id, first_name, last_name, email, username, password, creation_date, active, profile_image, about) FROM stdin;
1	Josh	OBrina	test@gmail.com	test12	test123	2017-04-28	t	\N	\N
2	Bob	Log	bobl@gmail.com	b123	b223	2017-04-28	t	\N	\N
3	Larry	Bird	lBird@bird.com	birdman22	12345	2017-04-28	t	\N	\N
4	Joe	Thorton	jthorton@gmail.com	jTest	jTest12	2017-04-30	t	\N	\N
5	Chris	Jones	Jones@gmail.com	jonesTest	jones123	2017-05-02	t	\N	\N
\.


--
-- Name: ballad_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: josh
--

SELECT pg_catalog.setval('ballad_users_id_seq', 5, true);


--
-- Data for Name: ballads; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY ballads (id, title, ballad, public, creation_date, creator_id) FROM stdin;
1	My First Ballad	yo yo yo yo	f	2017-04-28	1
3	The Long Way	I once rode a bird into a herd of curd.	f	2017-04-28	3
8	This Is a 5th entry	To Be or not to Be\nthat tis the question	f	2017-04-30	4
10	To Save or not to Save	This is going to save\ndave to update 	f	2017-04-30	1
11	This will Update	The bird ate the curd from the other side of the herd	t	2017-04-30	4
4	This is Joes first Ballad	Joe likes to test Things	t	2017-04-30	4
6	This is my Third Ballad	test test tes tg test	t	2017-04-30	4
9	This is a True Test	what what I dont fly 	t	2017-04-30	4
5	Joe and Potatoes	My name is joe \nI like to play in the Snow\nSometime I eat Potatoes \nthat glow.	t	2017-04-30	4
7	This is my fourth test	what is going\nthe word \n was the bird\n\n	t	2017-04-30	4
\.


--
-- Name: ballads_id_seq; Type: SEQUENCE SET; Schema: public; Owner: josh
--

SELECT pg_catalog.setval('ballads_id_seq', 11, true);


--
-- Data for Name: collaborators; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY collaborators (ballad_id, ballad_user_id, creator) FROM stdin;
1	1	t
3	3	t
4	4	t
5	4	t
6	4	t
7	4	t
8	4	t
9	4	t
10	1	t
11	4	t
\.


--
-- Data for Name: following; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY following (ballad_user_id, ballad_follower_id, following) FROM stdin;
\.


--
-- Data for Name: log_roles; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY log_roles (id, action) FROM stdin;
2	Save
3	Edit
4	Delete
1	Created
\.


--
-- Name: log_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: josh
--

SELECT pg_catalog.setval('log_roles_id_seq', 4, true);


--
-- Data for Name: notes; Type: TABLE DATA; Schema: public; Owner: josh
--

COPY notes (id, ballad_use_id, notes, creation_date, ballad_id) FROM stdin;
1	1	needs more flow	2017-04-30	1
\.


--
-- Name: notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: josh
--

SELECT pg_catalog.setval('notes_id_seq', 1, false);


--
-- Name: ballad_items ballad_items_pkey; Type: CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_items
    ADD CONSTRAINT ballad_items_pkey PRIMARY KEY (id);


--
-- Name: ballad_users ballad_users_pkey; Type: CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_users
    ADD CONSTRAINT ballad_users_pkey PRIMARY KEY (id);


--
-- Name: ballads ballads_pkey; Type: CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballads
    ADD CONSTRAINT ballads_pkey PRIMARY KEY (id);


--
-- Name: log_roles log_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY log_roles
    ADD CONSTRAINT log_roles_pkey PRIMARY KEY (id);


--
-- Name: notes notes_pkey; Type: CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY notes
    ADD CONSTRAINT notes_pkey PRIMARY KEY (id);


--
-- Name: ballad_interaction_ballad_id_user_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballad_interaction_ballad_id_user_id_unique ON ballad_interaction USING btree (ballad_id, ballad_user_id);


--
-- Name: ballad_items_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballad_items_id_unique ON ballad_items USING btree (id);


--
-- Name: ballad_items_item_type_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballad_items_item_type_unique ON ballad_items USING btree (item_type);


--
-- Name: ballad_users_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballad_users_id_unique ON ballad_users USING btree (id);


--
-- Name: ballad_users_username_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballad_users_username_unique ON ballad_users USING btree (username);


--
-- Name: ballads_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX ballads_id_unique ON ballads USING btree (id);


--
-- Name: collaborators_ballad_id_ballad_user; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX collaborators_ballad_id_ballad_user ON collaborators USING btree (ballad_id, ballad_user_id);


--
-- Name: following_users_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX following_users_unique ON following USING btree (ballad_user_id, ballad_follower_id);


--
-- Name: log_roles_action_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX log_roles_action_unique ON log_roles USING btree (action);


--
-- Name: log_roles_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX log_roles_id_unique ON log_roles USING btree (id);


--
-- Name: notes_id_unique; Type: INDEX; Schema: public; Owner: josh
--

CREATE UNIQUE INDEX notes_id_unique ON notes USING btree (id);


--
-- Name: ballad_interaction ballad_interaction_ballad_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_interaction
    ADD CONSTRAINT ballad_interaction_ballad_id_fkey FOREIGN KEY (ballad_id) REFERENCES ballads(id);


--
-- Name: ballad_interaction ballad_interaction_ballad_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_interaction
    ADD CONSTRAINT ballad_interaction_ballad_user_id_fkey FOREIGN KEY (ballad_user_id) REFERENCES ballad_users(id);


--
-- Name: ballad_logs ballad_logs_action_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_logs
    ADD CONSTRAINT ballad_logs_action_id_fkey FOREIGN KEY (action_id) REFERENCES log_roles(id);


--
-- Name: ballad_logs ballad_logs_ballad_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_logs
    ADD CONSTRAINT ballad_logs_ballad_id_fkey FOREIGN KEY (ballad_id) REFERENCES ballads(id);


--
-- Name: ballad_logs ballad_logs_ballad_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_logs
    ADD CONSTRAINT ballad_logs_ballad_user_id_fkey FOREIGN KEY (ballad_user_id) REFERENCES ballad_users(id);


--
-- Name: ballad_logs ballad_logs_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballad_logs
    ADD CONSTRAINT ballad_logs_item_id_fkey FOREIGN KEY (item_id) REFERENCES ballad_items(id);


--
-- Name: ballads ballads_creator_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY ballads
    ADD CONSTRAINT ballads_creator_id_fkey FOREIGN KEY (creator_id) REFERENCES ballad_users(id);


--
-- Name: collaborators collaborators_ballad_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY collaborators
    ADD CONSTRAINT collaborators_ballad_id_fkey FOREIGN KEY (ballad_id) REFERENCES ballads(id);


--
-- Name: collaborators collaborators_ballad_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY collaborators
    ADD CONSTRAINT collaborators_ballad_user_id_fkey FOREIGN KEY (ballad_user_id) REFERENCES ballad_users(id);


--
-- Name: following following_ballad_follower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY following
    ADD CONSTRAINT following_ballad_follower_id_fkey FOREIGN KEY (ballad_follower_id) REFERENCES ballad_users(id);


--
-- Name: following following_ballad_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY following
    ADD CONSTRAINT following_ballad_user_id_fkey FOREIGN KEY (ballad_user_id) REFERENCES ballad_users(id);


--
-- Name: notes notes_ballad_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY notes
    ADD CONSTRAINT notes_ballad_id_fkey FOREIGN KEY (ballad_id) REFERENCES ballads(id);


--
-- Name: notes notes_ballad_use_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: josh
--

ALTER TABLE ONLY notes
    ADD CONSTRAINT notes_ballad_use_id_fkey FOREIGN KEY (ballad_use_id) REFERENCES ballad_users(id);


--
-- PostgreSQL database dump complete
--

