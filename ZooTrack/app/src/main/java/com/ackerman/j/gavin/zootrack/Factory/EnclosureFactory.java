package com.ackerman.j.gavin.zootrack.Factory;

import com.ackerman.j.gavin.zootrack.Domain.Enclosure;
import com.ackerman.j.gavin.zootrack.Domain.Show;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public interface EnclosureFactory {

    Enclosure createEnclosure(Long id,String type,String name, String coach, Show show);
}
