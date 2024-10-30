package com.fatec.tg.erros;

public class IdosoCadastrado extends RuntimeException {
    public IdosoCadastrado( String id ){
        super("Idoso já cadastrado na plataforma: " + id);
    }
}


