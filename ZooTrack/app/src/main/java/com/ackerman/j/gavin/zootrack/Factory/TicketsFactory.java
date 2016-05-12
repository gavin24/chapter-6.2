package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Tickets;

import java.sql.Date;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface TicketsFactory {
    Tickets createTickets(Long id,float price, Date day, String ticketType);
}