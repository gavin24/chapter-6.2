package com.ackerman.j.gavin.zootrack.Domain;

import java.io.Serializable;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class Enclosure implements Serializable {
    private Show show;
    private String type;
    private String name;
    private String coach;
    private Long id;
    public Enclosure(Builder builder) {
        id=builder.id;
        type=builder.type;
        name=builder.name;
        coach=builder.coach;
        coach=builder.coach;
        show=builder.show;
    }
    public Show getshows() {
        return show;
    }
    public String getType() {
        return type;
    }

    public String getname() {
        return name;
    }

    public Long getId(){return id;}
    public String getCoach() {
        return coach;
    }



    public static class Builder{
        private String type;
        private String name;
        private Long id ;
        private String coach;

        private Show show;


        public Builder type(String value){
            this.type=value;
            return this;
        }
        public Builder name(String value){
            this.name=value;
            return this;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder coach(String value){
            this.coach=value;
            return this;
        }

        public Builder show(Show value){
            this.show=value;
            return this;
        }
        public Builder copy(Enclosure value){
            this.type=value.type;
            this.name=value.name;
            this.id=value.id;
            this.coach=value.coach;

            this.show=value.show;
            return this;
        }

        public Enclosure build(){
            return new Enclosure(this);
        }
    }
}
