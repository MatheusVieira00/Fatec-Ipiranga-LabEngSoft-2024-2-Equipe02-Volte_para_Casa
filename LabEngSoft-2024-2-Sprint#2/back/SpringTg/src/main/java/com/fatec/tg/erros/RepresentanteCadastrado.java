package com.fatec.tg.erros;

public class RepresentanteCadastrado extends RuntimeException {

    public RepresentanteCadastrado( String email ){
        super("Email já cadastrado na plataforma: " + email);
    }
}