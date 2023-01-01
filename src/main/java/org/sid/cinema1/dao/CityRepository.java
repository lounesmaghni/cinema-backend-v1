package org.sid.cinema1.dao;

import org.sid.cinema1.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface CityRepository extends JpaRepository<City,Long> {
}
