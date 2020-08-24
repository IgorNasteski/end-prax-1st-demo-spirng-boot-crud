package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customers {

                                    //POLA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    private String email;

    private String street;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "order_counter")
    private int orderCounter;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Orders> listOfOrders = new ArrayList<Orders>();

    //DODATNO POLJE KOJE NECU CUVATI ILI DODAVATI U BAZU, CISTO RADI ISPISIVANJA U THYMELEAF STRANICI
    @Transient
    private String firstNameLastName;





                                    //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        if (customerId != customers.customerId) return false;
        if (firstName != null ? !firstName.equals(customers.firstName) : customers.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customers.lastName) : customers.lastName != null) return false;
        if (phone != null ? !phone.equals(customers.phone) : customers.phone != null) return false;
        if (email != null ? !email.equals(customers.email) : customers.email != null) return false;
        if (street != null ? !street.equals(customers.street) : customers.street != null) return false;
        if (city != null ? !city.equals(customers.city) : customers.city != null) return false;
        if (state != null ? !state.equals(customers.state) : customers.state != null) return false;
        if (zipCode != null ? !zipCode.equals(customers.zipCode) : customers.zipCode != null) return false;
        return listOfOrders != null ? listOfOrders.equals(customers.listOfOrders) : customers.listOfOrders == null;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (listOfOrders != null ? listOfOrders.hashCode() : 0);
        return result;
    }


    //konstruktor
    public Customers() {

    }
                                    //BUILDER PATTERN
    public static class CustomersBuilder{
        private int customerId;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private int orderCounter;
        private List<Orders> listOfOrders = new ArrayList<Orders>();
        private String firstNameLastName;

        //konstruktor
        public CustomersBuilder(){}

        public static CustomersBuilder anCustomer(){
            return new CustomersBuilder();
        }


        //WITH
        public CustomersBuilder withCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }
        public CustomersBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withPhone(String phone) {
            this.phone = phone;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withEmail(String email) {
            this.email = email;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withStreet(String street) {
            this.street = street;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withCity(String city) {
            this.city = city;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withState(String state) {
            this.state = state;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
        public CustomersBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }

        public CustomersBuilder withOrderCounter(int orderCounter){
            this.orderCounter = orderCounter;
            return this;
        }

        public CustomersBuilder withListOfOrders(List<Orders> listOfOrders) {
            this.listOfOrders = listOfOrders;
            return this;  //By returning the builder each time, we can create a fluent interface.
        }

        public CustomersBuilder withFirstNameLastName(String firstNameLastName){
            this.firstNameLastName = firstNameLastName;
            return this;
        }

        //konstruktor glavne klase "Customers" --- ovde je public tamo je public
        public Customers build(){
            Customers customers = new Customers();
                customers.customerId = this.customerId;
                customers.firstName = this.firstName;
                customers.lastName = this.lastName;
                customers.phone = this.phone;
                customers.email = this.email;
                customers.street = this.street;
                customers.city = this.city;
                customers.state = this.state;
                customers.zipCode = this.zipCode;
                customers.orderCounter = this.orderCounter;
                customers.listOfOrders = this.listOfOrders;
                customers.firstNameLastName = this.firstNameLastName;

            return customers;
        }
    }


                                    //GETERI I SETERI JER LOMBOK ZEZA

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public List<Orders> getListOfOrders() {
        return listOfOrders;
    }
    public void setListOfOrders(List<Orders> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }
    public int getOrderCounter() {
        return orderCounter;
    }
    public void setOrderCounter(int orderCounter) {
        this.orderCounter = orderCounter;
    }


    //@Transient polje koje ne koristim u bazi, cisto dodato radi ispisa imena i prezimena na jednoj stranici
    public String getFirstNameLastName() {
        return getFirstName() + " " + getLastName();
    }

    public void setFirstNameLastName(String firstNameLastName) {
        this.firstNameLastName = firstNameLastName;
    }
}
