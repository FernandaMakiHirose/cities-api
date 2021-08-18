package com.github.fernandamakihirose.citiesapi.cities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {

    // distância por ponto, é uma query nativa, no '?1' vai passar o id 1 que passar no método e no '?2' um id da cidade 2 e retorna o calculo em distances usando o tipo double 
    @Query(value = "SELECT ((SELECT lat_lon FROM cidade WHERE id=?1) <@> (SELECT lat_lon FROM cidade WHERE id=?2)) as distance", nativeQuery = true)
    Double distanceByPoints(final Long cityId1, final Long cityId2);

    // distância por cubo, é uma query nativa, vai passar latitude 1, 2 e longitude 3 e 4 
    @Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
    Double distanceByCube(final Double lat1, final Double lon1, final Double lat2, final Double lon2);
}
