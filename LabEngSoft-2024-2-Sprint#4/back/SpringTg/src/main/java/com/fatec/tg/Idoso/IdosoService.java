package com.fatec.tg.Idoso;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fatec.tg.entity.IdosoEntity;
import com.fatec.tg.entity.IdosoLogEntity;
import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.repository.IdosoLogRepository;
import com.fatec.tg.repository.IdosoRepository;
import com.fatec.tg.repository.RepresentanteRepository;

@Service
public class IdosoService {

    private final IdosoRepository idosoRepository;
    private final RepresentanteRepository representanteRepository;
    private final IdosoLogRepository idosoLogRepository; 

    public IdosoService(IdosoRepository idosoRepository, RepresentanteRepository representanteRepository, IdosoLogRepository idosoLogRepository) {
        this.idosoRepository = idosoRepository;
        this.representanteRepository = representanteRepository;
        this.idosoLogRepository = idosoLogRepository;
    }

    public String gravar(IdosoDTO dto) {
        Optional<RepresentanteEntity> representanteOpt = representanteRepository.findById(dto.getIdRepresentante());

        if (!representanteOpt.isPresent()) {
            throw new RuntimeException("Representante não encontrado.");
        }

        Optional<IdosoEntity> existingEntity = idosoRepository.findById(dto.getIdIdoso());

        IdosoEntity entity = existingEntity.orElse(new IdosoEntity());
        entity.setNome(dto.getNome());
        entity.setGenero(dto.getGenero());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setFoto(dto.getFoto());
        entity.setEstadoCivil(dto.getEstadoCivil());
        entity.setTipoDemencia(dto.getTipoDemencia());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setDeleted(dto.isDeleted());

        
        entity.setRepresentante(representanteOpt.get());

        idosoRepository.save(entity);

        
        IdosoLogEntity logEntity = new IdosoLogEntity();
        logEntity.setIdRepresentante(dto.getIdRepresentante());
        logEntity.setAcao(existingEntity.isPresent() ? "Registro alterado" : "Cadastro realizado");
        logEntity.setDataHora(LocalDateTime.now());
        idosoLogRepository.save(logEntity);

        return existingEntity.isPresent() ? "Cadastro atualizado com sucesso" : "Cadastro realizado com sucesso";
    }

    public IdosoDTO carregar(long idIdoso) {
        Optional<IdosoEntity> retorno = idosoRepository.findByIdIdosoAndIsDeletedFalse(idIdoso);
        if (retorno.isEmpty()) {
            throw new RuntimeException("Idoso não encontrado.");
        }
        
        IdosoDTO dto = new IdosoDTO();
        IdosoEntity entity = retorno.get();
        dto.setIdIdoso(entity.getIdIdoso());
        dto.setNome(entity.getNome());
        dto.setGenero(entity.getGenero());
        dto.setCpf(entity.getCpf());
        dto.setRg(entity.getRg());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setFoto(entity.getFoto());
        dto.setEstadoCivil(entity.getEstadoCivil());
        dto.setTipoDemencia(entity.getTipoDemencia());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setIdRepresentante(entity.getRepresentante().getIdRepresentante());

        return dto;
    }

    public String removerPorId(long idIdoso) {
        Optional<IdosoEntity> idosoOpt = idosoRepository.findByIdIdosoAndIsDeletedFalse(idIdoso);
        if (idosoOpt.isPresent()) {
            IdosoEntity idoso = idosoOpt.get();
            idoso.setDeleted(true);  
            idosoRepository.save(idoso);

            
            IdosoLogEntity logEntity = new IdosoLogEntity();
            logEntity.setIdRepresentante(idoso.getRepresentante().getIdRepresentante());
            logEntity.setAcao("Registro excluído");
            logEntity.setDataHora(LocalDateTime.now());
            idosoLogRepository.save(logEntity);

            return "Idoso removido com sucesso (soft delete).";
        } else {
            throw new RuntimeException("Idoso não encontrado para remoção.");
        }
    }

    public IdosoDTO carregarIdRepresentante(long idRepresentante) {
        
        RepresentanteEntity representante = representanteRepository.findById(idRepresentante)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado com ID " + idRepresentante));
        
        
        List<IdosoEntity> idosos = idosoRepository.findByRepresentanteAndIsDeletedFalse(representante);
        
        if (idosos.isEmpty()) {
            throw new RuntimeException("Nenhum idoso encontrado para o representante com ID " + idRepresentante);
        }
    
        
        IdosoEntity entity = idosos.get(0); 
    
        IdosoDTO dto = new IdosoDTO();
        dto.setIdIdoso(entity.getIdIdoso());
        dto.setNome(entity.getNome());
        dto.setGenero(entity.getGenero());
        dto.setCpf(entity.getCpf());
        dto.setRg(entity.getRg());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setFoto(entity.getFoto());
        dto.setEstadoCivil(entity.getEstadoCivil());
        dto.setTipoDemencia(entity.getTipoDemencia());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setIdRepresentante(entity.getRepresentante().getIdRepresentante());
    
        return dto;
    }
    
    
    
    

    public List<IdosoDTO> listarTodos() {
    return idosoRepository.findAll().stream()
            .map(entity -> {
                IdosoDTO dto = new IdosoDTO();
                dto.setIdIdoso(entity.getIdIdoso());
                dto.setNome(entity.getNome());
                dto.setGenero(entity.getGenero());
                dto.setCpf(entity.getCpf());
                dto.setRg(entity.getRg());
                dto.setLogradouro(entity.getLogradouro());
                dto.setNumero(entity.getNumero());
                dto.setFoto(entity.getFoto());
                dto.setEstadoCivil(entity.getEstadoCivil());
                dto.setTipoDemencia(entity.getTipoDemencia());
                dto.setDataNascimento(entity.getDataNascimento());
                dto.setIdRepresentante(entity.getRepresentante() != null ? entity.getRepresentante().getIdRepresentante() : null);
                return dto;
            })
            .collect(Collectors.toList());
    }

    
    public Optional<IdosoEntity> buscarIdosoPorId(long idIdoso) {
        return idosoRepository.findByIdIdosoAndIsDeletedFalse(idIdoso);
    }

    
}
