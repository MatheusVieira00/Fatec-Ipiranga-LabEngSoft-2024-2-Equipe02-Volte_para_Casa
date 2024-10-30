package com.fatec.tg.Representante;

import java.util.List;

import com.fatec.tg.Idoso.IdosoDTO;



public class RepresentanteDTO {
    private long idRepresentante;
    
    // @NotEmpty(message = "O campo nome é obrigatório")
    private String nome;

    // @NotEmpty(message = "O campo email é obrigatório")
    private String email;

    // @NotEmpty(message = "O campo senha é obrigatório")
    private String senha;

    private String genero;

    private String telefone;

    private String cpf;

    private String logradouro;

    private int numero;

    private String rg;

    private String foto;

    private String estadoCivil;

  
    private List<IdosoDTO> idosos;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public void setFoto(String foto){
        this.foto = foto;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil){
        this.estadoCivil = estadoCivil;
    }

    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(long idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public List<IdosoDTO> getIdosos() {
        return idosos;
    }

    public void setIdosos(List<IdosoDTO> idosos) {
        this.idosos = idosos;
    }
}