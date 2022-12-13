-- Table: public.empenho

-- DROP TABLE IF EXISTS public.empenho;

CREATE TABLE IF NOT EXISTS public.empenho
(
    ano_empenho integer NOT NULL,
    nr_empenho integer NOT NULL,
    data timestamp(6) without time zone,
    observacao character varying(255) COLLATE pg_catalog."default",
    valor real NOT NULL,
    despesa_nr_protocolo integer,
    CONSTRAINT empenho_pkey PRIMARY KEY (ano_empenho, nr_empenho),
    CONSTRAINT fkasvvja9qgoebgg1i8914tt7ft FOREIGN KEY (despesa_nr_protocolo)
        REFERENCES public.despesa (nr_protocolo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.empenho
    OWNER to postgres;