package com.github.fernandamakihirose.citiesapi.countries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // diz que é uma entidade
@Table(name = "pais") // diz que a tabela se chama "pais"
public class Country {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "nome_pt")
    private String portugueseName;

    @Column(name = "sigla")
    private String code;

    private Integer bacen;

    // gera o construtor 
    public Country() {
    }

    // gera os getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPortugueseName() {
        return portugueseName;
    }

    public String getCode() {
        return code;
    }

    public Integer getBacen() {
        return bacen;
    }
}
