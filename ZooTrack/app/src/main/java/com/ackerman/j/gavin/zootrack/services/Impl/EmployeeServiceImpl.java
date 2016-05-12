package com.ackerman.j.gavin.zootrack.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Repository.EmployeeRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.EmployeeRepositoryImpl;
import com.ackerman.j.gavin.zootrack.services.EmployeeService;

import java.io.IOException;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-05-08.
 */
public class EmployeeServiceImpl extends Service implements EmployeeService {
    final private EmployeeRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static EmployeeServiceImpl service = null;

    public static EmployeeServiceImpl getInstance()
    {
        if(service == null)
            service = new EmployeeServiceImpl();
        return service;
    }

    private EmployeeServiceImpl()
    {
        repository = new EmployeeRepositoryImpl(this.getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public EmployeeServiceImpl getService() {
            return EmployeeServiceImpl.this;
        }
    }
    public boolean isAuthentic(String username,String password)
    {
        Set<Employee> employees;
        employees = repository.findAll();

        for (Employee employee : employees)

        {
            if (employee.getEmail().equalsIgnoreCase(username) && employee.getPassword().equals(password)) {
                return true;
            }

        }
        return false;
    }
}
