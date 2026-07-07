package br.com.fmautoserv.data.dto;

import java.io.Serializable;
import java.util.List;

import br.com.fmautoserv.validation.cpfcnpj.CPFCNPJValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@Size(min = 3, max = 100)
	@NotBlank
	private String nome;
	private String telefone;
	@NotBlank
	private String celular;
	@CPFCNPJValido
	@NotBlank(message = "CPF/CNPJ é obrigatório")
	private String cpfcnpj;
	@Size(max = 255)
	private String endereco;
	@Size(max = 255)
	private String bairro;
	@Size(max = 255)
	private String cidade;
	
    private List<VeiculoDTO> veiculos;
}
