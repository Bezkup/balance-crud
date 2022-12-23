CREATE TABLE public.users(
     user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
     username varchar(50) NOT NULL,
     password varchar(255) NOT NULL,
     firstName varchar(255) NOT NULL,
     lastName varchar(255) NOT NULL,
     email varchar(255) NOT NULL,
)
