package org.sid.cinema1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class movieProjection implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date projectionDate;
    private double price;
    @ManyToOne
    private Theater theater;
    @ManyToOne
    private Movie movie;
    @OneToMany(mappedBy = "movieProjection")
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;
}
