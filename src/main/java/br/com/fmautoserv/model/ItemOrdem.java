package br.com.fmautoserv.model;

import java.math.BigDecimal;

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
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public void atualizarValores() {
        this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }
}
