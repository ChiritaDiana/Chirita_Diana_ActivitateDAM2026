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
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Dinozaur> listaDinozauri = new ArrayList<>();
    private TextView tvRezultat;

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

//        tvRezultat = findViewById(R.id.tvRezultat);
//        Button btnAdauga = findViewById(R.id.btnAdaugaDino);
//        btnAdauga.setOnClickListener(v ->
//                adaugaLauncher.launch(new Intent(this, AdaugaDinozaur.class)));

        Button btnAdaugaDino = findViewById(R.id.btnAdaugaDino);
        btnAdaugaDino.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AdaugaDinozaur.class);
            adaugaLauncher.launch(intent);
        });

        Button btnAfiseazaLista = findViewById(R.id.btnAfiseazaLista);
        btnAfiseazaLista.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ListaDinozauri.class);
            intent.putParcelableArrayListExtra("lista", listaDinozauri);                listaLauncher.launch(intent);
            listaLauncher.launch(intent);
        });


        Button btnImaginiDinozauri = findViewById(R.id.btnImaginiDinozauri);
        btnImaginiDinozauri.setOnClickListener(v->{
            Intent intent = new Intent(this, ActivitateImagini.class);
            startActivity(intent);
        });
    }


    private final ActivityResultLauncher<Intent> adaugaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Dinozaur dino = IntentCompat.getParcelableExtra(result.getData(), "dinozaur", Dinozaur.class);

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
                    ArrayList<Dinozaur> listaActualizata = IntentCompat.getParcelableArrayListExtra(result.getData(), "lista", Dinozaur.class);

                    if(listaActualizata != null){
                        listaDinozauri.clear();
                        listaDinozauri.addAll(listaActualizata);
                    }
                }
            });


}