package com.github.fernandamakihirose.citiesapi.countries;

import com.github.fernandamakihirose.citiesapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController // diz que é um controller
@RequestMapping("/countries") // rota
public class CountryResource {

    // cria o repository
    private CountryRepository repository;

    // faz o mesmo papel que o @Autowired, porém orientado a objetos (construiu o construtor), ele serve para injetar o repository e fazer ele funcionar 
    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Country> countries(Pageable page) { // retorna uma página ao fazer o get em vez de uma list, é uma melhor prática
        return repository.findAll(page); // busca todos os países no repository (banco de dados)
    }


    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) { // busca por id
        Optional<Country> optional = repository.findById(id); // é optional porque o id pode ou não existir

        if (optional.isPresent()) { // se existe alguém dentro do optional retorna 200
            return ResponseEntity.ok().body(optional.get()); // vai retornar o ResponseEntity com o status ok e o corpo da resposta é o que está no optional
        } else { // caso contrário a RespondeEntity não foi encontrada
            return ResponseEntity.notFound().build();
        }
    }
}
