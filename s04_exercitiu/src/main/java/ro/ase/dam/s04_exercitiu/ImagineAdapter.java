package ro.ase.dam.s04_exercitiu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private final Context ctx;
    private final int resursaLayout;
    private final List<ImaginiDomeniu> lista;

    public ImagineAdapter(Context ctx, int resursaLayout, List<ImaginiDomeniu> lista) {
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        ImaginiDomeniu item = lista.get(position);

        ImageView ivImagine = v.findViewById(R.id.ivImagine);
        TextView tvDescriere = v.findViewById(R.id.tvDescriere);

        ivImagine.setImageBitmap(item.getImagine());
        tvDescriere.setText(item.getTextAfisat());

        return v;
    }
}
