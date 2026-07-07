package br.com.fmautoserv.data.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iditemordem")
public class ItemOrdemDTO {
	private Long iditemordem;
	@NotBlank(message = "Descrição é obrigatória")
	private String descricao;
	@Min(value = 1, message = "Quantidade deve ser maior que zero")
	private int quantidade;
	@DecimalMin(value = "0.0", inclusive = false, message = "Valor unitário deve ser maior que zero")
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;

}
