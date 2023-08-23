package io.gihub.nathanaelcarauna.service.impl;

import io.gihub.nathanaelcarauna.api.dto.ItemPedidoDTO;
import io.gihub.nathanaelcarauna.api.dto.PedidoDTO;
import io.gihub.nathanaelcarauna.domain.entity.Cliente;
import io.gihub.nathanaelcarauna.domain.entity.ItemPedido;
import io.gihub.nathanaelcarauna.domain.entity.Pedido;
import io.gihub.nathanaelcarauna.domain.entity.Produto;
import io.gihub.nathanaelcarauna.domain.enums.StatusPedido;
import io.gihub.nathanaelcarauna.domain.repository.Clientes;
import io.gihub.nathanaelcarauna.domain.repository.ItemsPedido;
import io.gihub.nathanaelcarauna.domain.repository.Pedidos;
import io.gihub.nathanaelcarauna.domain.repository.Produtos;
import io.gihub.nathanaelcarauna.exception.PedidoNaoEncontradoException;
import io.gihub.nathanaelcarauna.exception.RegraNegocioException;
import io.gihub.nathanaelcarauna.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);
        List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findById(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar pedido sem items");
        }
        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
