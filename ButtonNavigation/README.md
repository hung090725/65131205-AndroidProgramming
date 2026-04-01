# Bài Thực Hành Android: Bottom Navigation & Fragment Dynamic

Dự án này minh họa cách sử dụng **Fragment động** kết hợp với **Bottom Navigation View** để quản lý các màn hình chức năng khác nhau trong một Activity duy nhất.

## 📌 Tổng Quan Dự Án
Ứng dụng cung cấp một bộ công cụ hữu ích cho người dùng, bao gồm:
- **MainActivity**: Sử dụng `FragmentManager` để gắn và hoán đổi các Fragment (`Cau1`, `Cau2`, `Cau3`, `Cau4`, `Questions`) vào `FrameLayout`.
- **Giao diện**: Thiết kế hiện đại theo chuẩn **Material Design**, điều hướng mượt mà qua thanh menu phía dưới.

## ✨ Các Chức Năng Chính
1. **Câu 1: Chuyển Đổi Đơn Vị**
   - Hỗ trợ đổi đơn vị độ dài giữa **Mét (m)** và **Kilomet (km)** một cách nhanh chóng.
2. **Câu 2: Tính Chỉ Số BMI**
   - Tính toán chỉ số khối cơ thể dựa trên Chiều cao (cm) và Cân nặng (kg).
   - Đưa ra phân loại tình trạng sức khỏe (Gầy, Bình thường, Thừa cân, Béo phì).
3. **Câu 3: Giải Phương Trình Bậc 2**
   - Giải phương trình dạng `ax² + bx + c = 0`.
   - Xử lý đầy đủ các trường hợp: Vô nghiệm, Nghiệm kép, 2 Nghiệm phân biệt.
4. **Câu 4: Lời Chào Ngẫu Nhiên**
   - Hiển thị các câu châm ngôn, lời chúc ngẫu nhiên để tạo động lực cho người dùng.
5. **Trang Chủ (Questions)**
   - Màn hình giới thiệu tổng quan về các bài thực hành trong ứng dụng.

## 📸 Ảnh Kết Quả Chạy Ứng Dụng
![Kết quả chạy ứng dụng](./result_mockup.png)
*(Lưu ý: Bạn có thể xem video demo đính kèm trong thư mục hoặc tại đường link YouTube bên dưới)*

## 🛠️ Công Nghệ Sử Dụng
- **Ngôn ngữ**: Java
- **Framework**: Android SDK
- **Component**: Fragment, BottomNavigationView, FrameLayout, Material Components.

## 🚀 Hướng Dẫn Cài Đặt
1. Clone repository về máy:
   ```bash
   git clone https://github.com/hung090725/65131205-AndroidProgramming.git
   ```
2. Mở dự án bằng **Android Studio**.
3. Chờ Gradle đồng bộ và nhấn **Run** để khởi chạy trên máy ảo hoặc thiết bị thực.

---
**Thực hiện bởi:** Sinh viên MS - 65131205
**Link YouTube hướng dẫn:** [Xem tại đây](https://youtu.be/yhXe2baNeAE)
