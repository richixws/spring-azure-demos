package com.programacion.reactiva;

import com.programacion.reactiva.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class mensage {

      @GetMapping()
      public List<Usuario> mesagge(){
          List<Usuario> lista= Arrays.asList(new Usuario("richard","wong"));
          return lista.stream().collect(Collectors.toList());
      }
}
