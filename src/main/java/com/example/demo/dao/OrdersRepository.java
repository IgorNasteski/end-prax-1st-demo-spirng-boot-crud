package com.example.demo.dao;

import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByOrderIdOrCustomerOrOrderStatusOrOrderDateOrRequiredDateOrShippedDate(int orderId, Customers customer, String orderStatus, Date orderDate, Date requiredDate, Date shippedDate);
    List<Orders> findByOrderIdOrOrderStatusOrOrderDateOrRequiredDateOrShippedDate(int orderId, String orderStatus, Date orderDate, Date requiredDate, Date shippedDate);
    //List<Orders> findByOrderId(int orderId);

    //radi testa kreirao, cisto da vidim da lice da radi search sa stringom
    //jer ove odozgo nije htelo
    //posle testa - ni ovo nije htelo
    //HTELO JE KADA SAM OBRISAO cascase merge U ENTITY KLASAMA
    List<Orders> findByOrderStatus(String orderStatus);
    //List<Orders> findByOrderIdOrOrderStatus(int orderId, String orderStatus);
    List<Orders> findByOrderIdOrOrderStatus(int orderId, String orderStatus);
    List<Orders> findByOrderDate(String orderDate);
        //List<Orders> findByOrderDateOrOrderId(String orderDate, int orderId);
    //List<Orders> findByOrderId(int orderId);
        //List<Orders> findByOrderIdOrOrderDate(int orderId, String orderDate);

            //nece u kombinaciji sa statusom koji je isto String kao i ostali datumi??
            //prijavljuje java.sql.SQLException: Incorrect DATE value: 'OK'
    List<Orders> findByOrderStatusOrOrderDateOrRequiredDateOrShippedDate(String status, String orderDate, String requiredDate, String shippedDate);
    List<Orders> findByOrderDateOrRequiredDateOrShippedDate(String orderDate, String requiredDate, String shippedDate);


    //@Query("SELECT c FROM Customers c WHERE c.firstName LIKE %?1")



    //POENTA JE BILA DA NADJEM SVE ORDERSE CIJI JE ORDER STATUS "IN TRANSIT" I
    //KOJI SU SHIPPED U POSLEDNJIH 7 DANA. OVDE SAM PROSLEDIO PARAMETAR
    //today A U KONTROLERU SAM DOHVATIO TRENUTAN DATUM I UMANJIO GA ZA 7 DANA
    //PA SAM ONDA POREDIO SA DATUMIMA SHIPPED IZ BAZE
    //AKO JE DATUM SHIPPED IZ BAZE VECI NEGO OVAJ today KOJI JE UMANJEN OD
    //TRENUTNOG DATUMA ZA 7 DANA, ONDA IZBACI TE ORDERE

        //radi, za sad samo za datume manje od danasnjeg i za one ciji je order status "in transit"
    // @Query("SELECT o from Orders o WHERE o.shippedDate < :today AND o.orderStatus LIKE 'in transit'")
    @Query("SELECT o from Orders o WHERE o.shippedDate >= :today AND o.orderStatus LIKE 'in transit'")//ne mogu stringove oduzimati...
    List<Orders> findByShippedDate(@Param("today")String today);
        //posto sam trenutno vreme dohvatio i smestio u String today u kontroleru gde pozivam ovu metodu
        //da bih ga prosledio ovako, moram da dodam @Param("today") da bi ga vezao








    //@Query("SELECT o from Orders o WHERE o.shippedDate > :shippedDate AND o.orderStatus LIKE 'in transit'")
    //List<Orders> findByShippedDate(String shippedDate);

    //@Query("SELECT o from Orders o WHERE o.shippedDate > :shippedDate AND o.orderStatus LIKE 'in transit'")
    //List<Orders> findByShippedDate(@Param("shippedDate")String shippedDate);


    //just for test
    @Query("SELECT o from Orders o WHERE o.orderStatus LIKE 'in transit'")
    List<Orders> findsss();
























}
