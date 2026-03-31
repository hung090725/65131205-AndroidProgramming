package hung.edu.fragmentex_replace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FooterFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_footer, container, false);
        // tìm id của các nút trước
        Button nut1 = v.findViewById(R.id.button);
        Button nut2 = v.findViewById(R.id.button2);
        Button nut3 = v.findViewById(R.id.button3);

//        Bạn đang đứng trong một Fragment con.
//                Fragment con này muốn thay đổi Fragment khác trên màn hình, nên nó phải xin “người quản lý Fragment” từ bên cha.
//
//                getParentFragmentManager() nghĩa là:
//
//        lấy bộ quản lý Fragment của phần cha đang chứa Fragment hiện tại và lưu vào biến

        FragmentManager fragmentManager = getParentFragmentManager();
        // lắng nghe sự kiện dùng setonclickListener
        nut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code ở dây
//                trong một Fragment hiện tại, bạn bắt sự kiện bấm nút nut1
//                khi bấm nút đó
//                thì đổi phần nội dung trong fragmentContainerView_Content
//                sang Fragment1
                fragmentManager.beginTransaction()
                        // thay đổi fragment hiện tại
                        // id chỗ hiện tại đc khai báo trong main.xml, thay chỗ id đó bằng fragment1 để hiện fragment1
                        //thay Fragment hiện đang nằm trong fragmentContainerView_Content bằng Fragment1

                        .replace(R.id.fragmentContainerView_Content,new Fragment1())
                        .commit();
            }
        });
        nut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code ở dây
                fragmentManager.beginTransaction()
                        // thay đổi fragment hiện tại
                        // id chỗ hiện tại đc khai báo trong main.xml, thay chỗ id đó bằng fragment1 để hiện fragment1

                        .replace(R.id.fragmentContainerView_Content,new Fragment2())
                        .commit();
            }
        });
        nut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code ở dây
                fragmentManager.beginTransaction()
                        // thay đổi fragment hiện tại
                        // id chỗ hiện tại đc khai báo trong main.xml, thay chỗ id đó bằng fragment1 để hiện fragment1

                        .replace(R.id.fragmentContainerView_Content,new Fragment3())
                        .commit();
            }
        });

        return v;
    }
}