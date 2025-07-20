package br.com.fmautoserv.models;

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
	private OrdemDeServico ordem;
    private String descricao;
    private int quantidade;
    private float valorUnitario;
    private float valorTotal;
    
    public void calcularValorTotalItem() {
        this.valorTotal = this.quantidade * this.valorUnitario;
    }
}
