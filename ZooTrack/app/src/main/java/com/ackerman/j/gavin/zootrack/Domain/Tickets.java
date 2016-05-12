package com.ackerman.j.gavin.zootrack.Domain;

//import java.sql.Date;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class Tickets implements Serializable {
    private long id;
    private float price;
    private Date day;
    private String type;


    public Tickets(Builder builder) {
        id=builder.id;
        price=builder.price;
        day=builder.day;
        type=builder.type;

    }

    public long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public Date getDate() {
        return day;
    }


    public String getType() {
        return type;
    }


    public static class Builder{
        private long id;
        private float price;
        private Date day;
        private String type;

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }


        public Builder price(float value){
            this.price=value;
            return this;
        }

        public Builder day(Date value){
            this.day=value;
            return this;
        }


        public Builder type(String value){
            this.type=value;
            return this;
        }

        public Builder copy(Tickets value){
            this.id=value.id;
            this.price=value.price;
            this.day=value.day;
            this.type=value.type;
            return this;
        }

        public Tickets build(){
            return new Tickets(this);
        }
    }
}
