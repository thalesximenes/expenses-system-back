package com.projectjava.expensessystem.repository;

import com.projectjava.expensessystem.entity.DespesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Integer> {
    List<DespesaEntity> findDespesaByTipoAndCredorAndDtProtocolo(String tipo, String credor, Date data);
    List<DespesaEntity> findDespesaByCredorAndDtProtocolo(String credor, Date data);
    List<DespesaEntity> findDespesaByTipoAndDtProtocolo(String tipo, Date data);
    List<DespesaEntity> findDespesaByTipoAndCredor(String tipo, String credor);
    List<DespesaEntity> findDespesaByDtProtocolo(Date data);
    List<DespesaEntity> findDespesaByCredor(String credor);
    List<DespesaEntity> findDespesaByTipo(String tipo);
}
