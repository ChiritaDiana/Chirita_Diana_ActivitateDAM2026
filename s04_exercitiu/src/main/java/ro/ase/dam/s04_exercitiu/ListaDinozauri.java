package ro.ase.dam.s04_exercitiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import java.util.ArrayList;
import java.util.List;

public class ListaDinozauri extends AppCompatActivity {
     //S05
     public static final String EXTRA_LISTA = "lista";
     private ArrayList<Dinozaur> listaDinozauri;
     private DinozaurAdapter adapterDinozauri = null;
     private int idModificat = 0;

    //private ArrayAdapter<Dinozaur> adapterDinozauri; //de la s05


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_dinozauri);

        //listaDinozauri = (ArrayList<Dinozaur>) getIntent().getSerializableExtra(EXTRA_LISTA);
        listaDinozauri = IntentCompat.getParcelableArrayListExtra(getIntent(), EXTRA_LISTA, Dinozaur.class);
        if(listaDinozauri ==  null)
            listaDinozauri = new ArrayList<>();

        ListView lvListaDinoazauri = findViewById(R.id.lvListaDinozauri);
        //adapterDinozauri = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDinozauri);
        adapterDinozauri = new DinozaurAdapter(getApplicationContext(), listaDinozauri, R.layout.layout_res_file_dinozauri);
        lvListaDinoazauri.setAdapter(adapterDinozauri);

        lvListaDinoazauri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                Toast.makeText(ListaDinozauri.this,
//                        listaDinozauri.get(i).toString(),
//                        Toast.LENGTH_LONG).show();
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaDinozaur.class);
                intentModifica.putExtra(AdaugaDinozaur.EXTRA_DINOZAUR, listaDinozauri.get(i));
                idModificat=i;
                startActivityForResult(intentModifica, 209);
                Toast.makeText(getApplicationContext(), listaDinozauri.get(i).toString(), Toast.LENGTH_SHORT).show();
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
        //rezultat.putExtra(EXTRA_LISTA, listaDinozauri);
        rezultat.putParcelableArrayListExtra(EXTRA_LISTA, listaDinozauri);
        setResult(RESULT_OK, rezultat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 209){
//            Dinozaur dino = IntentCompat.getParcelableExtra(data, AdaugaDinozaur.EXTRA_DINOZAUR, Dinozaur.class);
//            if(dino!=null){
//                listaDinozauri.set(idModificat, dino);
//                adapterDinozauri.notifyDataSetChanged();
//                returneazaLista();
//            }
            listaDinozauri.set(idModificat, data.getParcelableExtra("EXTRA_DINOZAUR"));
            adapterDinozauri.notifyDataSetChanged();
            returneazaLista();
        }
    }
}
