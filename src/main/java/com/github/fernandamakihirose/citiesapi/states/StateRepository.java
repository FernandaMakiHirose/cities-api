package com.github.fernandamakihirose.citiesapi.states;

import org.springframework.data.jpa.repository.JpaRepository;

// cria uma interface que representa uma um repository do país
public interface StateRepository extends JpaRepository<State, Long> {
}