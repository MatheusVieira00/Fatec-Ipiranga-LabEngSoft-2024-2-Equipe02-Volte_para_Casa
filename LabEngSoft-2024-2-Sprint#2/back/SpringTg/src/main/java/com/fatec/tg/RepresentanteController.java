package com.fatec.tg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.erros.RepresentanteCadastrado;
import com.fatec.tg.repository.RepresentanteRepository;



@RestController
@CrossOrigin( origins = "*" )
public class RepresentanteController {

    private final RepresentanteService service;

    @Autowired
    private RepresentanteRepository repository;

    // Construtor manual para substituir @RequiredArgsConstructor
    @Autowired
    public RepresentanteController(RepresentanteService service) {
        this.service = service;
    }

   
    @GetMapping("/api/representante/operacao/id/{id}")
    public ResponseEntity<RepresentanteDTO> carregar(@PathVariable long id) {
        RepresentanteDTO obj = service.carregar(id);
        return ResponseEntity.ok(obj);
    }
    

    // @GetMapping("/api/representante/operacao/{id}")
    // public ResponseEntity<RepresentanteDTO> carregar(@PathVariable long id){
    //     RepresentanteDTO obj = service.carregar(id);
    //     return ResponseEntity.ok(obj);
    // }

@PostMapping("/api/representante/operacao")
    public ResponseEntity<Map<String, String>> gravar(@RequestBody @Valid RepresentanteDTO obj) {
        try {
            String mensagem = service.gravar(obj);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", mensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta); // Retorna um JSON
        } catch (RepresentanteCadastrado e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro); // Retorna um JSON com erro
        }
    }


    
    // @PutMapping("/api/representante/operacao")
    // public ResponseEntity<String> atualizar(@RequestBody RepresentanteDTO obj){
    //     String mensagem = service.gravar(obj);
    //     return ResponseEntity.ok(mensagem); 
    // }


    // @PutMapping("/api/representante/operacao")
    // public ResponseEntity<String> atualizar(@RequestBody RepresentanteDTO obj) {
    //     try {
    //         String mensagem = service.gravar(obj);
    //         return ResponseEntity.ok(mensagem);
    //     } catch (RepresentanteCadastrado e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //     }
    // }

    @PutMapping("/api/representante/operacao")
    public ResponseEntity<String> atualizar(@RequestBody RepresentanteDTO obj) {
        Optional<RepresentanteEntity> representanteOpt = repository.findByEmail(obj.getEmail());
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
            repository.save(representante);
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

    // @DeleteMapping("/api/representante/operacao/{id}")
    // public ResponseEntity<String> remover(@PathVariable long id){
    //     String menString = service.remover(id);
    //     return ResponseEntity.ok(menString);
    // }

    @GetMapping("/api/representantes")
    public ResponseEntity<List<RepresentanteEntity>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping("/api/representante/login")
    public RepresentanteEntity fazerLogin(@RequestBody RepresentanteEntity dto) {
        Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(dto.getEmail());
    
        // Verifica se o representante com o email existe
        if (representanteOpt.isPresent()) {
            RepresentanteEntity representante = representanteOpt.get();
    
            // Compara a senha do DTO com a do banco de dados
            if (representante.getSenha().equals(dto.getSenha())) {
                return representante;  // Login bem-sucedido, retorna o representante
            }
        }
    
        // Se o email não existe ou as senhas não combinam, retorna uma nova instância vazia
        return new RepresentanteEntity();
    }
    
    @GetMapping("/api/representante/operacao/email/{email}")
    public ResponseEntity<RepresentanteEntity> buscarRepresentantePorEmail(@PathVariable String email) {
        Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(email);
        if (representanteOpt.isPresent()) {
            return ResponseEntity.ok(representanteOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    


    
    // @GetMapping("/api/representante/operacao/{email}")
    // public ResponseEntity<RepresentanteEntity> buscarRepresentantePorEmail(@PathVariable String email) {
    //     Optional<RepresentanteEntity> representanteOpt = service.buscarRepresentantePorEmail(email);
    
    //     // Verifica se o representante com o email foi encontrado
    //     if (representanteOpt.isPresent()) {
    //         return ResponseEntity.ok(representanteOpt.get());
    //     } else {
    //         // Se não foi encontrado, retorna 404 Not Found
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }
    // }
    

}
