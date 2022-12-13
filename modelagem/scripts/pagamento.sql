-- Table: public.pagamento

-- DROP TABLE IF EXISTS public.pagamento;

CREATE TABLE IF NOT EXISTS public.pagamento
(
    ano_pgto integer NOT NULL,
    nr_pgto integer NOT NULL,
    dt_pagamento timestamp(6) without time zone,
    observacao character varying(255) COLLATE pg_catalog."default",
    valor real NOT NULL,
    empenho_ano_empenho integer,
    empenho_nr_empenho integer,
    CONSTRAINT pagamento_pkey PRIMARY KEY (ano_pgto, nr_pgto),
    CONSTRAINT fkjhmhid9b995b6kuch6q6nm1v0 FOREIGN KEY (empenho_ano_empenho, empenho_nr_empenho)
        REFERENCES public.empenho (ano_empenho, nr_empenho) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pagamento
    OWNER to postgres;