package br.com.fmautoserv.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idcliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcliente;
	private String nome;
	private String telefone;
	private String celular;
	private String cpfcnpj;
	private String endereco;
	private String bairro;
	private String cidade;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cliente")
    private List<Veiculo> veiculos = new ArrayList<>();

}
