package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoriaDto {
    @NotBlank
    @NotNull
    private String nombre;
}
