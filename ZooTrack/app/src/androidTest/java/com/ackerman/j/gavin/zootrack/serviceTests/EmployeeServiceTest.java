package com.ackerman.j.gavin.zootrack.serviceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.services.Impl.EmployeeServiceImpl;

/**
 * Created by gavin.ackerman on 2016-05-11.
 */
public class EmployeeServiceTest extends AndroidTestCase {
    private EmployeeServiceImpl employeeService;
    private boolean isBound;
    Employee employee = new Employee();
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), EmployeeServiceImpl.class);
        this.mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        //Create
        employee = new Employee.Builder()
                .name("Alec")
                .surname("James")
                .age(34)
                .Country("SA")
                .password("w32352d")
                .email("blah@gmail.com")
                .build();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EmployeeServiceImpl.ActivateServiceLocalBinder binder
                    = (EmployeeServiceImpl.ActivateServiceLocalBinder) service;
            employeeService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };


}
