package org.sid.cinema1.service;

import jakarta.transaction.Transactional;
import org.sid.cinema1.dao.*;
import org.sid.cinema1.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class ImplCinemaInitService implements IcinemaInitService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieProjectionRepository projectionRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void initCities() {
        Stream.of("Alger", "Tizi ouzou", "oran", "setif").forEach(nameCity -> {
            City city = new City();
            city.setName(nameCity);
            cityRepository.save(city);
        });
    }

    @Override
    public void initCinemas() {
        cityRepository.findAll().forEach(c -> {
            Stream.of("Algeria", "Djurdjura", "Essaada", "Cinefeel").forEach(nameCinema -> {
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                //between 3 and 10 theater
                cinema.setNbrTheater(3 + (int) (Math.random() * 7));
                cinema.setCity(c);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initTheaters() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNbrTheater(); i++) {
                Theater theater = new Theater();
                theater.setName("theater N" + (i + 1));
                theater.setCinema(cinema);
                theater.setNbrPosition(20 + (int) (Math.random() * 20));
                theaterRepository.save(theater);
            }
        });
    }

    @Override
    public void initPositions() {
        theaterRepository.findAll().forEach(theater -> {
            for (int i = 0; i < theater.getNbrPosition(); i++) {
                Position position = new Position();
                position.setNumber(i + 1);
                position.setTheater(theater);
                positionRepository.save(position);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
            Seance seance = new Seance();
            try {
                seance.setStartTime(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("History", "Action", "Fiction", "adventure").forEach(cat -> {
            Category category = new Category();
            category.setName(cat);
            categoryRepository.save(category);
        });
    }

    @Override
    public void initMovies() {
        double[] duration = new double[]{1, 1.5, 2, 2.5, 3};
        List<Category> categories = categoryRepository.findAll();
        Stream.of("Medieval For Love For Honor.", "Boyka Undisputed", "Ip Man 4 The Finale", "cry Macho", "The Bear")
                .forEach(titleMovie -> {
                    Movie movie = new Movie();
                    movie.setTitle(titleMovie);
                    movie.setDuration(duration[new Random().nextInt(duration.length)]);
                    //I will use the same names as the movies
                    movie.setPhoto(titleMovie.replace(" ", "") + ".jpg");
                    //I will use the random category
                    movie.setCategory(categories.get(new Random().nextInt(categories.size())));
                    movieRepository.save(movie);
                });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[]{300, 500, 600, 700, 900, 1000};
        List<Movie> movies = movieRepository.findAll();
        cityRepository.findAll().forEach(city -> {
            city.getCinemas().forEach(cinema -> {
                cinema.getTheaters().forEach(theater -> {
                    int index = new Random().nextInt(movies.size());
                    Movie movie = movies.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                        movieProjection projection = new movieProjection();
                        projection.setProjectionDate(new Date());
                        projection.setMovie(movie);
                        projection.setPrice(prices[new Random().nextInt(prices.length)]);
                        projection.setTheater(theater);
                        projection.setSeance(seance);
                        projectionRepository.save(projection);
                    });

                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p -> {
            p.getTheater().getPositions().forEach(position -> {
                Ticket ticket = new Ticket();
                ticket.setPosition(position);
                ticket.setPrice(p.getPrice());
                ticket.setMovieProjection(p);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });
    }
}
