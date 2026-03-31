# Fragment Replace Demo (3 Buttons)

Demo nay gom 2 phan:
- `ConTentFragment`: khung noi dung lon o tren.
- `FooterFragment`: thanh 3 nut o duoi (`One`, `Two`, `Three`).

Khi bam tung nut, app thay Fragment trong vung noi dung bang `replace(...)`.

## Ket qua khi bam nut

- Bam `One` -> hien `Fragment Number 1` (nen do).
- Bam `Two` -> hien `Fragment Number 2` (nen xanh duong).
- Bam `Three` -> hien `Fragment Number 3` (nen xanh ngoc).

### Anh minh hoa

> Luu y: anh dang duoc luu tu workspace tam cua Cursor.

- Nut 1: `C:/Users/Admin/.cursor/projects/c-Users-Admin-StudioProjects-65131205-AndroidProgramming-Fragmentex-replace/assets/c__Users_Admin_AppData_Roaming_Cursor_User_workspaceStorage_634874a59f94dd0734eeeba668c1d33c_images_image-9ed053ce-06e0-41dc-bc9f-f48338a964b6.png`
- Nut 2: `C:/Users/Admin/.cursor/projects/c-Users-Admin-StudioProjects-65131205-AndroidProgramming-Fragmentex-replace/assets/c__Users_Admin_AppData_Roaming_Cursor_User_workspaceStorage_634874a59f94dd0734eeeba668c1d33c_images_image-3e8bc599-27d0-4dc2-9033-719445a9d29e.png`
- Nut 3: `C:/Users/Admin/.cursor/projects/c-Users-Admin-StudioProjects-65131205-AndroidProgramming-Fragmentex-replace/assets/c__Users_Admin_AppData_Roaming_Cursor_User_workspaceStorage_634874a59f94dd0734eeeba668c1d33c_images_image-fd9bed7c-650b-4ed2-9072-63c2f0955ebf.png`
- Code den (doan replace): `C:/Users/Admin/.cursor/projects/c-Users-Admin-StudioProjects-65131205-AndroidProgramming-Fragmentex-replace/assets/c__Users_Admin_AppData_Roaming_Cursor_User_workspaceStorage_634874a59f94dd0734eeeba668c1d33c_images_image-e9bbd3ec-80a6-4f73-9872-912b4b8bd787.png`

## Giai thich de hieu doan code o anh den

Doan can hieu:

```java
fragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView_Content, new Fragment1())
        .commit();
```

Giai nghia tung dong:

- `fragmentManager`:
  la "nguoi quan ly Fragment". O day lay bang `getParentFragmentManager()`.

- `beginTransaction()`:
  mo mot "phien giao dich" de sua man hinh Fragment.
  Cu hinh dung la "bat dau 1 lenh doi giao dien".

- `.replace(R.id.fragmentContainerView_Content, new Fragment1())`:
  - `R.id.fragmentContainerView_Content`: o de hien noi dung (khung tren trong `activity_main.xml`).
  - `new Fragment1()`: tao Fragment moi.
  - `replace(...)`: lay Fragment dang hien trong khung do ra, roi thay bang Fragment moi.

- `.commit()`:
  xac nhan giao dich.
  Neu khong co dong nay thi lenh thay Fragment chua duoc thuc thi.

Noi ngan gon: bam nut -> tao Fragment tuong ung -> thay vao `fragmentContainerView_Content` -> cap nhat man hinh.

## File chinh cua bai

- `app/src/main/java/hung/edu/fragmentex_replace/FooterFragment.java`: xu ly su kien 3 nut + replace Fragment.
- `app/src/main/res/layout/activity_main.xml`: co 2 `FragmentContainerView` (content + footer).
- `app/src/main/res/layout/fragment_1.xml`, `fragment_2.xml`, `fragment_3.xml`: giao dien hien thi khi bam tung nut.

