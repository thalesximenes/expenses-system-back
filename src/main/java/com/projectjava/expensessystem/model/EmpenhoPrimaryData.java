package com.projectjava.expensessystem.model;

import java.io.Serializable;
import java.util.Objects;

public class EmpenhoPrimaryData implements Serializable {

    private int anoEmpenho;
    private int nrEmpenho;

    public EmpenhoPrimaryData() {
    }

    public EmpenhoPrimaryData(int anoEmpenho, int nrEmpenho) {
        this.anoEmpenho = anoEmpenho;
        this.nrEmpenho = nrEmpenho;
    }

    public int getAnoEmpenho() {
        return anoEmpenho;
    }

    public void setAnoEmpenho(int anoPgto) {
        this.anoEmpenho = anoPgto;
    }

    public int getNrEmpenho() {
        return nrEmpenho;
    }

    public void setNrEmpenho(int nrPgto) {
        this.nrEmpenho = nrPgto;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        EmpenhoPrimaryData empenhoPrimaryData = (EmpenhoPrimaryData) o;
        return Objects.equals(nrEmpenho, empenhoPrimaryData.nrEmpenho) &&
                Objects.equals(anoEmpenho, empenhoPrimaryData.anoEmpenho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrEmpenho, anoEmpenho);
    }

}


