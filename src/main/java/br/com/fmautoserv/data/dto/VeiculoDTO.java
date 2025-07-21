package br.com.fmautoserv.data.dto;


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
	private String placa;
    private ClienteDTO cliente;
}
