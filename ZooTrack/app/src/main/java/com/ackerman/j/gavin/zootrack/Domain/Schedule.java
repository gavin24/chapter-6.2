package com.ackerman.j.gavin.zootrack.Domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class Schedule implements Serializable {
    private List<Show> show;
    private Long id;
    private String type;
    private String duration;
    private String coach;


    public Schedule(Builder builder) {
        id=builder.id;
        type=builder.type;
        duration=builder.duration;
        coach=builder.coach;
        show=builder.show;
    }

    public String getType() {
        return type;
    }

    public String getDuration() {
        return duration;
    }
    public Long getId() {
        return id;
    }
    public List<Show> getshows() {
        return show;
    }
    public String getCoach() {
        return coach;
    }



    public static class Builder{
        private String type;
        private String duration;
        private String coach;
        List<Show> show;
        private Long id;
        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder duration(String value){
            this.duration=value;
            return this;
        }
        public Builder type(String value){
            this.type=value;
            return this;
        }


        public Builder coach(String value){
            this.coach=value;
            return this;
        }
        public Builder show(List<Show> show) {
            this.show = show;
            return this;
        }
        /*   public Builder show(Show value){
               this.show=value;
               return this;
           }*/
        public Builder copy(Schedule value){
            this.type=value.type;
            this.duration=value.duration;
            this.id=value.id;
            this.coach=value.coach;

            this.show=value.show;
            return this;
        }

        public Schedule build(){
            return new Schedule(this);
        }
    }
}
