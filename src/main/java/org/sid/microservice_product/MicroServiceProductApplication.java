package org.sid.microservice_product;

import org.sid.microservice_product.entities.Product;
import org.sid.microservice_product.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceProductApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroServiceProductApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner (ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);

            productRepository.saveAll(
                    List.of(
                            Product.builder().name("pen").price(5.0).img("Link1").build(),
                            Product.builder().name("basket").price(15.5).img("Link2").build(),
                            Product.builder().name("book").price(3.3).img("Link3").build()

                    )

            );
            productRepository.findAll().forEach(p->{
                System.out.println(p);
            });
        };
    }

}
