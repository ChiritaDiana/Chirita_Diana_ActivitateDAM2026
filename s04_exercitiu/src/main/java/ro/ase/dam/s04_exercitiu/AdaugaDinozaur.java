package ro.ase.dam.s04_exercitiu;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaDinozaur extends AppCompatActivity {

    public static final String EXTRA_DINOZAUR = "dinozaur";
    private EditText etNume;
    private CheckBox cbCarnivor;
    private RadioGroup rgHabitat;
    private Spinner spPerioada;
    private RatingBar rbPericol;
    //private Switch swDescoperitRecent;
    private CompoundButton swDescoperitRecent;
    private Button btnAdauga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.dinozaur);
        setContentView(R.layout.dinozaur_ai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNume = findViewById(R.id.etNume);
        cbCarnivor = findViewById(R.id.cbCarnivor);
        rgHabitat = findViewById(R.id.rgHabitat);
        spPerioada = findViewById(R.id.spPerioada);
        rbPericol = findViewById(R.id.rbPericol);
        swDescoperitRecent = findViewById(R.id.swDescoperitRecent);
        btnAdauga = findViewById(R.id.btnAdauga);


        btnAdauga.setOnClickListener(v -> {
            String nume = etNume.getText().toString().trim();
            if (nume.isEmpty()) {
                etNume.setError(getString(R.string.eroare_nume));
                return;
            }

            String perioada = spPerioada.getSelectedItem().toString();
            RadioButton rbSelectat = findViewById(rgHabitat.getCheckedRadioButtonId());
            String habitat = rbSelectat.getText().toString();

            Dinozaur dino = new Dinozaur(nume, perioada, habitat,
                    cbCarnivor.isChecked(), rbPericol.getRating(),
                    swDescoperitRecent.isChecked());

            Intent rezultat = new Intent();
            rezultat.putExtra(EXTRA_DINOZAUR, dino);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}
