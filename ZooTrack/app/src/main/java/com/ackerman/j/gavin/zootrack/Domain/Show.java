package com.ackerman.j.gavin.zootrack.Domain;
import java.io.Serializable;
import java.util.Date;
//import java.util.Time
//import java.sql.Date;
import java.sql.Time;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class Show implements Serializable {
    private Long id;
    private String name;
    private Date showDay;
   // private Time showTime;

    public Show(Builder builder) {
        id=builder.id;
        name=builder.name;
        showDay=builder.showDay;
      //  showTime=builder.showTime;
    }



    public String getname() {
        return name;
    }
    public Long getId(){return id;}
    public Date getDay() {
        return showDay;
    }

   // public Time getTime() {
    //    return showTime;
   // }

    public static class Builder{
        private Long id;
        private String name;
        private Date showDay;
     //   private Time showTime;


        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }
        public Builder day(Date value){
            this.showDay=value;
            return this;
        }
        public Builder name(String value){
            this.name=value;
            return this;
        }
      /*  public Builder time(Time value){
            this.showTime=value;
            return this;
        }*/

        public Builder copy(Show value){
            this.id=value.id;
            this.name=value.name;
            this.showDay=value.showDay;
         //   this.showTime=value.showTime;
            return this;
        }

        public Show build(){
            return new Show(this);
        }
    }
}
