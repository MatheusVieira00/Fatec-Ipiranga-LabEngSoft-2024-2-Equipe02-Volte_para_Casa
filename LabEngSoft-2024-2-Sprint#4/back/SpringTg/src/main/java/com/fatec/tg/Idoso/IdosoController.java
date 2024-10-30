package com.fatec.tg.Idoso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tg.erros.IdosoCadastrado;

@RestController
@CrossOrigin(origins = "*")
public class IdosoController {

    private final IdosoService service;

    @Autowired
    public IdosoController(IdosoService service) {
        this.service = service;
    }

    
    @GetMapping("/api/idoso/operacao/id/{id}")
    public ResponseEntity<IdosoDTO> carregar(@PathVariable long id) {
        IdosoDTO obj = service.carregar(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/api/idoso/operacao/idRepresentante/{representante}")
    public ResponseEntity<IdosoDTO> carregarIdRepresentante(@PathVariable long representante) {
        IdosoDTO obj = service.carregarIdRepresentante(representante);
        return ResponseEntity.ok(obj);
    }

    
    @PostMapping("/api/idoso/operacao")
    public ResponseEntity<Map<String, String>> gravar(@RequestBody @Valid IdosoDTO obj) {
        try {
            String message = service.gravar(obj);
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (IdosoCadastrado e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    
    @PutMapping("/api/idoso/operacao")
    public ResponseEntity<Map<String, String>> atualizar(@RequestBody @Valid IdosoDTO obj) {
        try {
            String message = service.gravar(obj);
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (IdosoCadastrado e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    
    @DeleteMapping("/api/idoso/operacao/id/{id}")
    public ResponseEntity<Map<String, String>> removerPorId(@PathVariable long id) {
        try {
            String message = service.removerPorId(id);
            return ResponseEntity.ok(Map.of("message", message));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    
    @GetMapping("/api/idoso/operacao")
    public ResponseEntity<List<IdosoDTO>> listarTodos() {
        List<IdosoDTO> list = service.listarTodos(); // Implementar esse método no serviço
        return ResponseEntity.ok(list);
    }

    @GetMapping("/api/idoso/operacao/detalhes/{id}")
    public ResponseEntity<IdosoDTO> buscarIdosoPorId(@PathVariable long id) {
        try {
            IdosoDTO idoso = service.carregar(id);
            return ResponseEntity.ok(idoso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
