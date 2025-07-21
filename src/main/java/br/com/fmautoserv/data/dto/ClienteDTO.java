package br.com.fmautoserv.data.dto;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idcliente")
public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5403195132279645777L;
	
	private Long idcliente;
	private String nome;
	private String telefone;
	private String celular;
	private String cpfcnpj;
	private String endereco;
	private String bairro;
	private String cidade;
	
    private List<VeiculoDTO> veiculos;
}
