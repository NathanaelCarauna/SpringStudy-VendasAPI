package io.gihub.nathanaelcarauna;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class MyConfiguration {

    @Bean()
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean()
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÂO DE DESENVOLVIMENTO");
        };
    }
}
