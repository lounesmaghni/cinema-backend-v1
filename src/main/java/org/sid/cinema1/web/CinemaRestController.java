package org.sid.cinema1.web;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.sid.cinema1.dao.MovieRepository;
import org.sid.cinema1.dao.TicketRepository;
import org.sid.cinema1.entities.Movie;
import org.sid.cinema1.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/imageMovie/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception {
        Movie m = movieRepository.findById(id).get();
        String photoName = m.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payedTickets")
    @Transactional
    public List<Ticket> payedTickets(@RequestBody TicketFormm ticketFrom) {
        List<Ticket> ticketList = new ArrayList<>();
        ticketFrom.getTickets().forEach(idTicket -> {
           // System.out.println(idTicket);
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setClientName(ticketFrom.getClientName());
            ticket.setReserved(true);
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }
}

@Data
class TicketFormm {
    private String clientName;
    private int paymentCode;
    private List<Long> tickets = new ArrayList<>();
}