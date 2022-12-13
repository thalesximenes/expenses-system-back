package com.projectjava.expensessystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class PagamentoPrimaryData implements Serializable {

    private int anoPgto;
    private int nrPgto;

    public PagamentoPrimaryData() {
    }

    public PagamentoPrimaryData(int anoPgto, int nrPgto) {
        this.anoPgto = anoPgto;
        this.nrPgto = nrPgto;
    }

    public int getAnoPgto() {
        return anoPgto;
    }

    public void setAnoPgto(int anoPgto) {
        this.anoPgto = anoPgto;
    }

    public int getNrPgto() {
        return nrPgto;
    }

    public void setNrPgto(int nrPgto) {
        this.nrPgto = nrPgto;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PagamentoPrimaryData pagamentoPrimaryData = (PagamentoPrimaryData) o;
        return Objects.equals( nrPgto, pagamentoPrimaryData.nrPgto ) &&
                Objects.equals( anoPgto, pagamentoPrimaryData.anoPgto );
    }

    @Override
    public int hashCode() {
        return Objects.hash( nrPgto, anoPgto );
    }

}
