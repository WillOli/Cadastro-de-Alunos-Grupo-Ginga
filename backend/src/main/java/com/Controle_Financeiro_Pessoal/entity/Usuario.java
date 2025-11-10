package com.Controle_Financeiro_Pessoal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "tb_usuarios") // Mantive o nome da tabela que você escolheu
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    /**
     * CORREÇÃO 2:
     * Adicionado unique = true de volta.
     * Isso garante que não podem existir dois usuários com o mesmo email.
     */
    @Column(nullable = false, unique = true) // <-- CORRIGIDO
    private String email;

    @Column(nullable = false)
    private String senha;

    /**
     * CORREÇÃO 1 (O ERRO PRINCIPAL):
     * O 'mappedBy' DEVE ser o nome do campo na classe Lancamento.
     * O nome do campo é 'usuario' (private Usuario usuario;).
     */
    @OneToMany(
            mappedBy = "usuario", // <-- CORRIGIDO
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Lancamento> lancamentos = new HashSet<>();

}