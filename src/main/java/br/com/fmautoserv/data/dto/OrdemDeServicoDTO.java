package br.com.fmautoserv.data.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idos")
public class OrdemDeServicoDTO {
	private Long idos;
	private ClienteDTO cliente;
	private VeiculoDTO veiculo;
	private float total;
    private List<ItemOrdemDTO> itensOrdem;

}
