package com.ackerman.j.gavin.zootrack.Domain;

import java.io.Serializable;

/**
 * Created by gavin.ackerman on 2016-04-15.
 */
public class Animal implements Serializable {
    private String name;
    private long id;
    private String species;
    private int age;
    private String Country;
    private Food food;

    public Animal() {
    }



    public int getAge(){return age;}
    public long getId() {
        return id;
    }
    public String getName(){return name;}
    public String getSpecies(){return species;}
    public Food getFood() {
        return food;
    }

    public String getCountry(){return Country;}

    public Animal(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.species=builder.species;
        this.age=builder.age;
        this.food=builder.food;
        this.Country=builder.Country;

    }

    public static class Builder{
        private long id;
        private String name;
        private String species;
        private int age;
        private Food food;
        private String Country;

        public Builder() {

        }


        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder food(Food value){
            this.food=value;
            return this;
        }
        public Builder species(String value)
        {
            this.species=value;
            return this;
        }



        public Builder Country(String value)
        {
            this.Country=value;
            return this;
        }

        public Builder age(int value){
            this.age=value;
            return this;

        }



        public Builder copy(Animal value){
            this.id=value.id;
            this.name=value.name;
            this.food=value.food;
            this.age=value.age;
            this.Country=value.Country;

            return this;
        }

        public Animal build(){
            return new Animal(this);
        }
    }
}
