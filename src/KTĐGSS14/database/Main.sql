CREATE DATABASE BankDB;
USE BankDB;

-- 1. Tạo bảng tài khoản
CREATE TABLE Accounts (
                          AccountId VARCHAR(10) PRIMARY KEY,
                          FullName VARCHAR(50),
                          Balance DECIMAL(18, 2)
);

-- 2. Chèn dữ liệu mẫu
INSERT INTO Accounts VALUES
                         ('ACC03', 'Nguyen Van C', 5000),
                         ('ACC04', 'Nguyen Van D', 5000),
                         ('ACC05', 'Nguyen Van EF', 5000);

-- 3. Tạo Procedure cập nhật số dư
DELIMITER //
CREATE PROCEDURE sp_UpdateBalance(
    IN p_Id VARCHAR(10),
    IN p_Amount DECIMAL(18, 2)
)
BEGIN
UPDATE Accounts SET Balance = Balance + p_Amount WHERE AccountId = p_Id;
END //
DELIMITER ;