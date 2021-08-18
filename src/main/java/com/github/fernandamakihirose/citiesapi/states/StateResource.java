package com.github.fernandamakihirose.citiesapi.states;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateResource {

    // cria o repository
    private final StateRepository repository;

    // faz o mesmo papel que o @Autowired, porém orientado a objetos (construiu o construtor), ele serve para injetar o repository e fazer ele funcionar 
    public StateResource(final StateRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    // retorna uma lista com todos os estados
    public List<State> states() {
        return repository.findAll();
    }
}