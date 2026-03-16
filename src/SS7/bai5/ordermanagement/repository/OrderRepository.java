package ordermanagement.repository;

import ordermanagement.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findById(String orderId);

    List<Order> findAll();

    void delete(String orderId);

    void update(Order order);

    boolean exists(String orderId);
}