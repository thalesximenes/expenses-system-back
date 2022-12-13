package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.EmpenhoEntity;
import com.projectjava.expensessystem.model.Empenho;
import com.projectjava.expensessystem.model.EmpenhoPrimaryData;
import com.projectjava.expensessystem.repository.DespesaRepository;
import com.projectjava.expensessystem.repository.EmpenhoRepository;
import com.projectjava.expensessystem.repository.PagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpenhoServiceImpl implements EmpenhoService{

    private final EmpenhoRepository empenhoRepository;
    private final DespesaRepository despesaRepository;
    private final PagamentoRepository pagamentoRepository;

    public EmpenhoServiceImpl(EmpenhoRepository empenhoRepository, DespesaRepository despesaRepository, PagamentoRepository pagamentoRepository) {
        this.empenhoRepository = empenhoRepository;
        this.despesaRepository = despesaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Empenho newEmpenho(Empenho empenho) {
        EmpenhoEntity empenhoEntity = new EmpenhoEntity();
        BeanUtils.copyProperties(empenho, empenhoEntity);
        if (despesaRepository.findById(empenho.getNrDespesa()).isEmpty()) {
            return null;
        }
        empenhoEntity.setDespesa(despesaRepository.findById(empenho.getNrDespesa()).get());
        empenhoEntity.setNrEmpenho(null);
        empenhoRepository.save(empenhoEntity);
        return empenho;
    }

    @Override
    public List<Empenho> getAllEmpenhos() {
        List<EmpenhoEntity> empenhoEntities = empenhoRepository.findAll();

        return empenhoEntities
                .stream()
                .map(empenhoEntity -> new Empenho(
                        empenhoEntity.getAnoEmpenho(),
                        empenhoEntity.getNrEmpenho(),
                        empenhoEntity.getDespesa().getNrProtocolo(),
                        empenhoEntity.getData(),
                        empenhoEntity.getValor(),
                        empenhoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Empenho getEmpenhoById(int id, int ano) {
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(ano, id);
        EmpenhoEntity empenhoEntity = empenhoRepository.findById(empenhoPrimaryData).isEmpty() ? null : empenhoRepository.findById(empenhoPrimaryData).get();
        if (empenhoEntity == null) {return null;}
        Empenho empenho = new Empenho();
        BeanUtils.copyProperties(empenhoEntity, empenho);
        empenho.setNrDespesa(empenhoEntity.getDespesa().getNrProtocolo());
        return empenho;
    }

    @Override
    public boolean deleteEmpenho(int id, int ano) {
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(ano, id);
        EmpenhoEntity empenhoEntity = empenhoRepository.findById(empenhoPrimaryData).isEmpty() ? null : empenhoRepository.findById(empenhoPrimaryData).get();
        if (empenhoEntity == null) {return false;}

        if(hasPagamento(empenhoEntity)){
            return false;
        }

        empenhoRepository.delete(empenhoEntity);
        return true;
    }

    @Override
    public Empenho updateEmpenho(int id, int ano, Empenho empenho) {
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(ano, id);
        EmpenhoEntity empenhoEntity = empenhoRepository.findById(empenhoPrimaryData).isEmpty() ? null :  empenhoRepository.findById(empenhoPrimaryData).get();
        if (empenhoEntity == null) {
            return null;
        }

        if (despesaRepository.findById(empenho.getNrDespesa()).isEmpty()) {
            return null;
        }
        empenhoEntity.setDespesa(despesaRepository.findById(empenho.getNrDespesa()).get());
        empenhoEntity.setData(empenho.getData());
        empenhoEntity.setValor(empenho.getValor());
        empenhoEntity.setObservacao(empenho.getObservacao());

        empenhoRepository.save(empenhoEntity);
        return empenho;
    }

    @Override
    public List<Empenho> findByPeriod(Date dataInicio, Date dataFim, int nrDespesa) {
        if( despesaRepository.findById(nrDespesa).isEmpty()){
            return null;
        }
        List<EmpenhoEntity> empenhoEntities = empenhoRepository.findEmpenhoEntitiesByDataLessThanEqualAndDataGreaterThanEqualAndDespesa(dataFim, dataInicio, despesaRepository.findById(nrDespesa).get());
        return empenhoEntities
                .stream()
                .map(empenhoEntity -> new Empenho(
                        empenhoEntity.getAnoEmpenho(),
                        empenhoEntity.getNrEmpenho(),
                        empenhoEntity.getDespesa().getNrProtocolo(),
                        empenhoEntity.getData(),
                        empenhoEntity.getValor(),
                        empenhoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Empenho> FindByDespesa(Integer nrDespesa) {
        if( despesaRepository.findById(nrDespesa).isEmpty()){
            return null;
        }
        List<EmpenhoEntity> empenhoEntities = empenhoRepository.findEmpenhoEntitiesByDespesa(despesaRepository.findById(nrDespesa).get());
        return empenhoEntities
                .stream()
                .map(empenhoEntity -> new Empenho(
                        empenhoEntity.getAnoEmpenho(),
                        empenhoEntity.getNrEmpenho(),
                        empenhoEntity.getDespesa().getNrProtocolo(),
                        empenhoEntity.getData(),
                        empenhoEntity.getValor(),
                        empenhoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean hasPagamento(EmpenhoEntity empenho) {
        return !pagamentoRepository.findPagamentoEntitiesByEmpenho(empenho).isEmpty();
    }
}
