package io.gihub.nathanaelcarauna.domain.repository;

import io.gihub.nathanaelcarauna.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
