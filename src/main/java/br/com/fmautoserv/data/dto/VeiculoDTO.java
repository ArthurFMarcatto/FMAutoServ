package br.com.fmautoserv.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idveiculo")
public class VeiculoDTO {

	private Long idveiculo;
	private String montadora;
	private String modelo;
	private int ano;
	private String cor;
	@NotBlank(message = "Placa é obrigatória")
	@Pattern(
		    regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
		    message = "Placa inválida. Use formato ABC1234 ou ABC1D23"
		)
	private String placa;
	@NotNull(message = "clienteId é obrigatório")
	private Long clienteId;
}
