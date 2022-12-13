package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.PagamentoEntity;
import com.projectjava.expensessystem.model.Pagamento;

import java.util.Date;
import java.util.List;

public interface PagamentoService {
    Pagamento newPagamento(Pagamento pagamento);

    List<Pagamento> getAllPagamentos();

    List<Pagamento> findByPeriod(Date dataInicio, Date dataFim, int nrEmpenho, int anoEmpenho);

    List<Pagamento> FindByEmpenho(int nrPgto, int anoPgto);

    Pagamento getPagamentoById(int id, int ano);

    boolean deletePagamento(int id, int ano);

    Pagamento updatePagamento(int id, int ano, Pagamento pagamento);
}
