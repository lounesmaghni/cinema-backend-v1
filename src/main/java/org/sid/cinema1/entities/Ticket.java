package org.sid.cinema1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor @ToString
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String clientName;
    private double price;
    @Column(unique = false)
    private Integer paymentCode;
    private boolean reserved;
    @ManyToOne
    private Position position;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private movieProjection movieProjection;
}
