package com.projectjava.expensessystem.repository;


import com.projectjava.expensessystem.entity.DespesaEntity;
import com.projectjava.expensessystem.entity.EmpenhoEntity;
import com.projectjava.expensessystem.model.EmpenhoPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, EmpenhoPrimaryData> {
    List<EmpenhoEntity> findEmpenhoEntitiesByDataLessThanEqualAndDataGreaterThanEqualAndDespesa(Date dataFim, Date dataInicio, DespesaEntity despesa);
    List<EmpenhoEntity> findEmpenhoEntitiesByDespesa(DespesaEntity despesa);
}
