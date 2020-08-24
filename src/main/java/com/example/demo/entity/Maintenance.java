package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String days;

    private String hours;


    //konstruktor
    public Maintenance() {
    }

    public static class MaintenanceBuilder{

        private int id;
        private String days;
        private String hours;

        //konstruktor
        public MaintenanceBuilder(){}

        public static MaintenanceBuilder anMaintenance(){
            return new MaintenanceBuilder();
        }


        public MaintenanceBuilder withId(int id){
            this.id = id;
            return this;
        }
        public MaintenanceBuilder withDays(String days){
            this.days = days;
            return this;
        }
        public MaintenanceBuilder withHours(String hours){
            this.hours = hours;
            return this;
        }


        //BUILD
        public Maintenance build(){
            Maintenance maintenance = new Maintenance();
                maintenance.id = this.id;
                maintenance.days = this.days;
                maintenance.hours = this.hours;

            return maintenance;
        }

    }


                                    //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance that = (Maintenance) o;

        if (id != that.id) return false;
        if (days != null ? !days.equals(that.days) : that.days != null) return false;
        return hours != null ? hours.equals(that.hours) : that.hours == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        return result;
    }

                                    //SETERI I GETERI
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }
}
