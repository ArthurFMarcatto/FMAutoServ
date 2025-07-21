package br.com.fmautoserv.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iditemordem")
public class ItemOrdemDTO {
	private Long iditemordem;
	
	private OrdemDeServicoDTO ordem;
    private String descricao;
    private int quantidade;
    private float valorUnitario;
    private float valorTotal;
    
    public void calcularValorTotalItem() {
        this.valorTotal = this.quantidade * this.valorUnitario;
    }
}
