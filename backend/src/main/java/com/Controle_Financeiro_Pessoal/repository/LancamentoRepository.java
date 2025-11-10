package com.Controle_Financeiro_Pessoal.repository;
import com.Controle_Financeiro_Pessoal.TipoLancamento;
import com.Controle_Financeiro_Pessoal.entity.Lancamento;
import com.Controle_Financeiro_Pessoal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface Repository para a entidade Lancamento.
 *
 * JpaRepository<Lancamento, Long>
 * - Lancamento: A entidade que este repositório gerencia.
 * - Long: O tipo da chave primária (ID) da entidade Lancamento.
 */
@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    /**
     * Método customizado para buscar todos os lançamentos de um usuário específico.
     * O Spring Data JPA entende o nome do método ("findByUsuario") e
     * automaticamente gera a query SQL: "SELECT * FROM lancamentos WHERE usuario_id = ?"
     *
     * @param usuario O objeto Usuario pelo qual filtrar.
     * @return Uma lista de Lancamentos pertencentes àquele usuário.
     */
    List<Lancamento> findByUsuario(Usuario usuario);

    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM Lancamento l WHERE l.usuario = :usuario AND l.tipo = :tipo")
    BigDecimal calcularTotalPorTipoEUsuario(Usuario usuario, TipoLancamento tipo);



}
