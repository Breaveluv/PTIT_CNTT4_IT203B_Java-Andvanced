package SS7.bai4;

public class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào database: " + order.getOrderId());
    }

    @Override
    public void findAll() {
        System.out.println("Tìm tất cả đơn hàng từ database.");
    }
}
