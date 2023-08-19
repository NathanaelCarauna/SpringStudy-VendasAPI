package io.gihub.nathanaelcarauna.configurations;


import io.gihub.nathanaelcarauna.annotations.DevelopmentProfile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@DevelopmentProfile
public class MyConfiguration {

    @Bean()
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean(name = "executar animal")
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÂO DE DESENVOLVIMENTO");
        };
    }
}
