package br.com.fmautoserv.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ordemdeservico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idos")
public class OrdemDeServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idos;
	
    @ManyToOne
    @JoinColumn(name = "idcliente")
	private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idveiculo")
	private Veiculo veiculo;
    
	private float total;
	
    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL)
    private List<ItemOrdem> itensOrdem;

}
