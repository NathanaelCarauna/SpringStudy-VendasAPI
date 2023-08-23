package io.gihub.nathanaelcarauna.api.controller;

import io.gihub.nathanaelcarauna.api.dto.PedidoDTO;
import io.gihub.nathanaelcarauna.domain.entity.Pedido;
import io.gihub.nathanaelcarauna.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
