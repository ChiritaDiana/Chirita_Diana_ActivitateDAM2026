package ro.ase.dam.s04_exercitiu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class DinozaurAdapter extends BaseAdapter {
    private List<Dinozaur> dinozauri = null;
    private Context ctx;
    private int resursaLayout;

    public DinozaurAdapter(Context ctx, List<Dinozaur> dinozauri, int resursaLayout) {
        this.ctx = ctx;
        this.dinozauri = dinozauri;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return dinozauri.size();
    }

    @Override
    public Object getItem(int i) {
        return dinozauri.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView tvNume = v.findViewById(R.id.tvNume);
        TextView tvPerioada = v.findViewById(R.id.tvPerioada);
        TextView tvHabitat = v.findViewById(R.id.tvHabitat);
        RatingBar rbPericol = v.findViewById(R.id.rbPericol);
        CheckBox cbCarnivor = v.findViewById(R.id.cbCarnivor);
        CheckBox cbRecent = v.findViewById(R.id.cbRecent);

        Dinozaur dinozaur = (Dinozaur) getItem(i);

        tvNume.setText(dinozaur.getNume());
        tvPerioada.setText(dinozaur.getPerioada());
        tvHabitat.setText(dinozaur.getHabitat());
        rbPericol.setRating(dinozaur.getPericulozitate());
        cbCarnivor.setChecked(dinozaur.isCarnivor());
        cbRecent.setChecked(dinozaur.isDescoperitRecent());

        return v;
    }
}
