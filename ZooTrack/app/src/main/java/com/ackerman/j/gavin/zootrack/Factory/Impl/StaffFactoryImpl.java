package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Domain.Staff;
import com.ackerman.j.gavin.zootrack.Factory.StaffFactory;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class StaffFactoryImpl implements StaffFactory {
    private static StaffFactoryImpl factory = null;

    private StaffFactoryImpl(){

    }

    public static StaffFactoryImpl getInstance(){
        if (factory == null)
            factory = new StaffFactoryImpl();

        return factory;
    }
    public Staff createStaff(Long id,Date workDay,List<Employee> employee)
    {
        Staff staff = new Staff.Builder().id(id).Day(workDay).employee(employee)
                .build();

        return staff;
    }
}