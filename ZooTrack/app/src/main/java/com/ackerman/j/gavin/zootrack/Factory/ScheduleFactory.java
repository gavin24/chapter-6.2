package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Schedule;
import com.ackerman.j.gavin.zootrack.Domain.Show;

import java.util.List;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface ScheduleFactory {

    Schedule createSchedule(Long id,List<Show> show,String type, String duration, String coach);
}