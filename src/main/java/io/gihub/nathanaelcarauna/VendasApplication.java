package io.gihub.nathanaelcarauna;


import io.gihub.nathanaelcarauna.domain.entity.Cliente;
import io.gihub.nathanaelcarauna.domain.entity.Pedido;
import io.gihub.nathanaelcarauna.domain.repository.Clientes;
import io.gihub.nathanaelcarauna.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
                                  @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Nathanael"));
            clientes.save(new Cliente("Symcia"));
            Cliente symcia = clientes.save(new Cliente("Larissa"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            Pedido p = new Pedido();
            p.setCliente(symcia);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            pedidos.save(p);

//            Cliente cliente = clientes.findClienteFetchPedidos(symcia.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            Set<Pedido> pedidosByCliente = pedidos.findByCliente(symcia);
            System.out.println(pedidosByCliente);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}