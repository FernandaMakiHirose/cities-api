package com.github.fernandamakihirose.citiesapi.countries.repository;

import com.github.fernandamakihirose.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

// cria uma interface que representa uma um repository dos países
public interface CountryRepository extends JpaRepository<Country, Long> {
}
