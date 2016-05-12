package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Employee;
import com.ackerman.j.gavin.zootrack.Domain.Staff;
import com.ackerman.j.gavin.zootrack.Repository.Impl.StaffRepositoryImpl;
import com.ackerman.j.gavin.zootrack.Repository.StaffRepository;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class StaffRepoTest extends AndroidTestCase {
    private static final String TAG="Staff TEST";
    private Long id;
    private Date day = new Date(14/06/2014);
    private Date updatedDay = new Date(13/06/2016);
    public void testCreateReadUpdateDelete() throws Exception {
        StaffRepository repo = new StaffRepositoryImpl(this.getContext());
        // CREATE
        Staff createEntity = new Staff.Builder()
                .Day(day)
                .build();
        Staff insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Staff> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Staff entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Staff updateEntity = new Staff.Builder()
                .copy(entity)
                .Day(updatedDay)
                .build();
        repo.update(updateEntity);
        Staff newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",updatedDay,newEntity.getDay());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Staff deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
