package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.DespesaEntity;
import com.projectjava.expensessystem.model.Despesa;

import java.util.List;

public interface DespesaService {
    Despesa newDespesa(Despesa despesa);

    List<Despesa> getAllDespesas();

    List<Despesa> searchDespesa(Despesa despesa);

    boolean deleteDespesa(int id);

    Despesa getDespesaById(int id);

    Despesa updateDespesa(int id, Despesa despesa);

    boolean hasEmpenho(DespesaEntity despesa);
}
