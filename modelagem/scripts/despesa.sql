-- Table: public.despesa

-- DROP TABLE IF EXISTS public.despesa;

CREATE TABLE IF NOT EXISTS public.despesa
(
    nr_protocolo integer NOT NULL DEFAULT nextval('despesa_nr_protocolo_seq'::regclass),
    credor character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    dt_protocolo timestamp(6) without time zone,
    dt_vencimento timestamp(6) without time zone,
    situacao_despesa character varying(255) COLLATE pg_catalog."default",
    tipo character varying(255) COLLATE pg_catalog."default",
    valor real NOT NULL,
    CONSTRAINT despesa_pkey PRIMARY KEY (nr_protocolo)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.despesa
    OWNER to postgres;