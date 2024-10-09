package com.fatec.tg.DocIdoso;



public class ArquivoDTO {
    private Long id;
    private String nomeArquivo;
    private String tipoArquivo;


    public ArquivoDTO(Long id, String nomeArquivo, String tipoArquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.tipoArquivo = tipoArquivo;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    
}