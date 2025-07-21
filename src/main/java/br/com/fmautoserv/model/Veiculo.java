package br.com.fmautoserv.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idveiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idveiculo;
	private String montadora;
	private String modelo;
	private int ano;
	private String cor;
	private String placa;
	
    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
}
