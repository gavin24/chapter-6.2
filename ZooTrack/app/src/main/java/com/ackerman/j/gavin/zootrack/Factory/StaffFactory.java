package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Domain.Staff;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface StaffFactory {


    Staff createStaff(Long id,Date workDay,List<Employee> employee);
}
