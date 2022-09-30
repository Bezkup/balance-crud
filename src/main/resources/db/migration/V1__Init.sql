-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
    category_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    category text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (category_id)
)

TABLESPACE pg_default;

ALTER TABLE public.category
    OWNER to postgres;

-- Table: public.transactions

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    transaction_type text COLLATE pg_catalog."default" NOT NULL,
    amount integer NOT NULL,
    transaction_date date NOT NULL,
    description text COLLATE pg_catalog."default",
    category_id integer,
    CONSTRAINT transactions_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category FOREIGN KEY (category_id)
        REFERENCES public.category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT transactions_transaction_type_check CHECK (transaction_type = ANY (ARRAY['IN'::text, 'OUT'::text]))
)

TABLESPACE pg_default;

ALTER TABLE public.transactions
    OWNER to postgres;
