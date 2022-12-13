package com.projectjava.expensessystem.controller;

import com.projectjava.expensessystem.model.Empenho;
import com.projectjava.expensessystem.service.EmpenhoService;
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
public class EmpenhoController {
    private final EmpenhoService empenhoService;

    public EmpenhoController(EmpenhoService empenhoService) {
        this.empenhoService = empenhoService;
    }

    @PostMapping("/empenhos")
    public ResponseEntity saveEmpenho(@RequestBody Empenho empenho){
        Empenho empenhoNew = null;
        empenhoNew = empenhoService.newEmpenho(empenho);
        if (empenhoNew == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Despesa nao encontrada");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(empenhoNew);
    }

    @GetMapping("/empenhos")
    public List<Empenho> getAllEmpenhos() {
        return empenhoService.getAllEmpenhos();
    }

    @GetMapping("/empenhos/findByPeriod/{dataInicio}/{dataFim}/{nrDespesa}")
    public List<Empenho> findByPeriod(@PathVariable("dataInicio") String dataInicio,@PathVariable("dataFim") String dataFim, @PathVariable("nrDespesa") int nrDespesa) throws ParseException {
        return empenhoService.findByPeriod(new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio),
                new SimpleDateFormat("yyyy-MM-dd").parse(dataFim),
                nrDespesa);
    }

    @GetMapping("/empenhos/findByDespesa/{nrDespesa}")
    public List<Empenho> findByDespesa(@PathVariable("nrDespesa") int nrDespesa){
        return empenhoService.FindByDespesa(nrDespesa);
    }

    @GetMapping("/empenhos/{id}/{ano}")
    public ResponseEntity getUserById(@PathVariable("id") int id, @PathVariable("ano") int ano) {
        Empenho empenho = null;

        empenho = empenhoService.getEmpenhoById(id, ano);
        if (empenho == null ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(empenho);
    }

    @DeleteMapping("/empenhos/{id}/{ano}")
    public ResponseEntity<Map<String,String>> deleteEmpenho(@PathVariable("id") int id, @PathVariable("ano") int ano ) {
        boolean deleted = false;
        deleted = empenhoService.deleteEmpenho(id, ano);
        Map<String,String> response = new HashMap<>();

        if (!deleted) {
            response.put("message","Id nao encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("deleted", "True");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/empenhos/{id}/{ano}")
    public ResponseEntity updateUser(@PathVariable("id")int id, @PathVariable("ano") int ano, @RequestBody Empenho empenho){
        empenho = empenhoService.updateEmpenho(id, ano, empenho);
        if (empenho == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(empenho);
    }
}
