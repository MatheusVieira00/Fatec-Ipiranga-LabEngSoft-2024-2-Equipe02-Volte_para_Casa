package com.fatec.tg.Representante;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.entity.RepresentanteLogEntity;
import com.fatec.tg.erros.RepresentanteCadastrado;

@RestController
@CrossOrigin(origins = "*")
public class RepresentanteController {

    private final RepresentanteService service;

    @Autowired
    public RepresentanteController(RepresentanteService service) {
        this.service = service;
    }

    @GetMapping("/api/representante/operacao/id/{id}")
    public ResponseEntity<RepresentanteDTO> carregar(@PathVariable long id) {
        RepresentanteDTO obj = service.carregar(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/api/representante/operacao")
    public ResponseEntity<Map<String, String>> gravar(@RequestBody @Valid RepresentanteDTO obj) {
        try {
            String mensagem = service.gravar(obj);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", mensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta); 
        } catch (RepresentanteCadastrado e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro); 
        }
    }

    @PutMapping("/api/representante/operacao")
    public ResponseEntity<String> atualizar(@RequestBody RepresentanteDTO obj) {
        Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(obj.getEmail());
        if (representanteOpt.isPresent()) {
            RepresentanteEntity representante = representanteOpt.get();
            representante.setNome(obj.getNome());
            representante.setSenha(obj.getSenha());
            representante.setGenero(obj.getGenero());
            representante.setCpf(obj.getCpf());
            representante.setRg(obj.getRg());
            representante.setLogradouro(obj.getLogradouro());
            representante.setNumero(obj.getNumero());
            representante.setTelefone(obj.getTelefone());
            representante.setEstadoCivil(obj.getEstadoCivil());
            representante.setFoto(obj.getFoto());
            service.gravar(obj); 
            return ResponseEntity.ok("Cadastro atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Representante não encontrado.");
        }
    }

    @DeleteMapping("/api/representante/operacao/email/{email}")
    public ResponseEntity<String> removerPorEmail(@PathVariable String email) {
        try {
            String mensagem = service.removerPorEmail(email);
            return ResponseEntity.ok(mensagem);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/api/representantes")
    public ResponseEntity<List<RepresentanteEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(value = "/api/representante/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepresentanteEntity> fazerLogin(@RequestBody RepresentanteEntity dto) {
        Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(dto.getEmail());

        if (representanteOpt.isPresent()) {
            RepresentanteEntity representante = representanteOpt.get();

            if (representante.getSenha().equals(dto.getSenha())) {
                // Registro de log de login
                RepresentanteLogEntity log = new RepresentanteLogEntity();
                log.setRepresentanteId(representante.getIdRepresentante());
                log.setAcao("Login efetuado");
                log.setDataHora(LocalDateTime.now());
                service.logRepository.save(log); 

                return ResponseEntity.ok(representante); 
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
    }

    @GetMapping("/api/representante/operacao/email/{email}")
    public ResponseEntity<RepresentanteDTO> buscarRepresentantePorEmail(@PathVariable String email) {
        Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(email);
        if (representanteOpt.isPresent()) {
            RepresentanteEntity representante = representanteOpt.get();
            RepresentanteDTO dto = mapToDTO(representante);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private RepresentanteDTO mapToDTO(RepresentanteEntity representante) {
        RepresentanteDTO dto = new RepresentanteDTO();
        dto.setIdRepresentante(representante.getIdRepresentante());
        dto.setNome(representante.getNome());
        dto.setEmail(representante.getEmail());
        dto.setSenha(representante.getSenha());
        dto.setGenero(representante.getGenero());
        dto.setCpf(representante.getCpf());
        dto.setRg(representante.getRg());
        dto.setLogradouro(representante.getLogradouro());
        dto.setNumero(representante.getNumero());
        dto.setTelefone(representante.getTelefone());
        dto.setFoto(representante.getFoto());
        dto.setEstadoCivil(representante.getEstadoCivil());
        return dto;
    }

    @GetMapping("/api/representante/operacao/idoso/{idIdoso}")
    public ResponseEntity<RepresentanteDTO> buscarRepresentantePorIdIdoso(@PathVariable long idIdoso) {
        Optional<RepresentanteDTO> representanteOpt = service.buscarRepresentantePorIdIdoso(idIdoso);
        
        if (representanteOpt.isPresent()) {
            return ResponseEntity.ok(representanteOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
