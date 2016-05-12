package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Schedule;
import com.ackerman.j.gavin.zootrack.Repository.Impl.ScheduleRepositoryImpl;
import com.ackerman.j.gavin.zootrack.Repository.ScheduleRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class ScheduleRepoTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ScheduleRepository repo = new ScheduleRepositoryImpl(this.getContext());
        // CREATE    (Long id,List<Show> show,String type, String duration, String coach
        Schedule createEntity = new Schedule.Builder()
                .type("daily")
                .duration("4 hours")
                .coach("larry")
                .build();
        Schedule insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Schedule> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Schedule entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Schedule updateEntity = new Schedule.Builder()
                .copy(entity)
                .coach("john")
                .build();
        repo.update(updateEntity);
        Schedule newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","john",newEntity.getCoach());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Schedule deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
