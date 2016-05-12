package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Tickets;
import com.ackerman.j.gavin.zootrack.Repository.Impl.TicketsRepositoryImpl;
import com.ackerman.j.gavin.zootrack.Repository.TicketsRepository;

import junit.framework.Assert;

import java.util.Date;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class TicketsRepoTest extends AndroidTestCase {
    private static final String TAG="Tickets TEST";
    private Long id;
    private Date day = new Date(14/06/2014);
    public void testCreateReadUpdateDelete() throws Exception {
        TicketsRepository repo = new TicketsRepositoryImpl(this.getContext());
        // CREATE
        Tickets createEntity = new Tickets.Builder()
                .price(56)
                .day(day)
                .type("adult")
                .build();
        Tickets insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Tickets> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Tickets entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Tickets updateEntity = new Tickets.Builder()
                .copy(entity)
                .type("child")
                .build();
        repo.update(updateEntity);
        Tickets newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","child",newEntity.getType());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Tickets deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
