package SS7.bai1;

class EmailService {
    public void sendEmail(Order order) {
        System.out.println("Đã gửi email đến " + order.customer.email +
                ": Đơn hàng " + order.orderId + " đã được tạo");
    }
}
