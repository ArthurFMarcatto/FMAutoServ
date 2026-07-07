package br.com.fmautoserv.data.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idos")
public class OrdemDeServicoDTO {
	private Long idos;
	@NotNull(message = "clienteId é obrigatório")
	private Long clienteId;
	@NotNull(message = "veiculoId é obrigatório")
	private Long veiculoId;
	private float total;
	@NotEmpty(message = "A ordem deve ter ao menos um item")
	@Valid
    private List<ItemOrdemDTO> itensOrdem;

}
