package com.fatec.tg.DocRepresentante;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.tg.entity.DocRepresentanteEntity;
import com.fatec.tg.repository.DocRepresentanteRepository;
// import com.fatec.tg.DocRepresentante.DocRepresentanteLogService;

@Service
public class DocRepresentanteService {

    @Autowired
    private DocRepresentanteRepository docRepresentanteRepository;

    @Autowired
    private DocRepresentanteLogService logService;

    public String uploadArquivo(Long representanteId, MultipartFile file) throws Exception {
        DocRepresentanteEntity arquivo = new DocRepresentanteEntity();
        arquivo.setRepresentanteId(representanteId);
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setTipoArquivo(file.getContentType());
        arquivo.setArquivo(file.getBytes());

        docRepresentanteRepository.save(arquivo);

        // Registra ação no log
        logService.registrarAcao(arquivo.getId(), "Upload de arquivo");

        return "Arquivo enviado com sucesso!";
    }

    public List<ArquivoDTO> listarArquivosPorRepresentante(Long representanteId) {
        List<DocRepresentanteEntity> arquivos = docRepresentanteRepository.findByRepresentanteId(representanteId);
        return arquivos.stream().map(arquivo -> new ArquivoDTO(
                arquivo.getId(),
                arquivo.getNomeArquivo(),
                arquivo.getTipoArquivo())
        ).collect(Collectors.toList());
    }

    public DocRepresentanteEntity getArquivoById(Long id) throws RuntimeException {
        DocRepresentanteEntity arquivo = docRepresentanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));

        // Registra ação no log
        logService.registrarAcao(id, "Download de arquivo");

        return arquivo;
    }

    public void deletarArquivo(Long id) throws RuntimeException {
        DocRepresentanteEntity arquivo = docRepresentanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado"));

        docRepresentanteRepository.delete(arquivo);

        // Registra ação no log
        logService.registrarAcao(id, "Exclusão de arquivo");
    }
}
