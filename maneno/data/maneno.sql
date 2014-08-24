--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.1
-- Dumped by pg_dump version 9.2.1
-- Started on 2014-08-24 23:37:27

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 33809)
-- Name: tp; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA tp;


ALTER SCHEMA tp OWNER TO postgres;

--
-- TOC entry 179 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1974 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = tp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 33812)
-- Name: categorie; Type: TABLE; Schema: tp; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie (
    id integer NOT NULL,
    libelle character varying(255) NOT NULL
);


ALTER TABLE tp.categorie OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 33810)
-- Name: categorie_id_seq; Type: SEQUENCE; Schema: tp; Owner: postgres
--

CREATE SEQUENCE categorie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp.categorie_id_seq OWNER TO postgres;

--
-- TOC entry 1975 (class 0 OID 0)
-- Dependencies: 169
-- Name: categorie_id_seq; Type: SEQUENCE OWNED BY; Schema: tp; Owner: postgres
--

ALTER SEQUENCE categorie_id_seq OWNED BY categorie.id;


--
-- TOC entry 1976 (class 0 OID 0)
-- Dependencies: 169
-- Name: categorie_id_seq; Type: SEQUENCE SET; Schema: tp; Owner: postgres
--

SELECT pg_catalog.setval('categorie_id_seq', 5, true);


--
-- TOC entry 174 (class 1259 OID 33841)
-- Name: client; Type: TABLE; Schema: tp; Owner: postgres; Tablespace: 
--

CREATE TABLE client (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    prenom character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    mail character varying(255) NOT NULL,
    adresse character varying(255) NOT NULL
);


ALTER TABLE tp.client OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 33839)
-- Name: client_id_seq; Type: SEQUENCE; Schema: tp; Owner: postgres
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp.client_id_seq OWNER TO postgres;

--
-- TOC entry 1977 (class 0 OID 0)
-- Dependencies: 173
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: tp; Owner: postgres
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- TOC entry 1978 (class 0 OID 0)
-- Dependencies: 173
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: tp; Owner: postgres
--

SELECT pg_catalog.setval('client_id_seq', 3, true);


--
-- TOC entry 176 (class 1259 OID 33852)
-- Name: commande; Type: TABLE; Schema: tp; Owner: postgres; Tablespace: 
--

CREATE TABLE commande (
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    total real NOT NULL,
    id_client integer NOT NULL
);


ALTER TABLE tp.commande OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 33850)
-- Name: commande_id_seq; Type: SEQUENCE; Schema: tp; Owner: postgres
--

CREATE SEQUENCE commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp.commande_id_seq OWNER TO postgres;

--
-- TOC entry 1979 (class 0 OID 0)
-- Dependencies: 175
-- Name: commande_id_seq; Type: SEQUENCE OWNED BY; Schema: tp; Owner: postgres
--

ALTER SEQUENCE commande_id_seq OWNED BY commande.id;


--
-- TOC entry 1980 (class 0 OID 0)
-- Dependencies: 175
-- Name: commande_id_seq; Type: SEQUENCE SET; Schema: tp; Owner: postgres
--

SELECT pg_catalog.setval('commande_id_seq', 6, true);


--
-- TOC entry 178 (class 1259 OID 33873)
-- Name: detail_commande; Type: TABLE; Schema: tp; Owner: postgres; Tablespace: 
--

CREATE TABLE detail_commande (
    id integer NOT NULL,
    id_commande integer NOT NULL,
    id_produit integer NOT NULL,
    prix_jour real NOT NULL,
    quantite integer NOT NULL
);


ALTER TABLE tp.detail_commande OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 33871)
-- Name: detail_commande_id_seq; Type: SEQUENCE; Schema: tp; Owner: postgres
--

CREATE SEQUENCE detail_commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp.detail_commande_id_seq OWNER TO postgres;

--
-- TOC entry 1981 (class 0 OID 0)
-- Dependencies: 177
-- Name: detail_commande_id_seq; Type: SEQUENCE OWNED BY; Schema: tp; Owner: postgres
--

ALTER SEQUENCE detail_commande_id_seq OWNED BY detail_commande.id;


--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 177
-- Name: detail_commande_id_seq; Type: SEQUENCE SET; Schema: tp; Owner: postgres
--

SELECT pg_catalog.setval('detail_commande_id_seq', 9, true);


--
-- TOC entry 172 (class 1259 OID 33820)
-- Name: produit; Type: TABLE; Schema: tp; Owner: postgres; Tablespace: 
--

CREATE TABLE produit (
    id integer NOT NULL,
    libelle character varying(255) NOT NULL,
    description text NOT NULL,
    prix real NOT NULL,
    stock integer NOT NULL,
    id_categorie integer NOT NULL
);


ALTER TABLE tp.produit OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 33818)
-- Name: produit_id_seq; Type: SEQUENCE; Schema: tp; Owner: postgres
--

CREATE SEQUENCE produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tp.produit_id_seq OWNER TO postgres;

--
-- TOC entry 1983 (class 0 OID 0)
-- Dependencies: 171
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: tp; Owner: postgres
--

ALTER SEQUENCE produit_id_seq OWNED BY produit.id;


--
-- TOC entry 1984 (class 0 OID 0)
-- Dependencies: 171
-- Name: produit_id_seq; Type: SEQUENCE SET; Schema: tp; Owner: postgres
--

SELECT pg_catalog.setval('produit_id_seq', 4, true);


--
-- TOC entry 1943 (class 2604 OID 33815)
-- Name: id; Type: DEFAULT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY categorie ALTER COLUMN id SET DEFAULT nextval('categorie_id_seq'::regclass);


--
-- TOC entry 1945 (class 2604 OID 33844)
-- Name: id; Type: DEFAULT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 33855)
-- Name: id; Type: DEFAULT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY commande ALTER COLUMN id SET DEFAULT nextval('commande_id_seq'::regclass);


--
-- TOC entry 1947 (class 2604 OID 33876)
-- Name: id; Type: DEFAULT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY detail_commande ALTER COLUMN id SET DEFAULT nextval('detail_commande_id_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 33823)
-- Name: id; Type: DEFAULT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY produit ALTER COLUMN id SET DEFAULT nextval('produit_id_seq'::regclass);


--
-- TOC entry 1962 (class 0 OID 33812)
-- Dependencies: 170
-- Data for Name: categorie; Type: TABLE DATA; Schema: tp; Owner: postgres
--

COPY categorie (id, libelle) FROM stdin;
1	categorie 01
2	categorie 02
4	categorie 03
5	categorie 004
\.


--
-- TOC entry 1964 (class 0 OID 33841)
-- Dependencies: 174
-- Data for Name: client; Type: TABLE DATA; Schema: tp; Owner: postgres
--

COPY client (id, nom, prenom, login, password, mail, adresse) FROM stdin;
1	toto1	tata1	toto1	toto1	toto@test.mg	adresse toto 1
2	toto2	tata2	toto2	toto2	toto2@yy.mg	adr T2
3	titi	tete	titi	titi	titi@tiit.mg	adr titit
\.


--
-- TOC entry 1965 (class 0 OID 33852)
-- Dependencies: 176
-- Data for Name: commande; Type: TABLE DATA; Schema: tp; Owner: postgres
--

COPY commande (id, date, total, id_client) FROM stdin;
6	2014-08-24 23:36:37	10607.2002	3
\.


--
-- TOC entry 1966 (class 0 OID 33873)
-- Dependencies: 178
-- Data for Name: detail_commande; Type: TABLE DATA; Schema: tp; Owner: postgres
--

COPY detail_commande (id, id_commande, id_produit, prix_jour, quantite) FROM stdin;
7	6	2	220.119995	10
8	6	3	330.299988	20
9	6	4	360	5
\.


--
-- TOC entry 1963 (class 0 OID 33820)
-- Dependencies: 172
-- Data for Name: produit; Type: TABLE DATA; Schema: tp; Owner: postgres
--

COPY produit (id, libelle, description, prix, stock, id_categorie) FROM stdin;
1	produit 01	desc 01	150	6	1
2	prod2XX	des2XX	220.119995	180	2
3	prod3	d3	330.299988	240	2
4	prod III	desc 03	360	25	4
\.


--
-- TOC entry 1949 (class 2606 OID 33817)
-- Name: categorie_pkey; Type: CONSTRAINT; Schema: tp; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (id);


--
-- TOC entry 1953 (class 2606 OID 33849)
-- Name: client_pkey; Type: CONSTRAINT; Schema: tp; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 1955 (class 2606 OID 33857)
-- Name: commande_pkey; Type: CONSTRAINT; Schema: tp; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (id);


--
-- TOC entry 1957 (class 2606 OID 33878)
-- Name: detail_commande_pkey; Type: CONSTRAINT; Schema: tp; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY detail_commande
    ADD CONSTRAINT detail_commande_pkey PRIMARY KEY (id);


--
-- TOC entry 1951 (class 2606 OID 33828)
-- Name: produit_pkey; Type: CONSTRAINT; Schema: tp; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 1959 (class 2606 OID 33991)
-- Name: commande_id_client_fkey; Type: FK CONSTRAINT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY commande
    ADD CONSTRAINT commande_id_client_fkey FOREIGN KEY (id_client) REFERENCES client(id) MATCH FULL;


--
-- TOC entry 1960 (class 2606 OID 33970)
-- Name: detail_commande_id_commande_fkey; Type: FK CONSTRAINT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY detail_commande
    ADD CONSTRAINT detail_commande_id_commande_fkey FOREIGN KEY (id_commande) REFERENCES commande(id);


--
-- TOC entry 1961 (class 2606 OID 33975)
-- Name: detail_commande_id_produit_fkey; Type: FK CONSTRAINT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY detail_commande
    ADD CONSTRAINT detail_commande_id_produit_fkey FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 1958 (class 2606 OID 33985)
-- Name: id_categorie; Type: FK CONSTRAINT; Schema: tp; Owner: postgres
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT id_categorie FOREIGN KEY (id_categorie) REFERENCES categorie(id) MATCH FULL;


--
-- TOC entry 1973 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-08-24 23:37:28

--
-- PostgreSQL database dump complete
--

