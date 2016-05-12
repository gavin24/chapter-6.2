package com.ackerman.j.gavin.zootrack.services;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Domain.Food;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public interface EmployeeService  {
    Employee addEmployee(Employee employee);
    boolean isAuthentic(String userName,String Password);
}
