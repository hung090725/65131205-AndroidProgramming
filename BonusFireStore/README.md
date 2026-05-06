# 🚀 Android Task Manager - Firebase Integration (Dành cho người mới bắt đầu)

Chào mừng bạn đến với dự án Quản lý công việc chuyên nghiệp sử dụng **Android Java** và **Firebase**. Đây là bài hướng dẫn chi tiết giúp bạn hiểu cách kết nối một ứng dụng di động với đám mây.

## 📝 Mục tiêu bài tập
Xây dựng ứng dụng cho phép:
- Đăng ký/Đăng nhập tài khoản.
- Lưu trữ công việc (Task) lên Cloud Firestore.
- Mỗi người dùng chỉ quản lý được dữ liệu của riêng mình.
- Giao diện hiện đại với Material Design 3.

## 🛠️ Các bước thực hiện (Hướng dẫn cho người mới)

### 1. Thiết lập Firebase (Backend)
- **Bước 1:** Truy cập [Firebase Console](https://console.firebase.google.com/), tạo một dự án mới.
- **Bước 2:** Đăng ký ứng dụng Android với `applicationId` là `hung.edu.bonusfirestore`.
- **Bước 3:** Tải file `google-services.json` và chép vào thư mục `app/` của dự án.
- **Bước 4:** Bật **Authentication** (chọn phương thức Email/Password).
- **Bước 5:** Tạo **Firestore Database** (chọn vị trí Singapore và chế độ Test mode).

### 2. Cấu hình Code (Frontend)
- **Gradle:** Sử dụng `libs.versions.toml` để quản lý phiên bản thư viện tập trung, giúp tránh xung đột bản Android API 36.
- **Giao diện (XML):** 
  - `LoginActivity` & `RegisterActivity`: Dùng `TextInputLayout` để có ô nhập liệu đẹp.
  - `MainActivity`: Dùng `RecyclerView` để hiển thị danh sách dài một cách mượt mà.
- **Logic (Java):** 
  - Sử dụng `FirebaseAuth` để xác thực người dùng.
  - Sử dụng `FirebaseFirestore` để thực hiện các lệnh: Thêm (Add), Tải (Get), Xóa (Delete).
  - **Logic then chốt:** Lưu dữ liệu theo đường dẫn `users/{UID}/tasks` để phân quyền dữ liệu.

## 📸 Kết quả đạt được
*(Bạn có thể xem các ảnh kết quả thực tế trong thư mục `app/src/main/res/drawable`)*
- [x] Đăng ký tài khoản thành công.
- [x] Dữ liệu hiển thị thời gian thực trên Firebase Console.
- [x] Giao diện gạch ngang chữ khi hoàn thành công việc.

## 💡 Bài học kinh nghiệm
1. **Lỗi SDK:** Nếu gặp lỗi `Aar Dependency compatibility`, hãy nâng `compileSdk` lên 36.
2. **Bảo mật:** Luôn kiểm tra `request.auth != null` trong Firestore Rules.
3. **UX:** Luôn thêm ProgressBar khi đang tải dữ liệu để người dùng không cảm thấy app bị "đơ".

---
*Dự án được thực hiện với tinh thần chuyên nghiệp 100%!*
