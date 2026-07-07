package br.com.fmautoserv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    private BigDecimal total;

    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrdem> itensOrdem;

    public Long getClienteId() {
        return this.cliente != null ? this.cliente.getIdcliente() : null;
    }

    public Long getVeiculoId() {
        return this.veiculo != null ? this.veiculo.getIdveiculo() : null;
    }

    public void adicionarItem(ItemOrdem item) {
        item.setOrdemDeServico(this);
        item.atualizarValores();

        if (this.itensOrdem == null) {
            this.itensOrdem = new ArrayList<>();
        }

        this.itensOrdem.add(item);
        recalcularTotal();
    }

    public void recalcularTotal() {
        this.total = itensOrdem.stream()
            .map(ItemOrdem::getValorTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
