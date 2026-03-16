package SS7.bai1;

class OderCalculator {
    public double calculateTotal(Order order) {
        double total = 0;
        for (Product product : order.products) {
            total += product.price;
        }
        order.totalAmount = total;
        return total;
    }
}
