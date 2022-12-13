package com.projectjava.expensessystem.model;

import jakarta.annotation.Nullable;

import java.util.Date;

public class Empenho {
    private int anoEmpenho;
    private Integer nrEmpenho;
    private int nrDespesa;
    private Date data;
    private float valor;
    private String observacao;

    public Empenho() {
    }

    public Empenho(int anoEmpenho, Integer nrEmpenho, int nrDespesa, Date data, float valor, @Nullable String observacao) {
        this.anoEmpenho = anoEmpenho;
        this.nrEmpenho = nrEmpenho;
        this.nrDespesa = nrDespesa;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
    }

    public int getAnoEmpenho() {
        return anoEmpenho;
    }

    public void setAnoEmpenho(int anoEmpenho) {
        this.anoEmpenho = anoEmpenho;
    }

    public Integer getNrEmpenho() {
        return nrEmpenho;
    }

    public void setNrEmpenho(Integer nrEmpenho) {
        this.nrEmpenho = nrEmpenho;
    }

    public int getNrDespesa() {
        return nrDespesa;
    }

    public void setNrDespesa(int nrDespesa) {
        this.nrDespesa = nrDespesa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
