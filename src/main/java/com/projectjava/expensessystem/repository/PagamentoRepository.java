package com.projectjava.expensessystem.repository;

import com.projectjava.expensessystem.entity.EmpenhoEntity;
import com.projectjava.expensessystem.entity.PagamentoEntity;
import com.projectjava.expensessystem.model.Empenho;
import com.projectjava.expensessystem.model.PagamentoPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, PagamentoPrimaryData> {
    List<PagamentoEntity> findPagamentoEntitiesByDtPagamentoLessThanEqualAndDtPagamentoGreaterThanEqualAndEmpenho(Date dataFim, Date dataInicio, EmpenhoEntity empenho);
    List<PagamentoEntity> findPagamentoEntitiesByEmpenho(EmpenhoEntity empenho);
}
