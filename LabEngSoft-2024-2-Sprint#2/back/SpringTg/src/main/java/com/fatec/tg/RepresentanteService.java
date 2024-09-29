package com.fatec.tg;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.repository.RepresentanteRepository;

@Service
public class RepresentanteService {
    @Autowired
    private RepresentanteRepository repository;

    // public String gravar(RepresentanteDTO dto) {
    //     System.out.println("Dados recebidos: " + dto.toString());  // Log para verificar os dados
    //     // Verifica se o email já está em uso
    //     if (repository.existsByEmail(dto.getEmail())) {
    //         throw new RepresentanteCadastrado(dto.getEmail());
    //     }
    
    //     try {
    //         RepresentanteEntity entity = new RepresentanteEntity();
    //         entity.setIdRepresentante(dto.getIdRepresentante());
    //         entity.setNome(dto.getNome());
    //         entity.setEmail(dto.getEmail());
    //         entity.setSenha(dto.getSenha());
    //         entity.setGenero(dto.getGenero());
    //         entity.setCpf(dto.getCpf());
    //         entity.setRg(dto.getRg());
    //         entity.setLogradouro(dto.getLogradouro());
    //         entity.setNumero(dto.getNumero());
    //         entity.setTelefone(dto.getTelefone());
    //         entity.setFoto(dto.getFoto());
    //         entity.setEstadoCivil(dto.getEstadoCivil());


    //         // RepresentanteEntity entity = new RepresentanteEntity();
    //         // entity.setIdRepresentante(dto.getIdRepresentante());
    //         // entity.setEmail(dto.getEmail());
    //         // entity.setSenha(dto.getSenha());
    //         // entity.setNome(dto.getNome());
    //         // entity.setGenero(dto.getGenero());
    
    //         repository.save(entity);
    //         return "Cadastro realizado com sucesso";
    //     } catch (DataIntegrityViolationException e) {
    //         throw new RepresentanteCadastrado(dto.getEmail());
    //     } catch (Exception e) {
    //         // Captura exceções genéricas e registra o erro
    //         e.printStackTrace(); // para depuração
    //         throw new RuntimeException("Erro ao gravar o representante: " + e.getMessage());
    //     }
    // }


    public String gravar(RepresentanteDTO dto) {
        System.out.println("Dados recebidos: " + dto.toString());  // Log para verificar os dados
        // Verifica se o email já está em uso
        Optional<RepresentanteEntity> existingEntity = repository.findByEmail(dto.getEmail());
        if (existingEntity.isPresent()) {
            // Atualizar o registro existente
            RepresentanteEntity entity = existingEntity.get();
            entity.setNome(dto.getNome());
            entity.setSenha(dto.getSenha());
            entity.setGenero(dto.getGenero());
            entity.setCpf(dto.getCpf());
            entity.setRg(dto.getRg());
            entity.setLogradouro(dto.getLogradouro());
            entity.setNumero(dto.getNumero());
            entity.setTelefone(dto.getTelefone());
            entity.setFoto(dto.getFoto());
            entity.setEstadoCivil(dto.getEstadoCivil());
            repository.save(entity);
            return "Cadastro atualizado com sucesso";
        } else {
            // Criar um novo registro
            RepresentanteEntity entity = new RepresentanteEntity();
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity.setGenero(dto.getGenero());
            entity.setCpf(dto.getCpf());
            entity.setRg(dto.getRg());
            entity.setLogradouro(dto.getLogradouro());
            entity.setNumero(dto.getNumero());
            entity.setTelefone(dto.getTelefone());
            entity.setFoto(dto.getFoto());
            entity.setEstadoCivil(dto.getEstadoCivil());
            repository.save(entity);
            return "Cadastro realizado com sucesso";
        }
    }

    

     public RepresentanteDTO carregar(long idRepresentante){
        RepresentanteDTO dto = new RepresentanteDTO();
        Optional<RepresentanteEntity> retorno = repository.findById(idRepresentante);
        if(retorno.isPresent()){
            dto.setIdRepresentante(retorno.get().getIdRepresentante());
            dto.setNome(retorno.get().getNome());
            dto.setEmail(retorno.get().getEmail());
            dto.setSenha(retorno.get().getSenha());
            dto.setGenero(retorno.get().getGenero());
            dto.setCpf(retorno.get().getCpf());
            dto.setRg(retorno.get().getRg());
            dto.setLogradouro(retorno.get().getLogradouro());
            dto.setNumero(retorno.get().getNumero());
            dto.setTelefone(retorno.get().getTelefone());
            dto.setFoto(retorno.get().getFoto());
            dto.setEstadoCivil(retorno.get().getEstadoCivil());
        }
        return dto;        
     }

     
    // public String remover(long idRepresentante) {
    //     Optional<RepresentanteEntity> representanteOpt = repository.findById(idRepresentante);
    //     if (representanteOpt.isPresent()) {
    //         repository.delete(representanteOpt.get());
    //         return "Representante removido com sucesso.";
    //     } else {
    //         throw new RuntimeException("Representante não encontrado para remoção.");
    //     }
    // }
    
    public String removerPorEmail(String email) {
        Optional<RepresentanteEntity> representanteOpt = repository.findByEmail(email);
        if (representanteOpt.isPresent()) {
            repository.delete(representanteOpt.get());
            return "Representante removido com sucesso.";
        } else {
            throw new RuntimeException("Representante não encontrado para remoção.");
        }
    }

    public List<RepresentanteEntity> listar() {
        return  repository.findAll();
    }

    
    public Optional<RepresentanteEntity> buscarRepresentantePorEmail(String email) {
        return repository.findByEmail(email);
    }
    

}
