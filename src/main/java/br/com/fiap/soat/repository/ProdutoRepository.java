package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.ProdutoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Interface do repositório de produtos.
 */
@Component
public interface ProdutoRepository extends JpaRepository<ProdutoJpa, Long> {}
