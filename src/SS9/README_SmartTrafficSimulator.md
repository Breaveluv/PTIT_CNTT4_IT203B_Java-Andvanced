# Smart Traffic Simulator - Quick Start

# SRS - Smart Traffic Simulator (Hệ thống Mô phỏng Giao thông Thông minh)

## I. Giới thiệu

Hệ thống Mô phỏng Giao thông Thông minh là một ứng dụng console (hoặc backend module) mô phỏng hoạt động của một ngã tư đường phố.

## II. Mục tiêu dự án

1. Xây dựng cấu trúc chương trình chuẩn OOAD và tuân thủ chặt chẽ các nguyên lý SOLID.
2. Làm chủ lập trình đa luồng (Multithreading) và cơ chế đồng bộ hóa (Synchronization) trong Java.
3. Áp dụng các Design Patterns để giải quyết các bài toán thiết kế mô phỏng phức tạp.
4.Sử dụng Java 8+ và các cấu trúc dữ liệu đồng bộ (Concurrent Collections) để quản lý luồng dữ

## Thiết kế chính

- **State Pattern**: `TrafficLight` dùng `TrafficLightState` (Green/Yellow/Red) để luân phiên trạng thái.
- **Observer Pattern**: `TrafficLight` là Subject; các `Vehicle` đăng ký làm Observer để nhận thông báo khi đèn đổi. `Intersection` cũng đăng ký để đánh thức các xe chờ khi chuyển sang xanh.
- **Factory Method**: `VehicleFactory` tạo ngẫu nhiên `StandardVehicle`/`PriorityVehicle`.
- **Concurrency**:
  - `Intersection` dùng `ReentrantLock` + `Condition` để đảm bảo chỉ 1 xe vào giao lộ tại một thời điểm.
  - `BlockingQueue` giữ hàng chờ.
  - `ExecutorService` quản lý luồng xe.

---

## Cách hệ thống xử lý Deadlock

- Không có deadlock vì chỉ một `ReentrantLock` duy nhất được sử dụng cho giao lộ.
- Xe chờ ở phía ngoài **không giữ khóa** trong khi chờ đèn; chỉ khi đến lượt và đèn xanh (hoặc là xe ưu tiên) thì mới cố gắng `lock`.
- Mọi thread ra khỏi `lock` nhanh chóng sau khi hoàn thành băng qua giao lộ.

Nếu bạn muốn **mở rộng** để tận dụng nhiều lane (đường) và giảm khả năng tắc, có thể thay `ReentrantLock` bằng `ReadWriteLock` hoặc thêm nhiều lock cho từng làn riêng biệt.
