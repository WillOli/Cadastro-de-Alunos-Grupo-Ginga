package com.Controle_Financeiro_Pessoal.entity;

import com.Controle_Financeiro_Pessoal.TipoLancamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um Lançamento (Receita ou Despesa) no banco de dados.
 * Esta é a tabela principal para os registros financeiros.
 */
@Entity
@Table(name = "tb_lancamentos") // Nome da tabela no MySQL
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    /**
     * Usamos BigDecimal para valores monetários para evitar problemas de
     * arredondamento que acontecem com float ou double.
     * nullable = false garante que um lançamento sempre tenha um valor.
     */
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    /**
     * @Enumerated(EnumType.STRING)
     * Isso avisa ao JPA para salvar o Enum como seu nome ("RECEITA" ou "DESPESA")
     * no banco de dados, em vez de seu número (0 ou 1).
     * É uma prática muito melhor para a legibilidade do banco.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLancamento tipo;

    /**
     * Mapeamento do relacionamento: Muitos Lançamentos pertencem a Um Usuário.
     * Esta é a entidade "dona" do relacionamento.
     *
     * @ManyToOne: Define o tipo de relação.
     * @JoinColumn(name = "usuario_id"):
     * - Cria a coluna 'usuario_id' na tabela 'lancamentos'.
     * - Esta coluna será a chave estrangeira (foreign key) que aponta para o 'id' da tabela 'usuarios'.
     * 'fetch = FetchType.LAZY': Otimização. O Spring só carregará o objeto 'Usuario'
     * associado quando você chamar getUsuario().
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
