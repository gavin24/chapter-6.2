package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Schedule;
import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Factory.ScheduleFactory;

import java.util.List;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class ScheduleFactoryImpl implements ScheduleFactory {
    private static ScheduleFactoryImpl factory = null;

    private ScheduleFactoryImpl(){

    }

    public static ScheduleFactoryImpl getInstance(){
        if (factory == null)
            factory = new ScheduleFactoryImpl();

        return factory;
    }
    public Schedule createSchedule(Long id,List<Show> show,String type, String duration, String coach)
    {
        Schedule schedule = new Schedule.Builder().id(id).type(type).duration(duration).coach(coach).show(show)
                .build();

        return schedule;
    }
}