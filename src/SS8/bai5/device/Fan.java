package SS8.bai5.device;

import SS8.bai5.observer.Observer;

public class Fan implements Observer {
    public void low() {
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void high() {
        System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
    }
    
    public void off() {
        System.out.println("Quạt: Tắt");
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            high();
        }
    }
}
