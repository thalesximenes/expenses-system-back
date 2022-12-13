package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.DespesaEntity;
import com.projectjava.expensessystem.model.Despesa;
import com.projectjava.expensessystem.repository.DespesaRepository;
import com.projectjava.expensessystem.repository.EmpenhoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaServiceImpl implements DespesaService{

    private final DespesaRepository despesaRepository;
    private final EmpenhoRepository empenhoRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository, EmpenhoRepository empenhoRepository) {
        this.despesaRepository = despesaRepository;
        this.empenhoRepository = empenhoRepository;
    }

    @Override
    public Despesa newDespesa(Despesa despesa){
        DespesaEntity despesaEntity = new DespesaEntity();
        BeanUtils.copyProperties(despesa, despesaEntity);
        despesaRepository.save(despesaEntity);
        return despesa;
    }

    @Override
    public List<Despesa> getAllDespesas() {
        List<DespesaEntity> despesaEntities = despesaRepository.findAll();

        return despesaEntities
                        .stream()
                        .map(despesaEntity -> new Despesa(
                                despesaEntity.getNrProtocolo(),
                                despesaEntity.getTipo(),
                                despesaEntity.getDtProtocolo(),
                                despesaEntity.getDtVencimento(),
                                despesaEntity.getCredor(),
                                despesaEntity.getDescricao(),
                                despesaEntity.getSituacaoDespesa(),
                                despesaEntity.getValor()
                            ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Despesa> searchDespesa(Despesa despesa) {
        List<DespesaEntity> despesaEntities = null;

        int caso = 0;
        caso += despesa.getTipo() == null || despesa.getTipo() == ""? 0:1;
        caso += despesa.getCredor() == null || despesa.getCredor() == "" ? 0:2;
        caso += despesa.getDtProtocolo() == null  ? 0:4;

        switch (caso) {
            case 0 -> despesaEntities = despesaRepository.findAll();
            case 1 -> despesaEntities = despesaRepository.findDespesaByTipo(despesa.getTipo());
            case 2 -> despesaEntities = despesaRepository.findDespesaByCredor(despesa.getCredor());
            case 3 ->
                    despesaEntities = despesaRepository.findDespesaByTipoAndCredor(despesa.getTipo(), despesa.getCredor());
            case 4 -> despesaEntities = despesaRepository.findDespesaByDtProtocolo(despesa.getDtProtocolo());
            case 5 ->
                    despesaEntities = despesaRepository.findDespesaByTipoAndDtProtocolo(despesa.getTipo(), despesa.getDtProtocolo());
            case 6 ->
                    despesaEntities = despesaRepository.findDespesaByCredorAndDtProtocolo(despesa.getCredor(), despesa.getDtProtocolo());
            case 7 ->
                    despesaEntities = despesaRepository.findDespesaByTipoAndCredorAndDtProtocolo(despesa.getTipo(), despesa.getCredor(), despesa.getDtProtocolo());
        }

        return despesaEntities
                .stream()
                .map(despesaEntity -> new Despesa(
                        despesaEntity.getNrProtocolo(),
                        despesaEntity.getTipo(),
                        despesaEntity.getDtProtocolo(),
                        despesaEntity.getDtVencimento(),
                        despesaEntity.getCredor(),
                        despesaEntity.getDescricao(),
                        despesaEntity.getSituacaoDespesa(),
                        despesaEntity.getValor()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteDespesa(int id) {
        DespesaEntity despesa = despesaRepository.findById(id).isEmpty() ?
                null : despesaRepository.findById(id).get();

        if (despesa == null) {
            return false;
        }

        if (hasEmpenho(despesa)){
            return false;
        }

        despesaRepository.delete(despesa);
        return true;
    }

    @Override
    public Despesa getDespesaById(int id) {
        DespesaEntity despesaEntity = despesaRepository.findById(id).isEmpty() ? null : despesaRepository.findById(id).get();
        if (despesaEntity == null) {return null;}
        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(despesaEntity, despesa);
        return despesa;
    }

    @Override
    public Despesa updateDespesa(int id, Despesa despesa) {
        DespesaEntity despesaEntity = despesaRepository.findById(id).isEmpty() ? null :  despesaRepository.findById(id).get();
        if (despesaEntity == null) {
            return null;
        }
        despesaEntity.setTipo(despesa.getTipo());
        despesaEntity.setDtProtocolo(despesa.getDtProtocolo());
        despesaEntity.setDtVencimento(despesa.getDtVencimento());
        despesaEntity.setCredor(despesa.getCredor());
        despesaEntity.setDescricao(despesa.getDescricao());
        despesaEntity.setSituacaoDespesa(despesa.getSituacaoDespesa());
        despesaEntity.setValor(despesa.getValor());

        despesaRepository.save(despesaEntity);
        return despesa;
    }

    @Override
    public boolean hasEmpenho(DespesaEntity despesa) {
        return !empenhoRepository.findEmpenhoEntitiesByDespesa(despesa).isEmpty();
    }
}
