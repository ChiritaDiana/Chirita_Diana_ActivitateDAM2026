package ro.ase.dam.s04_exercitiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Dinozaur> listaDinozauri = new ArrayList<>();

    private TextView tvRezultat;

//    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Dinozaur dino = (Dinozaur) result.getData()
//                            .getSerializableExtra(AdaugaDinozaur.EXTRA_DINOZAUR);
//                    if (dino != null) {
//                        tvRezultat.setText(dino.toString());
//                        Toast.makeText(this, dino.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            });

    private final ActivityResultLauncher<Intent> adaugaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Dinozaur dino = (Dinozaur) result.getData()
                            .getSerializableExtra(AdaugaDinozaur.EXTRA_DINOZAUR);
                    if (dino != null) {
                        listaDinozauri.add(dino);
                        Toast.makeText(this, dino.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    private final ActivityResultLauncher<Intent> listaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Dinozaur> listaActualizata = (ArrayList<Dinozaur>) result.getData().getSerializableExtra(ListaDinozauri.EXTRA_LISTA);

                    if(listaActualizata != null){
                        listaDinozauri.clear();
                        listaDinozauri.addAll(listaActualizata);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRezultat = findViewById(R.id.tvRezultat);
        Button btnAdauga = findViewById(R.id.btnAdaugaDino);
        btnAdauga.setOnClickListener(v ->
                adaugaLauncher.launch(new Intent(this, AdaugaDinozaur.class)));


        findViewById(R.id.btnAfiseazaLista).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaDinozauri.class);
                intent.putExtra(ListaDinozauri.EXTRA_LISTA, listaDinozauri);
                listaLauncher.launch(intent);
            }
        });
    }
}