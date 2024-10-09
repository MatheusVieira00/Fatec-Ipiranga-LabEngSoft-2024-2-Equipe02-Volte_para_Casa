package com.fatec.tg.DocIdoso;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.tg.entity.DocIdosoEntity;
import com.fatec.tg.repository.DocIdosoRepository;

@Service
public class DocIdosoService {

    @Autowired
    private DocIdosoRepository arquivoRepository;

    @Autowired
    private DocIdosoLogService logService; // Adiciona o serviço de log

    public String uploadArquivo(Long pessoaId, MultipartFile file) throws Exception {
        DocIdosoEntity arquivo = new DocIdosoEntity();
        arquivo.setPessoaId(pessoaId);
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setTipoArquivo(file.getContentType());
        arquivo.setArquivo(file.getBytes());

        arquivoRepository.save(arquivo);

        // Registra a ação no log
        logService.registrarAcao(arquivo.getId(), "Upload de arquivo");

        return "Arquivo enviado com sucesso!";
    }

    public List<ArquivoDTO> listarArquivosPorPessoa(Long pessoaId) {
        List<DocIdosoEntity> arquivos = arquivoRepository.findByPessoaId(pessoaId);
        return arquivos.stream().map(arquivo -> new ArquivoDTO(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                arquivo.getTipoArquivo())
        ).collect(Collectors.toList());
    }

    public DocIdosoEntity getArquivoById(Long id) throws RuntimeException {
        DocIdosoEntity arquivo = arquivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));

        // Registra a ação no log
        logService.registrarAcao(id, "Download de arquivo");

        return arquivo;
    }

    public void deletarArquivo(Long id) throws RuntimeException {
        DocIdosoEntity arquivo = arquivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));

        arquivoRepository.delete(arquivo);

        // Registra a ação no log
        logService.registrarAcao(id, "Exclusão de arquivo");
    }
}
