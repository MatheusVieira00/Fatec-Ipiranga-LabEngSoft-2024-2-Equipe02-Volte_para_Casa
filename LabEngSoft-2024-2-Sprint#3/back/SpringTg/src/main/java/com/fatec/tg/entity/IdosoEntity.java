package com.fatec.tg.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "idoso")
public class IdosoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_idoso") 
    private long idIdoso;
    
    @Column(name = "nome")
    // @NotEmpty (message= "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "genero")
    private String genero;

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

    @Column(name = "tipoDemencia")
    private String tipoDemencia;

    @Column(name = "dataNascimento")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "id_representante")
    @JsonBackReference
    private RepresentanteEntity representante;

    @OneToOne(mappedBy = "idoso")
    @JsonManagedReference
    private QRCodeEntity qrCode;

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

    public LocalDate getDataNascimento() {  
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public RepresentanteEntity getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteEntity representante) {
        this.representante = representante;
    }

    public QRCodeEntity getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeEntity qrCode) {
        this.qrCode = qrCode;
    }
}