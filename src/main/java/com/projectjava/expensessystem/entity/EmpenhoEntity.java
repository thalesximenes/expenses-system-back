package com.projectjava.expensessystem.entity;

import com.projectjava.expensessystem.model.EmpenhoPrimaryData;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Empenho")
@IdClass( EmpenhoPrimaryData.class )
public class EmpenhoEntity {
    @Id
    private int anoEmpenho;

    @Id
    @GeneratedValue
    private Integer nrEmpenho;

    @ManyToOne
    private DespesaEntity despesa;
    private Date data;
    private float valor;
    private String observacao;

    public EmpenhoEntity() {
    }

    public EmpenhoEntity(int anoEmpenho, Integer nrEmpenho, DespesaEntity despesa, Date data, float valor, @Nullable String observacao) {
        this.anoEmpenho = anoEmpenho;
        this.nrEmpenho = nrEmpenho;
        this.despesa = despesa;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
    }

    public int getAnoEmpenho() {
        return anoEmpenho;
    }

    public void setAnoEmpenho(int anoPgto) {
        this.anoEmpenho = anoPgto;
    }

    public Integer getNrEmpenho() {
        return nrEmpenho;
    }

    public void setNrEmpenho(Integer nrPgto) {
        this.nrEmpenho = nrPgto;
    }

    public DespesaEntity getDespesa() {
        return despesa;
    }

    public void setDespesa(DespesaEntity despesa) {
        this.despesa = despesa;
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


