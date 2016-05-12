package com.ackerman.j.gavin.zootrack.Repository;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public interface Repository<E, ID> {
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
