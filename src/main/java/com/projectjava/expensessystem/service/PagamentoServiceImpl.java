package com.projectjava.expensessystem.service;


import com.projectjava.expensessystem.entity.PagamentoEntity;
import com.projectjava.expensessystem.model.EmpenhoPrimaryData;
import com.projectjava.expensessystem.model.Pagamento;
import com.projectjava.expensessystem.model.PagamentoPrimaryData;
import com.projectjava.expensessystem.repository.EmpenhoRepository;
import com.projectjava.expensessystem.repository.PagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final EmpenhoRepository empenhoRepository;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository, EmpenhoRepository empenhoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.empenhoRepository = empenhoRepository;
    }

    @Override
    public Pagamento newPagamento(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        BeanUtils.copyProperties(pagamento, pagamentoEntity);
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(pagamento.getAnoPgtoEmpenho(),
                pagamento.getNrPgtoEmpenho());
        if (empenhoRepository.findById(empenhoPrimaryData).isEmpty()) {
            return null;
        }
        pagamentoEntity.setEmpenho(empenhoRepository.findById(empenhoPrimaryData).get());
        pagamentoRepository.save(pagamentoEntity);
        return pagamento;
    }

    @Override
    public List<Pagamento> getAllPagamentos() {
        List<PagamentoEntity> pagamentoEntities = pagamentoRepository.findAll();

        return pagamentoEntities
                .stream()
                .map(pagamentoEntity -> new Pagamento(
                        pagamentoEntity.getAnoPgto(),
                        pagamentoEntity.getNrPgto(),
                        pagamentoEntity.getEmpenho().getAnoEmpenho(),
                        pagamentoEntity.getEmpenho().getNrEmpenho(),
                        pagamentoEntity.getDtPagamento(),
                        pagamentoEntity.getValor(),
                        pagamentoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> findByPeriod(Date dataInicio, Date dataFim, int nrEmpenho, int anoEmpenho) {
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(anoEmpenho, nrEmpenho);
        if( empenhoRepository.findById(empenhoPrimaryData).isEmpty()){
            return null;
        }
        List<PagamentoEntity> pagamentoEntities = pagamentoRepository
                .findPagamentoEntitiesByDtPagamentoLessThanEqualAndDtPagamentoGreaterThanEqualAndEmpenho(dataFim, dataInicio, empenhoRepository.findById(empenhoPrimaryData).get());
        return pagamentoEntities
                .stream()
                .map(pagamentoEntity -> new Pagamento(
                        pagamentoEntity.getAnoPgto(),
                        pagamentoEntity.getNrPgto(),
                        pagamentoEntity.getEmpenho().getAnoEmpenho(),
                        pagamentoEntity.getEmpenho().getNrEmpenho(),
                        pagamentoEntity.getDtPagamento(),
                        pagamentoEntity.getValor(),
                        pagamentoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pagamento> FindByEmpenho(int nrEmpenho, int anoEmpenho) {
        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(anoEmpenho, nrEmpenho);
        if( empenhoRepository.findById(empenhoPrimaryData).isEmpty()){
            return null;
        }
        List<PagamentoEntity> pagamentoEntities = pagamentoRepository.findPagamentoEntitiesByEmpenho(empenhoRepository.findById(empenhoPrimaryData).get());
        return pagamentoEntities
                .stream()
                .map(pagamentoEntity -> new Pagamento(
                        pagamentoEntity.getAnoPgto(),
                        pagamentoEntity.getNrPgto(),
                        pagamentoEntity.getEmpenho().getAnoEmpenho(),
                        pagamentoEntity.getEmpenho().getNrEmpenho(),
                        pagamentoEntity.getDtPagamento(),
                        pagamentoEntity.getValor(),
                        pagamentoEntity.getObservacao()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Pagamento getPagamentoById(int id, int ano) {
        PagamentoPrimaryData pagamentoPrimaryData = new PagamentoPrimaryData(ano, id);
        PagamentoEntity pagamentoEntity = pagamentoRepository.findById(pagamentoPrimaryData).isEmpty() ?
                null : pagamentoRepository.findById(pagamentoPrimaryData).get();
        if (pagamentoEntity == null) {return null;}
        Pagamento pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoEntity, pagamento);
        pagamento.setAnoPgtoEmpenho(pagamentoEntity.getEmpenho().getAnoEmpenho());
        pagamento.setNrPgtoEmpenho(pagamentoEntity.getEmpenho().getNrEmpenho());
        return pagamento;
    }

    @Override
    public boolean deletePagamento(int id, int ano) {
        PagamentoPrimaryData pagamentoPrimaryData = new PagamentoPrimaryData(ano, id);
        PagamentoEntity pagamentoEntity = pagamentoRepository.findById(pagamentoPrimaryData).isEmpty() ?
                null : pagamentoRepository.findById(pagamentoPrimaryData).get();
        if (pagamentoEntity == null) {return false;}
        pagamentoRepository.delete(pagamentoEntity);
        return true;
    }

    @Override
    public Pagamento updatePagamento(int id, int ano, Pagamento pagamento) {
        PagamentoPrimaryData pagamentoPrimaryData = new PagamentoPrimaryData(ano, id);

        PagamentoEntity pagamentoEntity = pagamentoRepository.findById(pagamentoPrimaryData).isEmpty() ?
                null : pagamentoRepository.findById(pagamentoPrimaryData).get();

        if (pagamentoEntity == null) {return null;}

        EmpenhoPrimaryData empenhoPrimaryData = new EmpenhoPrimaryData(pagamento.getAnoPgtoEmpenho(),
                pagamento.getNrPgtoEmpenho());

        if (empenhoRepository.findById(empenhoPrimaryData).isEmpty()) { return null; }

        pagamentoEntity.setEmpenho(empenhoRepository.findById(empenhoPrimaryData).get());
        pagamentoEntity.setDtPagamento(pagamento.getDtPagamento());
        pagamentoEntity.setValor(pagamento.getValor());
        pagamentoEntity.setObservacao(pagamento.getObservacao());

        pagamentoRepository.save(pagamentoEntity);
        return pagamento;
    }
}
