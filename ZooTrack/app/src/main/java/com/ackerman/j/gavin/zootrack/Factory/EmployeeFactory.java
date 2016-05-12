package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Employee;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface EmployeeFactory {

    Employee createEmployee(Long id,String name, String surname, int age,String Country,String password,String email);
}

