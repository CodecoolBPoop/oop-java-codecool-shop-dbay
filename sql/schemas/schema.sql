--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.addresses (
    id integer NOT NULL,
    country character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    zipcode integer NOT NULL,
    address character varying(255) NOT NULL
);


ALTER TABLE public.addresses OWNER TO zoli;

--
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_id_seq OWNER TO zoli;

--
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;


--
-- Name: line_items; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.line_items (
    id integer NOT NULL,
    product integer NOT NULL,
    quantity integer DEFAULT 1 NOT NULL,
    cart integer NOT NULL
);


ALTER TABLE public.line_items OWNER TO zoli;

--
-- Name: line_items_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.line_items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.line_items_id_seq OWNER TO zoli;

--
-- Name: line_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.line_items_id_seq OWNED BY public.line_items.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    personalinfo integer NOT NULL,
    shippingaddress integer NOT NULL,
    cart integer,
    billingaddress integer NOT NULL,
    sessionid character varying(255) NOT NULL
);


ALTER TABLE public.orders OWNER TO zoli;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO zoli;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: personal_info; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.personal_info (
    id integer NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL,
    email character varying(255),
    phonenumber character varying(255) NOT NULL
);


ALTER TABLE public.personal_info OWNER TO zoli;

--
-- Name: personal_info_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.personal_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personal_info_id_seq OWNER TO zoli;

--
-- Name: personal_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.personal_info_id_seq OWNED BY public.personal_info.id;


--
-- Name: product_categories; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.product_categories (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    department character varying(255)
);


ALTER TABLE public.product_categories OWNER TO zoli;

--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.product_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_category_id_seq OWNER TO zoli;

--
-- Name: product_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_categories.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    defaultprice double precision NOT NULL,
    defaultcurrency character varying(255),
    productcategory integer,
    supplier integer,
    bhp integer NOT NULL,
    acceleration double precision NOT NULL,
    modelyear integer NOT NULL,
    topspeed integer NOT NULL
);


ALTER TABLE public.products OWNER TO zoli;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO zoli;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: shopping_carts; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.shopping_carts (
    id integer NOT NULL,
    sessionid character varying(255) NOT NULL,
    totalprice double precision
);


ALTER TABLE public.shopping_carts OWNER TO zoli;

--
-- Name: shopping_carts_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.shopping_carts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shopping_carts_id_seq OWNER TO zoli;

--
-- Name: shopping_carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.shopping_carts_id_seq OWNED BY public.shopping_carts.id;


--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: zoli
--

CREATE TABLE public.suppliers (
    id integer NOT NULL,
    name character varying(255),
    description character varying(255)
);


ALTER TABLE public.suppliers OWNER TO zoli;

--
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: zoli
--

CREATE SEQUENCE public.suppliers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suppliers_id_seq OWNER TO zoli;

--
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: zoli
--

ALTER SEQUENCE public.suppliers_id_seq OWNED BY public.suppliers.id;


--
-- Name: addresses id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);


--
-- Name: line_items id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.line_items ALTER COLUMN id SET DEFAULT nextval('public.line_items_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: personal_info id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.personal_info ALTER COLUMN id SET DEFAULT nextval('public.personal_info_id_seq'::regclass);


--
-- Name: product_categories id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.product_categories ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: shopping_carts id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.shopping_carts ALTER COLUMN id SET DEFAULT nextval('public.shopping_carts_id_seq'::regclass);


--
-- Name: suppliers id; Type: DEFAULT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.suppliers ALTER COLUMN id SET DEFAULT nextval('public.suppliers_id_seq'::regclass);


--
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.addresses (id, country, city, zipcode, address) FROM stdin;
\.


--
-- Data for Name: line_items; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.line_items (id, product, quantity, cart) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.orders (id, personalinfo, shippingaddress, cart, billingaddress, sessionid) FROM stdin;
\.


--
-- Data for Name: personal_info; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.personal_info (id, firstname, lastname, email, phonenumber) FROM stdin;
\.


--
-- Data for Name: product_categories; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.product_categories (id, name, description, department) FROM stdin;
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.products (id, name, description, defaultprice, defaultcurrency, productCategoryID, supplierID, bhp, acceleration, modelyear, topspeed) FROM stdin;
\.


--
-- Data for Name: shopping_carts; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.shopping_carts (id, sessionid, totalprice) FROM stdin;
\.


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: zoli
--

COPY public.suppliers (id, name, description) FROM stdin;
1	name	description
2	name1	description1
3	name2	description2
4	name3	description3
6	name5	description5
7	name6	description6
\.


--
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.addresses_id_seq', 1, false);


--
-- Name: line_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.line_items_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: personal_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.personal_info_id_seq', 1, false);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.product_category_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.products_id_seq', 1, false);


--
-- Name: shopping_carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.shopping_carts_id_seq', 1, false);


--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: zoli
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 7, true);


--
-- Name: addresses addresses_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pk PRIMARY KEY (id);


--
-- Name: line_items line_items_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT line_items_pk PRIMARY KEY (id);


--
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- Name: personal_info personal_info_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.personal_info
    ADD CONSTRAINT personal_info_pk PRIMARY KEY (id);


--
-- Name: product_categories product_category_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.product_categories
    ADD CONSTRAINT product_category_pk PRIMARY KEY (id);


--
-- Name: products products_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- Name: shopping_carts shopping_carts_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.shopping_carts
    ADD CONSTRAINT shopping_carts_pk PRIMARY KEY (id);


--
-- Name: suppliers suppliers_pk; Type: CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.suppliers
    ADD CONSTRAINT suppliers_pk PRIMARY KEY (id);


--
-- Name: addresses_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX addresses_id_uindex ON public.addresses USING btree (id);


--
-- Name: line_items_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX line_items_id_uindex ON public.line_items USING btree (id);


--
-- Name: orders_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX orders_id_uindex ON public.orders USING btree (id);


--
-- Name: personal_info_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX personal_info_id_uindex ON public.personal_info USING btree (id);


--
-- Name: product_category_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX product_category_id_uindex ON public.product_categories USING btree (id);


--
-- Name: products_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX products_id_uindex ON public.products USING btree (id);


--
-- Name: shopping_carts_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX shopping_carts_id_uindex ON public.shopping_carts USING btree (id);


--
-- Name: suppliers_id_uindex; Type: INDEX; Schema: public; Owner: zoli
--

CREATE UNIQUE INDEX suppliers_id_uindex ON public.suppliers USING btree (id);


--
-- Name: orders billing_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT billing_address_fk FOREIGN KEY (billingaddress) REFERENCES public.addresses(id);


--
-- Name: line_items cart_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT cart_fk FOREIGN KEY (cart) REFERENCES public.shopping_carts(id);


--
-- Name: orders personal_info_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT personal_info_fk FOREIGN KEY (personalinfo) REFERENCES public.personal_info(id);


--
-- Name: line_items product_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT product_fk FOREIGN KEY (product) REFERENCES public.products(id);


--
-- Name: products productcategory_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT productcategory_fk FOREIGN KEY (productCategoryID) REFERENCES public.product_categories(id);


--
-- Name: orders shipping_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT shipping_address_fk FOREIGN KEY (shippingaddress) REFERENCES public.addresses(id);


--
-- Name: orders shopping_cart_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT shopping_cart_fk FOREIGN KEY (cart) REFERENCES public.shopping_carts(id);


--
-- Name: products supplier_fk; Type: FK CONSTRAINT; Schema: public; Owner: zoli
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT supplier_fk FOREIGN KEY (supplierID) REFERENCES public.suppliers(id);


--
-- PostgreSQL database dump complete
--

