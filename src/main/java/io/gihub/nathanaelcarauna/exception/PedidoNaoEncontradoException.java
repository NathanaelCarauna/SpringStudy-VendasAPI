package io.gihub.nathanaelcarauna.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String pedidoNãoEncontrado) {
        super(pedidoNãoEncontrado);
    }
}
