package io.gihub.nathanaelcarauna.service;

import io.gihub.nathanaelcarauna.api.dto.PedidoDTO;
import io.gihub.nathanaelcarauna.domain.entity.Pedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
