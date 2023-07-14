package com.programacion.reactiva;

import com.programacion.reactiva.models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ProgramacionReactivaApplication implements CommandLineRunner {

    private static final Logger log= LoggerFactory.getLogger(ProgramacionReactivaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProgramacionReactivaApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {


        Flux<Usuario> nombres= Flux.just("andres","pedro","maria","diego","juan")
                .map(nombre -> new Usuario(nombre.toUpperCase(),null))
                .doOnNext(usuario ->{
                    if (usuario == null){
                       throw new RuntimeException("nombres no pueden ser vacios");
                    }
                        System.out.println(usuario.getNombre());
                }).map(usuario -> {
                    String nombre= usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);
                    return usuario;
                });

        nombres.subscribe(s -> log.info(s.getNombre()), error->log.error(error.getMessage()), new Runnable() {
            @Override
            public void run() {
                log.info("ha finalizado la ejecucion del observable con exito");
            }
        });




    }
}
