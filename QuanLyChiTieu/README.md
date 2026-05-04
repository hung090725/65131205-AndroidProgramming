# Dự án QuanLyChiTieu - Kết nối Firebase Realtime Database

Dự án này minh họa cách kết nối một ứng dụng Android với Firebase Realtime Database để đọc dữ liệu theo thời gian thực.

## Thông tin sinh viên
- **Họ và tên:** Nguyễn Văn Hưng
- **MSSV:** 65131205
- **Lớp:** 65.CNTT-2

## Các bước thực hiện
1. Cấu hình Firebase Project và thêm file `google-services.json` vào thư mục `app/`.
2. Khai báo thư viện Firebase Database trong `build.gradle`.
3. Thiết lập giao diện với một `TextView` (ID: `textView`).
4. Viết code Java để kết nối và lắng nghe sự thay đổi dữ liệu từ Firebase.

## Mã nguồn chính (MainActivity.java)
```java
// Khởi tạo Database
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("message");

// Lắng nghe thay đổi dữ liệu
myRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String value = snapshot.getValue(String.class);
        // Hiển thị thông báo Toast
        Toast.makeText(getBaseContext(), "Value is: " + value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(getBaseContext(), "Failed to read value.", Toast.LENGTH_LONG).show();
    }
});
```

## Kết quả đạt được
- Ứng dụng đã kết nối thành công với Firebase.
- Khi thay đổi dữ liệu trên **Firebase Console** (ví dụ đổi giá trị của key `message`), ứng dụng trên Android ngay lập tức nhận được và hiển thị thông báo qua **Toast**.

### Hình ảnh minh họa
*Dữ liệu trên Firebase Console:*
`message: "Hello, Nguyễn Văn Hưng_65131205_Lớp65.CNTT-2"`

*Kết quả hiển thị trên Emulator:*
![Kết quả Android](https://raw.githubusercontent.com/hung090725/65131205-AndroidProgramming/master/QuanLyChiTieu/result_toast.png) *(Lưu ý: Bạn cần thay đường dẫn ảnh thật nếu muốn hiển thị trên GitHub)*
