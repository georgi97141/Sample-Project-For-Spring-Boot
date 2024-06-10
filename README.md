Open folder PicturesInstructionsAndRunningFile.
There you can find the jar file.
There are several screenshots showing sample requests and some file containing sample requests as text.
There is script for creating the database and script for creating the user table.
TODO: add Jakarta Entities, add JavaDoc,  save the rules in DB, create a table for ruleset with 1 ruleset to many rules, this was done but added to another repo since I have some stuff to clear and is unstable :).


Doing some updates,added entities and saving rules to the DB, still unstable but you can check code that is working, no integration or unittest but I confirmed it works.
Link to the repo where the updates happen:
https://github.com/georgi97141/MockRepo


Note:should have created a new branch but I got confused a bit and wasn't sure if it would be visible to non-project collaborators, since I was working recently with SVN.


Note: 
There I added new tables there:
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
    CONSTRAINT ukasd57j4i2nr2043y2jvq4m1fh UNIQUE (name),
    CONSTRAINT rules_foreignkey_fkey FOREIGN KEY (foreignkey)
        REFERENCES public.rulesetmodel (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rules
    OWNER to postgres;


---------- ANOTHER TABLE ---------------

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

----------------- NOTES --------------------

![image](https://github.com/georgi97141/zettaOnline/assets/79843551/27e3ef3b-30d6-41d4-9cee-a0af28402e26)

![image](https://github.com/georgi97141/zettaOnline/assets/79843551/92d0cb82-e803-4ec7-96e1-ca1302802ae1)









