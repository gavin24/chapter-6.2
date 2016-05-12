package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Factory.ShowFactory;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class ShowFactoryImpl implements ShowFactory {
    private static ShowFactoryImpl factory = null;

    private ShowFactoryImpl(){

    }

    public static ShowFactoryImpl getInstance(){
        if (factory == null)
            factory = new ShowFactoryImpl();

        return factory;
    }
    public Show createShow(Long id,String name, Date showDay)
    {
        Show show = new Show.Builder().id(id).name(name).day(showDay)
                .build();

        return show;
    }
}