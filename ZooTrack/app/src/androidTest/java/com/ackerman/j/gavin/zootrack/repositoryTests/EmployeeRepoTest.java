package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Repository.EmployeeRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.EmployeeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class EmployeeRepoTest extends AndroidTestCase {
    private static final String TAG="Employee TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());
        // CREATE
        Employee createEntity = new Employee.Builder()
                .name("Alec")
                .surname("James")
                .age(34)
                .Country("SA")
                .password("w32352d")
                .email("blah@gmail.com")
                .build();
        Employee insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Employee> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Employee entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Employee updateEntity = new Employee.Builder()
                .copy(entity)
                .name("Peter")
                .build();
        repo.update(updateEntity);
        Employee newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Peter",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Employee deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
