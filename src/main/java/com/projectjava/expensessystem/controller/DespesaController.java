package com.projectjava.expensessystem.controller;

import com.projectjava.expensessystem.model.Despesa;
import com.projectjava.expensessystem.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class DespesaController {
    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) { this.despesaService = despesaService; }

    @PostMapping("/despesas")
    public ResponseEntity saveDespesa(@RequestBody Despesa despesa){
        Despesa despesaNew = null;
        despesaNew = despesaService.newDespesa(despesa);
        return ResponseEntity.ok(despesaNew);
    }

    @GetMapping("/despesas")
    public List<Despesa> getAllDespesas() {
        return despesaService.getAllDespesas();
    }

    @PostMapping("/despesas/busca")
    public List<Despesa> searchDespesa(@RequestBody Despesa despesa) {
        return despesaService.searchDespesa(despesa);
    }

    @GetMapping("/despesas/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        Despesa despesa = null;
        despesa = despesaService.getDespesaById(id);
        if (despesa == null ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("/despesas/{id}")
    public ResponseEntity<Map<String,String>> deleteDespesa(@PathVariable("id") int id ) {
        boolean deleted = false;
        deleted = despesaService.deleteDespesa(id);
        Map<String,String> response = new HashMap<>();

        if (!deleted) {
            response.put("message","Id nao encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("deleted", "True");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/despesas/{id}")
    public ResponseEntity updateUser(@PathVariable("id") int id, @RequestBody Despesa despesa){
        despesa = despesaService.updateDespesa(id, despesa);
        if (despesa == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(despesa);
    }
}
