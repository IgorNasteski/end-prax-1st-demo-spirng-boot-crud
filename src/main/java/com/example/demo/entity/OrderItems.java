package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_items")
public class OrderItems {
                                                //POLJA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "product_id")
    private int productId;

    private int quantity;

    @Column(name = "list_price")
    private double listPrice;

    private int discount;

    @ManyToMany(mappedBy = "listOfOrdersItems")
    private List<Orders> listOfOrders = new ArrayList<Orders>();


    //konstruktor
    public OrderItems() {
    }

    public static class OrderItemsBuilder{

        private int itemId;
        private int productId;
        private int quantity;
        private double listPrice;
        private int discount;
        private List<Orders> listOfOrders = new ArrayList<Orders>();

        //KONSTRUKTOR
        public OrderItemsBuilder(){}

        public static OrderItemsBuilder anOrderItems(){
            return new OrderItemsBuilder();
        }


        //WITH
        public OrderItemsBuilder withItemId(int itemId){
            this.itemId = itemId;
            return this;
        }
        public OrderItemsBuilder withProductId(int productId){
            this.productId = productId;
            return this;
        }
        public OrderItemsBuilder withQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        public OrderItemsBuilder withListPrice(double listPrice){
            this.listPrice = listPrice;
            return this;
        }
        public OrderItemsBuilder withDiscount(int discount){
            this.discount = discount;
            return this;
        }
        public OrderItemsBuilder withListOfOrders(List<Orders> lsitOfOrders){
            this.listOfOrders = lsitOfOrders;
            return this;
        }

        public OrderItems build(){
            OrderItems orderItems = new OrderItems();
                orderItems.itemId = this.itemId;
                orderItems.productId = this.productId;
                orderItems.quantity = this.quantity;
                orderItems.listPrice = this.listPrice;
                orderItems.discount = this.discount;
                orderItems.listOfOrders = this.listOfOrders;

            return orderItems;
        }


    }



                                        //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItems that = (OrderItems) o;

        if (itemId != that.itemId) return false;
        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.listPrice, listPrice) != 0) return false;
        if (discount != that.discount) return false;
        return listOfOrders != null ? listOfOrders.equals(that.listOfOrders) : that.listOfOrders == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemId;
        result = 31 * result + productId;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(listPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + discount;
        result = 31 * result + (listOfOrders != null ? listOfOrders.hashCode() : 0);
        return result;
    }



                                        //SETERI I GETERI
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getListPrice() {
        return listPrice;
    }
    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public List<Orders> getListOfOrders() {
        return listOfOrders;
    }
    public void setListOfOrders(List<Orders> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

}
