package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Tickets;
import com.ackerman.j.gavin.zootrack.Factory.TicketsFactory;

import java.sql.Date;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class TicketsFactoryImpl implements TicketsFactory {
    private static TicketsFactoryImpl factory = null;

    private TicketsFactoryImpl(){

    }

    public static TicketsFactoryImpl getInstance(){
        if (factory == null)
            factory = new TicketsFactoryImpl();

        return factory;
    }


    public Tickets createTickets(Long id,float price, Date day, String ticketType)
    {
        Tickets ticket = new Tickets.Builder().id(id).price(price).day(day).type(ticketType)
                .build();

        return ticket;
    }
}