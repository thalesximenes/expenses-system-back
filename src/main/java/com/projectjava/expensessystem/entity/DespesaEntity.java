package com.projectjava.expensessystem.entity;

import com.projectjava.expensessystem.model.Situacao;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="despesa")
public class DespesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nrProtocolo;
    private String tipo;
    private Date dtProtocolo;
    private Date dtVencimento;
    private String credor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Situacao situacaoDespesa;
    private float valor;

    public DespesaEntity() {
    }

    public DespesaEntity(int nrProtocolo, String tipo, Date dtProtocolo, Date dtVencimento, String credor, String descricao, Situacao situacaoDespesa, float valor) {
        this.nrProtocolo = nrProtocolo;
        this.tipo = tipo;
        this.dtProtocolo = dtProtocolo;
        this.dtVencimento = dtVencimento;
        this.credor = credor;
        this.descricao = descricao;
        this.situacaoDespesa = situacaoDespesa;
        this.valor = valor;
    }

    public int getNrProtocolo() {
        return nrProtocolo;
    }

    public void setNrProtocolo(int nrProtocolo) {
        this.nrProtocolo = nrProtocolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDtProtocolo() {
        return dtProtocolo;
    }

    public void setDtProtocolo(Date dtProtocolo) {
        this.dtProtocolo = dtProtocolo;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public String getCredor() {
        return credor;
    }

    public void setCredor(String credor) {
        this.credor = credor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacaoDespesa() {
        return situacaoDespesa;
    }

    public void setSituacaoDespesa(Situacao situacaoDespesa) {
        this.situacaoDespesa = situacaoDespesa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
