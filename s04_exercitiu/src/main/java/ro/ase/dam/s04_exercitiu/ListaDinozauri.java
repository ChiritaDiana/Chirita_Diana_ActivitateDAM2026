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
     private ArrayList<Dinozaur> listaDinozauri;
     private DinozaurAdapter adapter = null;
     private int idModificat = 0;

    //private ArrayAdapter<Dinozaur> adapterDinozauri; //de la s05


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_dinozauri);

        //listaDinozauri = (ArrayList<Dinozaur>) getIntent().getSerializableExtra("lista");
        listaDinozauri = IntentCompat.getParcelableArrayListExtra(getIntent(), "lista", Dinozaur.class);
        if(listaDinozauri ==  null)
            listaDinozauri = new ArrayList<>();

        ListView lvListaDinoazauri = findViewById(R.id.lvListaDinozauri);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDinozauri);
        adapter= new DinozaurAdapter(getApplicationContext(), listaDinozauri, R.layout.layout_res_file_dinozauri);
        lvListaDinoazauri.setAdapter(adapter);

        //-----------------------------------------------------------------------------

        lvListaDinoazauri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//              Toast.makeText(ListaDinozauri.this,listaDinozauri.get(i).toString(),Toast.LENGTH_LONG).show();
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaDinozaur.class);
                intentModifica.putExtra("dinozaur", listaDinozauri.get(i));
                idModificat=i;
                startActivityForResult(intentModifica, 209);
                Toast.makeText(getApplicationContext(), listaDinozauri.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lvListaDinoazauri.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listaDinozauri.remove(position);
                adapter.notifyDataSetChanged();
//                returneazaLista();
                return true;
            }
        });
//
//        returneazaLista();

    }
//    private void returneazaLista(){
//        Intent rezultat = new Intent();
//        rezultat.putParcelableArrayListExtra("lista", listaDinozauri);
//        setResult(RESULT_OK, rezultat);
//    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 209){
            Dinozaur dinoModificat = IntentCompat.getParcelableExtra(data, "dinozaur", Dinozaur.class);
            if(dinoModificat!=null){
                listaDinozauri.set(idModificat, dinoModificat);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
