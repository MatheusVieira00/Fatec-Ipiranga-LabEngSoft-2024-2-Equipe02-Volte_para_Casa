package com.fatec.tg.erros;

import java.util.Arrays;
import java.util.List;

public class ErrosApi {

    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ErrosApi(List<String> errors) {
        this.errors = errors;
    }

    public ErrosApi(String message) {
        this.errors = Arrays.asList(message);
    }
}
