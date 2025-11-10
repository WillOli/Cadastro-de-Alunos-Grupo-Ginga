package com.Controle_Financeiro_Pessoal.repository;

import com.Controle_Financeiro_Pessoal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface Repository para a entidade Usuario.
 * O Spring Data JPA criará automaticamente a implementação
 * com base nos métodos definidos aqui.
 *
 * JpaRepository<Usuario, Long>
 * - Usuario: A entidade que este repositório gerencia.
 * - Long: O tipo da chave primária (ID) da entidade Usuario.
 */
@Repository // Opcional, mas boa prática para clareza
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método customizado para buscar um usuário pelo seu email.
     * O Spring Data JPA entende o nome do método ("findByEmail") e
     * automaticamente gera a query SQL: "SELECT * FROM usuarios WHERE email = ?"
     *
     * Usamos Optional<Usuario> para evitar NullPointerExceptions,
     * pois um usuário pode não ser encontrado com o email fornecido.
     *
     * @param email O email a ser buscado.
     * @return um Optional contendo o Usuario (se encontrado) ou um Optional vazio.
     */
    Optional<Usuario> findByEmail(String email);

}
