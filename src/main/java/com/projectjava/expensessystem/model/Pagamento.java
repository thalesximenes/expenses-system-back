package com.projectjava.expensessystem.model;

import jakarta.annotation.Nullable;

import java.util.Date;

public class Pagamento {
    private int anoPgto;
    private Integer nrPgto;
    private int anoPgtoEmpenho;
    private Integer nrPgtoEmpenho;
    private Date dtPagamento;
    private float valor;
    private String observacao;

    public Pagamento() {
    }

    public Pagamento(int anoPgto, Integer nrPgto, int anoPgtoEmpenho, Integer nrPgtoEmpenho, Date dtPagamento, float valor, @Nullable String observacao) {
        this.anoPgto = anoPgto;
        this.nrPgto = nrPgto;
        this.anoPgtoEmpenho = anoPgtoEmpenho;
        this.nrPgtoEmpenho = nrPgtoEmpenho;
        this.dtPagamento = dtPagamento;
        this.valor = valor;
        this.observacao = observacao;
    }

    public int getAnoPgto() {
        return anoPgto;
    }

    public void setAnoPgto(int anoPgto) {
        this.anoPgto = anoPgto;
    }

    public Integer getNrPgto() {
        return nrPgto;
    }

    public void setNrPgto(Integer nrPgto) {
        this.nrPgto = nrPgto;
    }

    public int getAnoPgtoEmpenho() {
        return anoPgtoEmpenho;
    }

    public void setAnoPgtoEmpenho(int anoPgtoEmpenho) {
        this.anoPgtoEmpenho = anoPgtoEmpenho;
    }

    public Integer getNrPgtoEmpenho() {
        return nrPgtoEmpenho;
    }

    public void setNrPgtoEmpenho(Integer nrPgtoEmpenho) {
        this.nrPgtoEmpenho = nrPgtoEmpenho;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
