# 📜 Bí Kíp Copy-Paste Firebase (Trọn gói cho mọi bài thi)

Khi bạn muốn thêm Firebase Realtime Database vào một Project mới, chỉ cần mở 3 file này ra và dán đống này vào là xong.

---

### 1️⃣ File: `gradle/libs.versions.toml`
Dán vào các mục tương ứng:

```toml
[versions]
googleServices = "4.4.4"
firebaseBom = "34.12.0"

[libraries]
firebase-bom = { group = "com.google.firebase", name = "firebase-bom", version.ref = "firebaseBom" }
firebase-database = { group = "com.google.firebase", name = "firebase-database" }

[plugins]
google-services = { id = "com.google.gms.google-services", version.ref = "googleServices" }
```

---

### 2️⃣ File: `build.gradle.kts` (Project-level)
Dán vào khối `plugins {}`:

```kotlin
alias(libs.plugins.google.services) apply false
```

---

### 3️⃣ File: `app/build.gradle.kts` (Module-level)
Dán vào 2 vị trí:

**Vị trí 1 (Khối plugins ở đầu file):**
```kotlin
alias(libs.plugins.google.services)
```

**Vị trí 2 (Khối dependencies ở cuối file):**
```kotlin
implementation(platform(libs.firebase.bom))
implementation(libs.firebase.database)
```

---

### 🚀 Sau khi dán xong:
1. Nhấn **Sync Now**.
2. Copy file `google-services.json` vào thư mục `app`.
3. Tận hưởng thành quả!
