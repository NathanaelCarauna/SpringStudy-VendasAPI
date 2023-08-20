package io.gihub.nathanaelcarauna.domain.repository;

import io.gihub.nathanaelcarauna.domain.entity.Cliente;
import io.gihub.nathanaelcarauna.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);
}
