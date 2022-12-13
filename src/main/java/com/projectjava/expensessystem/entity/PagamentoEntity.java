package com.projectjava.expensessystem.entity;

import com.projectjava.expensessystem.model.PagamentoPrimaryData;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Pagamento")
@IdClass( PagamentoPrimaryData.class )
public class PagamentoEntity {
    @Id
    private int anoPgto;
    @Id
    @GeneratedValue
    private Integer nrPgto;
    @ManyToOne
    private EmpenhoEntity empenho;
    private Date dtPagamento;
    private float valor;
    private String observacao;

    public PagamentoEntity() {
    }

    public PagamentoEntity(int anoPgto, Integer nrPgto, EmpenhoEntity empenho, Date dtPagamento, float valor, String observacao) {
        this.anoPgto = anoPgto;
        this.nrPgto = nrPgto;
        this.empenho = empenho;
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

    public EmpenhoEntity getEmpenho() {
        return empenho;
    }

    public void setEmpenho(EmpenhoEntity empenho) {
        this.empenho = empenho;
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
