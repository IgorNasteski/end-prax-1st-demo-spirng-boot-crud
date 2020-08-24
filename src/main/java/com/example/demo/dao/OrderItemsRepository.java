package com.example.demo.dao;

import com.example.demo.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findByItemId(int itemId);
    List<OrderItems> findByItemIdOrProductId(int itemId, int productId);
    List<OrderItems> findByItemIdOrProductIdOrQuantity(int itemId, int productId, int quantity);
    List<OrderItems> findByItemIdOrProductIdOrQuantityOrDiscount(int itemId, int productId, int quantity, int discount);

            //nece jer sam kombinovao sve intove i jedan double
        //List<OrderItems> findByItemIdOrProductIdOrQuantityOrListPriceOrDiscount(int itemId, int productId, int quantity, double listPrice, int discount);

}
