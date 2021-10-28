package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProyectoDto {
	private Long developerId;
	private Long juegoId;
	@NotBlank
	@NotNull
	private String puesto;
}
