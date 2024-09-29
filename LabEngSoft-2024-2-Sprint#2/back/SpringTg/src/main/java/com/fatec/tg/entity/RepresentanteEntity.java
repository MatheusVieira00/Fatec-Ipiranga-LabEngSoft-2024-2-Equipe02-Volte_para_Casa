package com.fatec.tg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "representante")
public class RepresentanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long idRepresentante;

    @Column(name = "nome")
    // @NotEmpty (message= "{campo.nome.obrigatorio}")
    private String nome;

    @Column(unique = true, name = "email")
    // @NotEmpty (message= "{campo.email.obrigatorio}")
    private String email;
    @Column(name = "senha")
    // @NotEmpty (message= "{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "genero")
    private String genero;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private int numero;

    @Column(name = "rg")
    private String rg;

    @Lob
    @Column(name = "foto")
    private String foto;

    @Column(name = "estadoCivil")
    private String estadoCivil;

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
}