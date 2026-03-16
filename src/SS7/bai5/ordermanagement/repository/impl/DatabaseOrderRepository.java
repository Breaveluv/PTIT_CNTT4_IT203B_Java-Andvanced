package ordermanagement.repository.impl;

import ordermanagement.model.Order;
import ordermanagement.repository.OrderRepository;
import java.util.*;

public class DatabaseOrderRepository implements OrderRepository {
    private Map<String, Order> database;

    public DatabaseOrderRepository() {
        this.database = new HashMap<>();
        System.out.println("DatabaseOrderRepository initialized (simulated in-memory database)");
    }

    @Override
    public void save(Order order) {
        database.put(order.getOrderId(), order);
        System.out.println("Order saved to database: " + order.getOrderId());
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(database.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void delete(String orderId) {
        database.remove(orderId);
        System.out.println("Order deleted from database: " + orderId);
    }

    @Override
    public void update(Order order) {
        database.put(order.getOrderId(), order);
        System.out.println("Order updated in database: " + order.getOrderId());
    }

    @Override
    public boolean exists(String orderId) {
        return database.containsKey(orderId);
    }
}