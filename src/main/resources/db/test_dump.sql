--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: login_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login_requests (
    id integer NOT NULL,
    login character varying(50) NOT NULL,
    request_count integer NOT NULL
);


ALTER TABLE public.login_requests OWNER TO postgres;

--
-- Name: login_requests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.login_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_requests_id_seq OWNER TO postgres;

--
-- Name: login_requests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.login_requests_id_seq OWNED BY public.login_requests.id;


--
-- Name: login_requests id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_requests ALTER COLUMN id SET DEFAULT nextval('public.login_requests_id_seq'::regclass);


--
-- Data for Name: login_requests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.login_requests (id, login, request_count) FROM stdin;
2	123	1
4	admin	1
1	octocat	8
3	cat	3
\.


--
-- Name: login_requests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.login_requests_id_seq', 4, true);


--
-- Name: login_requests login_requests_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_requests
    ADD CONSTRAINT login_requests_login_key UNIQUE (login);


--
-- Name: login_requests login_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_requests
    ADD CONSTRAINT login_requests_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

