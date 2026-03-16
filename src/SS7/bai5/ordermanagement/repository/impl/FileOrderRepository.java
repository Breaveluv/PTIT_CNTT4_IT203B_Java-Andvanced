package ordermanagement.repository.impl;

import ordermanagement.model.Order;
import ordermanagement.repository.OrderRepository;
import java.io.*;
import java.util.*;

public class FileOrderRepository implements OrderRepository {
    private static final String FILE_PATH = "orders.dat";
    private Map<String, Order> orders;

    public FileOrderRepository() {
        this.orders = loadFromFile();
    }

    @Override
    public void save(Order order) {
        orders.put(order.getOrderId(), order);
        saveToFile();
        System.out.println("Order saved to file: " + order.getOrderId());
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void delete(String orderId) {
        orders.remove(orderId);
        saveToFile();
        System.out.println("Order deleted from file: " + orderId);
    }

    @Override
    public void update(Order order) {
        orders.put(order.getOrderId(), order);
        saveToFile();
        System.out.println("Order updated in file: " + order.getOrderId());
    }

    @Override
    public boolean exists(String orderId) {
        return orders.containsKey(orderId);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Order> loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Map<String, Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from file: " + e.getMessage());
            return new HashMap<>();
        }
    }
}