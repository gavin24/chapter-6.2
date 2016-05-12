package com.ackerman.j.gavin.zootrack.Factory.Impl;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Factory.EmployeeFactory;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class EmployeeFactoryImpl implements EmployeeFactory {
    private static EmployeeFactoryImpl factory = null;

    private EmployeeFactoryImpl(){

    }

    public static EmployeeFactoryImpl getInstance(){
        if (factory == null)
            factory = new EmployeeFactoryImpl();

        return factory;
    }
    public Employee createEmployee(Long id,String name, String surname, int age,String Country,String email,String password)
    {
        Employee employee = new Employee.Builder().id(id).name(name).surname(surname).age(age).Country(Country).email(email).password(password)
                .build();

        return employee;
    }
}