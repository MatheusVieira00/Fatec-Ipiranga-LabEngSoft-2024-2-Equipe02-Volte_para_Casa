package com.fatec.tg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "DocIdoso")
public class DocIdosoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pessoaId;

    private String nomeArquivo;

    private String tipoArquivo;

    @Lob
    private byte[] arquivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
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

        public byte[] getArquivo() {
            return arquivo;
        }

        public void setArquivo(byte[] arquivo) {
            this.arquivo = arquivo;
        }


}




