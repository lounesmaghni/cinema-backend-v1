package org.sid.cinema1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 20)
    private String longitude,latitude,altitude;
    @Column(length = 20)
    private int nbrTheater;
    @OneToMany(mappedBy = "cinema")
    private Collection<Theater> theaters;
    @ManyToOne
    private City city;
}
