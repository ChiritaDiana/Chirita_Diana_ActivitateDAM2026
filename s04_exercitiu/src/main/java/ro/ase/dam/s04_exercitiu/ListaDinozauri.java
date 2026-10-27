package ro.ase.dam.s04_exercitiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaDinozauri extends AppCompatActivity {
    public static final String EXTRA_LISTA = "lista";
    private ArrayList<Dinozaur> listaDinozauri;
    private ArrayAdapter<Dinozaur> adapterDinozauri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_dinozauri);

        listaDinozauri = (ArrayList<Dinozaur>) getIntent().getSerializableExtra(EXTRA_LISTA);
        if(listaDinozauri ==  null)
            listaDinozauri = new ArrayList<>();

        ListView lvListaDinoazauri = findViewById(R.id.lvListaDinozauri);
        adapterDinozauri = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDinozauri);
        lvListaDinoazauri.setAdapter(adapterDinozauri);

        lvListaDinoazauri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaDinozauri.this,
                        listaDinozauri.get(position).toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

        lvListaDinoazauri.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaDinozauri.remove(position);
                adapterDinozauri.notifyDataSetChanged();
                returneazaLista();
                return true;
            }
        });

        returneazaLista();

    }
    private void returneazaLista(){
        Intent rezultat = new Intent();
        rezultat.putExtra(EXTRA_LISTA, listaDinozauri);
        setResult(RESULT_OK, rezultat);
    }

}
