package io.gihub.nathanaelcarauna.api;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String memsagem) {
        this.errors = Arrays.asList(memsagem);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
