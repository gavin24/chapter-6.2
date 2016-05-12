package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Enclosure;
import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Factory.EnclosureFactory;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class EnclosureFactoryImpl implements EnclosureFactory {
    private static EnclosureFactoryImpl factory = null;

    private EnclosureFactoryImpl(){

    }

    public static EnclosureFactoryImpl getInstance(){
        if (factory == null)
            factory = new EnclosureFactoryImpl();

        return factory;
    }
    public Enclosure createEnclosure(Long id,String type,String name, String coach, Show show)
    {
        Enclosure staff = new Enclosure.Builder().id(id).type(type).name(name).coach(coach).show(show)
                .build();

        return staff;
    }
}