package com.github.fernandamakihirose.citiesapi.distances.service;

import java.util.Arrays;
import java.util.List;
import com.github.fernandamakihirose.citiesapi.cities.City;
import com.github.fernandamakihirose.citiesapi.cities.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    Logger log = LoggerFactory.getLogger(DistanceService.class);
    private final CityRepository cityRepository;

    public DistanceService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // recebe o id de duas cidades e pega a distância pelos points
    public Double distanceByPointsInMiles(final Long city1, final Long city2) {
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        return cityRepository.distanceByPoints(city1, city2);
    }

    // recebe o id de duas cidades, vai no repository pega uma lista de todos os id e pega a localização pelo cube 
    public Double distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
}