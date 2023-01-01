package org.sid.cinema1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Theater implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    private int nbrPosition;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cinema cinema;
    @OneToMany(mappedBy = "theater")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Position> positions;
    @OneToMany(mappedBy = "theater")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<movieProjection> movieProjections;
}
