package br.com.fmautoserv.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itemordem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iditemordem")
public class ItemOrdem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iditemordem;
	
    @ManyToOne
    @JoinColumn(name = "idos")
	private OrdemDeServico ordemDeServico;
    private String descricao;
    private int quantidade;
    private float valorUnitario;
    private float valorTotal;
    
    public void calcularValorTotalItem() {
        this.valorTotal = this.quantidade * this.valorUnitario;
    }
}
