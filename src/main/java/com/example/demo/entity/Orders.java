package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
                                    //POLJA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_date")
    //@Value("${new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${PROP_DATE}\")}")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    //@Temporal(TemporalType.DATE)
    private String orderDate;

    @Column(name = "required_date")
    //@Value("${new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${PROP_DATE}\")}")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    //@Temporal(TemporalType.DATE)
    private String requiredDate;

    @Column(name = "shipped_date")
    //@Value("${new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${PROP_DATE}\")}")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    //@Temporal(TemporalType.DATE)
    private String shippedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //, cascade = CascadeType.MERGE
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @ManyToMany(fetch = FetchType.EAGER)//, cascade = CascadeType.MERGE
    @JoinTable(
            name = "orders_order_items",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<OrderItems> listOfOrdersItems = new ArrayList<OrderItems>();


    //konstruktor
    public Orders() {
    }

    public static class OrdersBuilder{

        private int orderId;
        private String orderStatus;
        private String orderDate;
        private String requiredDate;
        private String shippedDate;
        private Customers customer;
        private List<OrderItems> listOfOrdersItems = new ArrayList<OrderItems>();

        //KONSTRUKTORI
        public OrdersBuilder(){}

        public static OrdersBuilder anOrders(){
            return new OrdersBuilder();
        }

        //WITH
        public OrdersBuilder withOrderId(int orderId){
            this.orderId = orderId;
            return this;
        }
        public OrdersBuilder withOrderStatus(String orderStatus){
            this.orderStatus = orderStatus;
            return this;
        }
        public OrdersBuilder withOrderDate(String orderDate){
            this.orderDate = orderDate;
            return this;
        }
        public OrdersBuilder withRequiredDate(String requiredDate){
            this.requiredDate = requiredDate;
            return this;
        }
        public OrdersBuilder withShippedDate(String shippedDate){
            this.shippedDate = shippedDate;
            return this;
        }
        public OrdersBuilder withCustomer(Customers customer){
            this.customer = customer;
            return this;
        }
        public OrdersBuilder withListOfOrdersItems(List<OrderItems> listOfOrdersItems){
            this.listOfOrdersItems = listOfOrdersItems;
            return this;
        }


        //BUILD
        public Orders build(){
            Orders orders = new Orders();
                orders.orderId = this.orderId;
                orders.orderStatus = this.orderStatus;
                orders.orderDate = this.orderDate;
                orders.requiredDate = this.requiredDate;
                orders.shippedDate = this.shippedDate;
                orders.customer = this.customer;
                orders.listOfOrdersItems = this.listOfOrdersItems;

            return orders;
        }
    }









                                                //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (orderId != orders.orderId) return false;
        if (orderStatus != null ? !orderStatus.equals(orders.orderStatus) : orders.orderStatus != null) return false;
        if (orderDate != null ? !orderDate.equals(orders.orderDate) : orders.orderDate != null) return false;
        if (requiredDate != null ? !requiredDate.equals(orders.requiredDate) : orders.requiredDate != null)
            return false;
        if (shippedDate != null ? !shippedDate.equals(orders.shippedDate) : orders.shippedDate != null) return false;
        if (customer != null ? !customer.equals(orders.customer) : orders.customer != null) return false;
        return listOfOrdersItems != null ? listOfOrdersItems.equals(orders.listOfOrdersItems) : orders.listOfOrdersItems == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (requiredDate != null ? requiredDate.hashCode() : 0);
        result = 31 * result + (shippedDate != null ? shippedDate.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (listOfOrdersItems != null ? listOfOrdersItems.hashCode() : 0);
        return result;
    }





                                                //SETERI I GETERI
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getRequiredDate() {
        return requiredDate;
    }
    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }
    public String getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }
    public Customers getCustomer() {
        return customer;
    }
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
    public List<OrderItems> getListOfOrdersItems() {
        return listOfOrdersItems;
    }
    public void setListOfOrdersItems(List<OrderItems> listOfOrdersItems) {
        this.listOfOrdersItems = listOfOrdersItems;
    }
}
