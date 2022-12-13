package com.projectjava.expensessystem.controller;

import com.projectjava.expensessystem.entity.PagamentoEntity;
import com.projectjava.expensessystem.model.Pagamento;
import com.projectjava.expensessystem.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/pagamentos")
    public ResponseEntity savePagamento(@RequestBody Pagamento pagamento){
        Pagamento pagamentoNew = null;
        pagamentoNew = pagamentoService.newPagamento(pagamento);
        if (pagamentoNew == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Empenho nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(pagamentoNew);
    }

    @GetMapping("/pagamentos")
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.getAllPagamentos();
    }

    @GetMapping("/pagamentos/findByPeriod/{dataInicio}/{dataFim}/{nrEmpenho}/{anoEmpenho}")
    public List<Pagamento> findByPeriod(@PathVariable("dataInicio") String dataInicio, @PathVariable("dataFim") String dataFim,@PathVariable int nrEmpenho,@PathVariable int anoEmpenho) throws ParseException {
        return pagamentoService.findByPeriod(new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio),
                new SimpleDateFormat("yyyy-MM-dd").parse(dataFim),
                nrEmpenho,
                anoEmpenho);
    }

    @GetMapping("/pagamentos/findByEmpenho/{nrPgto}/{anoPgto}")
    public List<Pagamento> findByEmpenho(@PathVariable("nrPgto") int nrPgto, @PathVariable("anoPgto") int anoPgto){
        return pagamentoService.FindByEmpenho(nrPgto, anoPgto);
    }

    @GetMapping("/pagamentos/{id}/{ano}")
    public ResponseEntity getPagamentoById(@PathVariable("id") int id, @PathVariable("ano") int ano) {
        Pagamento pagamento = null;
        pagamento = pagamentoService.getPagamentoById(id, ano);
        if (pagamento == null ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/pagamentos/{id}/{ano}")
    public ResponseEntity<Map<String,String>> deletePagamento(@PathVariable("id") int id, @PathVariable("ano") int ano ) {
        boolean deleted = false;
        deleted = pagamentoService.deletePagamento(id, ano);
        Map<String,String> response = new HashMap<>();

        if (!deleted) {
            response.put("message","Id nao encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("deleted", "True");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/pagamentos/{id}/{ano}")
    public ResponseEntity updatePagamento(@PathVariable("id")int id, @PathVariable("ano") int ano, @RequestBody Pagamento pagamento){
        pagamento = pagamentoService.updatePagamento(id, ano, pagamento);
        if (pagamento == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(pagamento);
    }
}
