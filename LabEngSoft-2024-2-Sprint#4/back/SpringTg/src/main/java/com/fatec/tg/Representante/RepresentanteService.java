package com.fatec.tg.Representante;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.IdosoEntity;
import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.entity.RepresentanteLogEntity;
import com.fatec.tg.repository.IdosoRepository;
import com.fatec.tg.repository.RepresentanteLogRepository;
import com.fatec.tg.repository.RepresentanteRepository;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository repository;

    @Autowired
    public RepresentanteLogRepository logRepository;

    @Autowired
    private IdosoRepository idosoRepository;


    public String gravar(RepresentanteDTO dto) {
        System.out.println("Dados recebidos: " + dto.toString());
    
        Optional<RepresentanteEntity> existingEntity = repository.findByEmail(dto.getEmail());
        RepresentanteLogEntity log = new RepresentanteLogEntity();
        log.setDataHora(LocalDateTime.now());
    
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
            log.setAcao("Registro alterado");
            log.setRepresentanteId(entity.getIdRepresentante()); // Definir o ID do representante
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
            entity = repository.save(entity); // Salvar e obter a entidade persistida
            log.setAcao("Cadastro realizado");
            log.setRepresentanteId(entity.getIdRepresentante()); // Agora o ID é correto
        }
    
        logRepository.save(log); // Salva o log
        return existingEntity.isPresent() ? "Cadastro atualizado com sucesso" : "Cadastro realizado com sucesso";
    }
    

    public RepresentanteDTO carregar(long idRepresentante) {
        RepresentanteDTO dto = new RepresentanteDTO();
        Optional<RepresentanteEntity> retorno = repository.findById(idRepresentante);
        if (retorno.isPresent()) {
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

    public String removerPorEmail(String email) {
        Optional<RepresentanteEntity> representanteOpt = repository.findByEmailAndIsDeletedFalse(email);
        if (representanteOpt.isPresent()) {
            RepresentanteEntity representante = representanteOpt.get();
            representante.setDeleted(true);  // Soft delete
            repository.save(representante);

            // Registro de log
            RepresentanteLogEntity log = new RepresentanteLogEntity();
            log.setRepresentanteId(representante.getIdRepresentante());
            log.setAcao("Registro excluído");
            log.setDataHora(LocalDateTime.now());
            logRepository.save(log);

            return "Representante removido com sucesso (soft delete).";
        } else {
            throw new RuntimeException("Representante não encontrado para remoção.");
        }
    }

    public List<RepresentanteEntity> listar() {
        return repository.findAllByIsDeletedFalse();
    }

    public Optional<RepresentanteEntity> buscarRepresentantePorEmail(String email) {
        System.out.println("Buscando representante com email: " + email);
        return repository.findByEmailAndIsDeletedFalse(email);
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
    
    public Optional<RepresentanteDTO> buscarRepresentantePorIdIdoso(long idIdoso) {
        
        Optional<IdosoEntity> idosoOpt = idosoRepository.findById(idIdoso); 

        if (idosoOpt.isPresent()) {
            RepresentanteEntity representante = idosoOpt.get().getRepresentante();
            if (representante != null && !representante.isDeleted()) {
                RepresentanteDTO dto = mapToDTO(representante); 
                return Optional.of(dto);
            }
        }

        return Optional.empty();
    }

}
