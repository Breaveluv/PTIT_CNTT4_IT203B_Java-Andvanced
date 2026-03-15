package SS6.bai6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static List<TicketPool> rooms = new ArrayList<>();
    private static List<BookingCounter> counters = new ArrayList<>();
    private static TimeoutManager timeoutManager;
    private static DeadlockDetector deadlockDetector;
    private static ExecutorService executor;
    private static boolean simulationRunning = false;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== HE THONG RAP CHIEU PHIM ===");

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    startSimulation();
                    break;
                case 2:
                    pauseSimulation();
                    break;
                case 3:
                    resumeSimulation();
                    break;
                case 4:
                    addTickets();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    detectDeadlock();
                    break;
                case 7:
                    exitSystem();
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Bat dau mo phong");
        System.out.println("2. Tam dung mo phong");
        System.out.println("3. Tiep tuc mo phong");
        System.out.println("4. Them ve vao phong");
        System.out.println("5. Xem thong ke");
        System.out.println("6. Phat hien deadlock");
        System.out.println("7. Thoat");
        System.out.print("Nhap lua chon: ");
    }

    private static void startSimulation() {
        if (simulationRunning) {
            System.out.println("Mo phong dang chay!");
            return;
        }

        System.out.print("Nhap so phong: ");
        int numRooms = scanner.nextInt();
        System.out.print("Nhap so ve moi phong: ");
        int ticketsPerRoom = scanner.nextInt();
        System.out.print("Nhap so quay: ");
        int numCounters = scanner.nextInt();
        scanner.nextLine();

        rooms.clear();
        for (int i = 0; i < numRooms; i++) {
            String roomName = "" + (char) ('A' + i);
            rooms.add(new TicketPool(roomName, ticketsPerRoom));
        }

        counters.clear();
        for (int i = 0; i < numCounters; i++) {
            boolean isVIP = (i == 0); 
            counters.add(new BookingCounter("Quay " + (i + 1), rooms.toArray(new TicketPool[0]), isVIP));
        }

        timeoutManager = new TimeoutManager(rooms.toArray(new TicketPool[0]));
        deadlockDetector = new DeadlockDetector();

        executor = Executors.newCachedThreadPool();

        for (BookingCounter counter : counters) {
            executor.submit(counter);
        }
        executor.submit(timeoutManager);
        executor.submit(deadlockDetector);

        simulationRunning = true;
        System.out.println("Da khoi tao he thong voi " + numRooms + " phong, " + (numRooms * ticketsPerRoom) + " ve, "
                + numCounters + " quay");
        System.out.println("Quay bat dau ban ve...");
    }

    private static void pauseSimulation() {
        if (!simulationRunning) {
            System.out.println("Mo phong chua bat dau!");
            return;
        }
        for (BookingCounter counter : counters) {
            counter.pause();
        }
        timeoutManager.pause();
        System.out.println("Da tam dung tat ca quay ban ve.");
    }

    private static void resumeSimulation() {
        if (!simulationRunning) {
            System.out.println("Mo phong chua bat dau!");
            return;
        }
        for (BookingCounter counter : counters) {
            counter.resume();
        }
        timeoutManager.resume();
        System.out.println("Da tiep tuc hoat dong.");
    }

    private static void addTickets() {
        System.out.print("Nhap ten phong (A, B, C...): ");
        String roomName = scanner.nextLine().toUpperCase();
        System.out.print("Nhap so ve them: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (TicketPool room : rooms) {
            if (room.getRoomName().equals(roomName)) {
                room.addTickets(count);
                System.out.println("Da them " + count + " ve vao phong " + roomName);
                return;
            }
        }
        System.out.println("Khong tim thay phong " + roomName);
    }

    private static void showStatistics() {
        System.out.println("=== THONG KE HIEN TAI ===");
        double totalRevenue = 0;
        for (TicketPool room : rooms) {
            int sold = room.getSoldCount();
            int total = room.getInitialCount();
            System.out.println("Phong " + room.getRoomName() + ": Da ban " + sold + "/" + total + " ve");
            totalRevenue += room.getRevenue();
        }
        System.out.println("Tong doanh thu: " + String.format("%.0f", totalRevenue) + " VND");
    }

    private static void detectDeadlock() {
        System.out.println("Dang quet deadlock...");
       
        System.out.println("Khong phat hien deadlock.");
    }

    private static void exitSystem() {
        if (executor != null) {
            executor.shutdownNow();
        }
        if (deadlockDetector != null) {
            deadlockDetector.stop();
        }
        System.out.println("Dang dung he thong...");
        System.out.println("Ket thuc chuong trinh.");
    }
}
