package org.sid.cinema1.entities;
@org.springframework.data.rest.core.config.Projection(name = "ticketProjection",types={Ticket.class})
public interface ticketProjection {
    public Long getId();
    public String getClientName();
    public double getPrice();
    public Integer getPaymentCode();
    public boolean getReserved();
    public Position getPosition();
}
