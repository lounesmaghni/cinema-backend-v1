package org.sid.cinema1;

import org.sid.cinema1.entities.Movie;
import org.sid.cinema1.entities.Theater;
import org.sid.cinema1.entities.Ticket;
import org.sid.cinema1.service.IcinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class Cinema1Application implements CommandLineRunner {
    @Autowired
    private IcinemaInitService icinemaInitService;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(Cinema1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Movie.class, Theater.class, Ticket.class);
        icinemaInitService.initCities();
        icinemaInitService.initCinemas();
        icinemaInitService.initTheaters();
        icinemaInitService.initPositions();
        icinemaInitService.initSeances();
        icinemaInitService.initCategories();
        icinemaInitService.initMovies();
        icinemaInitService.initProjections();
        icinemaInitService.initTickets();
    }
}
