package com.fatec.tg.erros;

public class RepresentanteCadastrado extends RuntimeException {
    public RepresentanteCadastrado(String message) {
        super(message);
    }
}