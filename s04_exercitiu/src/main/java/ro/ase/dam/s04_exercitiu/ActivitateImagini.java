package ro.ase.dam.s04_exercitiu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateImagini extends AppCompatActivity {
    private List<Bitmap> imagini = new ArrayList<>();
    private List<ImaginiDomeniu> lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://stiintasitehnica.com/wp-content/uploads/2024/08/T-rex-size.jpg");
        linkuriImagini.add("https://dinosaurland.es/wp-content/uploads/elementor/thumbs/dinosaurio-stegosaurus-dinosaurland-r1n5jvppvyq7ahgc8fksp9wkzqen5sq9cnu2l3cav4.jpg");
        linkuriImagini.add("https://www.nhm.ac.uk/content/dam/nhm-www/discover/mosasaur-explainer/mosasaur-explainer-mosasaurus-swimming-reconstruction-full-width-shutterstock.jpg");
        linkuriImagini.add("https://cdn.mos.cms.futurecdn.net/6vA8wWskCkUgffN9dbQhvh.jpg");
        linkuriImagini.add("https://ichef.bbci.co.uk/ace/standard/2048/cpsprodpb/d3b5/live/3fde62b0-ec90-11f0-abc2-c92f19f0499a.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new ImaginiDomeniu("T-REX", imagini.get(0), "https://stiintasitehnica.com/t-rex-ar-fi-putut-fi-mult-mai-mare-decat-se-credea-pana-acum/"));
                        lista.add(new ImaginiDomeniu("Stegosaurus", imagini.get(1), "https://dinosaurland.es/en/dinos/stegosaurus/"));
                        lista.add(new ImaginiDomeniu("Mosasaur", imagini.get(2), "https://www.nhm.ac.uk/discover/what-is-a-mosasaur.html"));
                        lista.add(new ImaginiDomeniu("Pterodactyl", imagini.get(3), "https://www.livescience.com/24071-pterodactyl-pteranodon-flying-dinosaurs.html"));
                        lista.add(new ImaginiDomeniu("Triceratop", imagini.get(4), "https://www.bbc.co.uk/newsround/articles/c98p437gdr9o"));

                        ListView lvImagini = findViewById(R.id.lvImagini);
                        ImagineAdapter adapter = new ImagineAdapter(ActivitateImagini.this, R.layout.layout_imagine, lista);
                        lvImagini.setAdapter(adapter);
                        lvImagini.setOnItemClickListener((parent, view, i, l)->{
                            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                            intent.putExtra("link", lista.get(i).getLink());
                            startActivity(intent);

                        });
                    }
                });
            }
        });




    }
}