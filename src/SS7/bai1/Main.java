package SS7.bai1;


public class Main {
    public static void main(String[] args) {
        Product sp1 = new Product("SP01", "Laptop", 15000000);
        Product sp2 = new Product("SP02", "Chuột", 300000);
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer("Nguyen Văn A","A@gmail.com","Hà Nội");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001",customer);
        order.addProduct(sp1);
        order.addProduct(sp2);
        order.addProduct(sp2);
        System.out.println("Đơn hàng đã được tạo");

        OderCalculator od = new OderCalculator();
        double result = od.calculateTotal(order);
        System.out.println("Tổng số tiền: "+(long)result );

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.save(order);

        EmailService emailService = new EmailService();
        emailService.sendEmail(order);
    }
    
    
}
