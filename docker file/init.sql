-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial NOT NULL,
    age integer NOT NULL DEFAULT 0,
    nationality character varying(2) COLLATE pg_catalog."default",
    salary bigint NOT NULL DEFAULT 1000,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;



-- Table: public.rulesetmodel

-- DROP TABLE IF EXISTS public.rulesetmodel;

CREATE TABLE IF NOT EXISTS public.rulesetmodel
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    set_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT rulesetmodel_pkey PRIMARY KEY (id),
    CONSTRAINT uk22bfgxu1xnapf1yv71395n4hk UNIQUE (set_name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rulesetmodel
    OWNER to postgres;


-- Table: public.rules

-- DROP TABLE IF EXISTS public.rules;

CREATE TABLE IF NOT EXISTS public.rules
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    rule_type character varying(31) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    privileges character varying(255) COLLATE pg_catalog."default",
    nationality character varying(255) COLLATE pg_catalog."default",
    field character varying(255) COLLATE pg_catalog."default" NOT NULL,
    foreignkey integer,
    CONSTRAINT rules_pkey PRIMARY KEY (id),
    CONSTRAINT rules_name_key UNIQUE (name),
    CONSTRAINT rules_foreignkey_fkey FOREIGN KEY (foreignkey)
        REFERENCES public.rulesetmodel (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rules
    OWNER to postgres;