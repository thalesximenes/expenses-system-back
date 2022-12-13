package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.EmpenhoEntity;
import com.projectjava.expensessystem.model.Empenho;

import java.util.Date;
import java.util.List;

public interface EmpenhoService {
    Empenho newEmpenho(Empenho empenho);

    List<Empenho> getAllEmpenhos();

    Empenho getEmpenhoById(int id, int ano);

    boolean deleteEmpenho(int id, int ano);

    Empenho updateEmpenho(int id, int ano, Empenho empenho);

    List<Empenho> findByPeriod(Date dataInicio, Date dataFim, int nrDespesa);

    List<Empenho> FindByDespesa(Integer nrDespesa);

    Boolean hasPagamento(EmpenhoEntity empenho);
}
