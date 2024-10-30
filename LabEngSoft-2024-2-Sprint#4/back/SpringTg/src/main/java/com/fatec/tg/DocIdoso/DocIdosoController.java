

package com.fatec.tg.DocIdoso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fatec.tg.entity.DocIdosoEntity;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/arquivos")
public class DocIdosoController {

    @Autowired
    private DocIdosoService docIdosoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadArquivo(
            @RequestParam("pessoaId") Long pessoaId,
            @RequestParam("file") MultipartFile file) {

        try {
            String mensagem = docIdosoService.uploadArquivo(pessoaId, file);
            return ResponseEntity.ok(mensagem);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer upload do arquivo.");
        }
    }

    @GetMapping("/pessoa/{pessoaId}")
    public List<ArquivoDTO> listarArquivosPorPessoa(@PathVariable Long pessoaId) {
        return docIdosoService.listarArquivosPorPessoa(pessoaId);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long id) {
        try {
            DocIdosoEntity arquivo = docIdosoService.getArquivoById(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(arquivo.getTipoArquivo()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNomeArquivo() + "\"")
                    .body(arquivo.getArquivo());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarArquivo(@PathVariable Long id) {
        try {
            docIdosoService.deletarArquivo(id);
            return ResponseEntity.ok("Arquivo deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o arquivo: " + e.getMessage());
        }
    }
}
