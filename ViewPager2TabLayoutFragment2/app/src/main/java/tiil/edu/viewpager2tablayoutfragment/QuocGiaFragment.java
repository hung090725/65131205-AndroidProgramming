package tiil.edu.viewpager2tablayoutfragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class QuocGiaFragment extends Fragment {
    QuocGia quocGia;

    public QuocGiaFragment(QuocGia _quocGia) {
        // Required empty public constructor
        quocGia = _quocGia;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_quoc_gia, container, false);
        TextView textViewTenQG = viewFragment.findViewById(R.id.textViewCountryName);
        TextView textViewDanSo = viewFragment.findViewById(R.id.textViewPopulation);
        ImageView imageViewFlag = viewFragment.findViewById(R.id.imageViewFlag);

        textViewTenQG.setText(quocGia.getCountryName());
        textViewDanSo.setText("Population:"+ String.valueOf(quocGia.getPopulation()));
        int  resId = viewFragment.getResources().getIdentifier(quocGia.getCountryFlag(),
                "mipmap",viewFragment.getContext().getPackageName());
        imageViewFlag.setImageResource(resId);


        return  viewFragment;
    }
}