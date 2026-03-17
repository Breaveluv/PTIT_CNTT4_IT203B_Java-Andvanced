package SS8.bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị mới");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = 0;
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ.");
                continue;
            }

            switch (choice) {
                case 1:
                    HardwareConnection.getInstance();
                    break;
                case 2:
                    System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa");
                    System.out.print("Chọn: ");
                    int typeChoice = 0;
                    try {
                        typeChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Lựa chọn không hợp lệ.");
                        break;
                    }

                    DeviceFactory factory = null;
                    if (typeChoice == 1) {
                        factory = new LightFactory();
                    } else if (typeChoice == 2) {
                        factory = new FanFactory();
                    } else if (typeChoice == 3) {
                        factory = new AirConditionerFactory();
                    } else {
                        System.out.println("Loại thiết bị không hợp lệ.");
                    }

                    if (factory != null) {
                        Device device = factory.createDevice();
                        devices.add(device);
                    }
                    break;
                case 3:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị nào được tạo.");
                    } else {
                        System.out.println("Danh sách thiết bị:");
                        for (int i = 0; i < devices.size(); i++) {
                            System.out.println((i + 1) + ". " + devices.get(i).getClass().getSimpleName());
                        }
                        System.out.print("Chọn thiết bị vừa tạo: ");
                        int deviceIndex = -1;
                        try {
                            deviceIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Lựa chọn không hợp lệ.");
                            break;
                        }

                        if (deviceIndex >= 0 && deviceIndex < devices.size()) {
                            Device selectedDevice = devices.get(deviceIndex);
                            selectedDevice.turnOn();
                        } else {
                            System.out.println("Thiết bị không tồn tại.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
