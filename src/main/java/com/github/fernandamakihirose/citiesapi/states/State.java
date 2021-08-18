package com.github.fernandamakihirose.citiesapi.states;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity(name = "State") // diz que é uma entidade
@Table(name = "estado") // diz que a tabela se chama "estado"

// diz para o hibernate que a coluna 'jsonb' está dentro da dependência adicionada
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class State {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private String uf;

    private Integer ibge;

  //1st
  @Column(name = "pais")
  private Integer countryId;

    /*2nd - @ManyToOne
    @ManyToOne // vários estados pertencem a um país
    @JoinColumn(name = "pais", referencedColumnName = "id") // passa o nome da coluna do país e o id
    private Country country;*/

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd; // quando fazer o select na tabela de estado a lista de ddd vem na lista do java

    public State() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public List<Integer> getDdd() {
        return ddd;
    }

    /*public Country getCountry() {
        return country;
    }*/

  /*public Integer getCountryId() {
      return countryId;
  }*/
}
