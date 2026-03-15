package tiil.edu.viewpager2tablayoutfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class QuocGiaPagerAdapter extends FragmentStateAdapter {
    List<QuocGia> QuocGiaList;
    public QuocGiaPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<QuocGia> _dataSource) {
        super(fragmentActivity);
        QuocGiaList = _dataSource;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        QuocGia QuocGiaX = QuocGiaList.get(position);
        QuocGiaFragment quocGiaFragment = new QuocGiaFragment(QuocGiaX);
        return quocGiaFragment;
    }

    @Override
    public int getItemCount() {
        return QuocGiaList.size();
    }
}
