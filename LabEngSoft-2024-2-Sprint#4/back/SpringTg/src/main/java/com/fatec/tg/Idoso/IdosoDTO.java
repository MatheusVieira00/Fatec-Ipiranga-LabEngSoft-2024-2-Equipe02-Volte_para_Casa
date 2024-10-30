package com.fatec.tg.Idoso;

import java.time.LocalDate;


public class IdosoDTO {
    private long idIdoso;

    // @NotEmpty (message= "{campo.nome.obrigatorio}")
    private String nome;

    private String genero;

    private String cpf;

    private String logradouro;

    private int numero;

    private String rg;

    private String foto;

    private String estadoCivil;

    private String tipoDemencia;

    private LocalDate dataNascimento;

    private boolean isDeleted = false;

    private Long idRepresentante;

     public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

   
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getIdIdoso() {
        return idIdoso;
    }
    public void setIdIdoso(long idIdoso) {
        this.idIdoso = idIdoso;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getTipoDemencia() {
        return tipoDemencia;
    }
    public void setTipoDemencia(String tipoDemencia) {
        this.tipoDemencia = tipoDemencia;
    }
    public Long getIdRepresentante() {
        return idRepresentante;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setIdRepresentante(Long idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
}
