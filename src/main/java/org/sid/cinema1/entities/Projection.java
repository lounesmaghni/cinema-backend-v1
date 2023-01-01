package org.sid.cinema1.entities;

import java.util.Collection;
import java.util.Date;

@org.springframework.data.rest.core.config.Projection(name = "p1",types={movieProjection.class})
public interface Projection {
    public Long getId();
    public double getPrice();
    public Date getProjectionDate();
    public Theater getTheater();
    public Movie getMovie();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
