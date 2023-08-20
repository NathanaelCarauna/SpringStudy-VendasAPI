package io.gihub.nathanaelcarauna.domain.repository;

import io.gihub.nathanaelcarauna.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
