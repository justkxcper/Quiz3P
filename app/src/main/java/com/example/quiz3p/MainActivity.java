package com.example.quiz3p;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Pytanie> pytania;
    private TextView textView;
    private ImageView imageView;
    private Button buttonTak, buttonNie, buttonDalej, buttonPodp;
    private int aktualne = 0;

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
        dodajPytania();
        textView = findViewById(R.id.textViewPytanie);
        buttonTak = findViewById(R.id.button2);
        buttonNie = findViewById(R.id.button);
        buttonDalej = findViewById(R.id.button3);
        buttonPodp = findViewById(R.id.buttonPodp);
        imageView = findViewById(R.id.imageView);
        wypiszPytanie(0);

        buttonNie.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzOdp(false);
                    }
                }
        );

        buttonTak.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzOdp(true);

                    }
                }
        );
        buttonDalej.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualne++;
                        if (aktualne == pytania.size()) {
                            buttonDalej.setVisibility(INVISIBLE);
                            // TODO: wypisywanie wyniku.
                            int punkty = 0;
                            for (Pytanie pytanie : pytania) {
                                if (pytanie.isCzyPoprawnie()) {
                                    punkty++;
                                }
                            }
                            textView.setText("Koniec tekstu. Wynik: " + punkty);
                            // TODO: ukryć przyciski tak/nie
                        }
                        else {
                            wypiszPytanie(aktualne);
                        }
                    }
                }
        );
        buttonPodp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: obsłuźyć wyświetlenie kliknięciem -> intencja
                        // intencja jawna
                        Intent intent = new Intent(MainActivity.this, PodpowiedzActivity.class);
                        intent.putExtra("NR", aktualne);
                        startActivity(intent);
                    }
                }
        );
    }



    private void sprawdzOdp(boolean odp) {
        if (pytania.get(aktualne).isPoprawnaOdp() == odp) {
            pytania.get(aktualne).setCzyPoprawnie(true);
            Toast.makeText(this, "Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
        } else {
            pytania.get(aktualne).setCzyPoprawnie(false);
            Toast.makeText(this, "Błędna odpowiedź!", Toast.LENGTH_SHORT).show();
        }
    }

    private void wypiszPytanie(int ktore) {
        Pytanie pytanie = pytania.get(ktore);
        textView.setText(pytanie.getTrescPytania());
        imageView.setImageResource((int)(pytanie.getIdZdjecia()));
    }
    protected static ArrayList<Pytanie> dodajPytania() {
        pytania = new ArrayList<>();
        pytania.add(new Pytanie(R.drawable.lis, "Czy lis mieszka w lesie?", true, "Lisowi towarzyszy wiele drzew."));
        pytania.add(new Pytanie(R.drawable.czerwonapanda, "Czy to zwykła panda?", false, "Spojrz na kolor i oceń, jaka to panda."));
        pytania.add(new Pytanie(R.drawable.tygrys, "Czy tygrys jest z rodziny wilków?", false, "Tygrys to kot."));
        pytania.add(new Pytanie(R.drawable.zebra, "Czy zebra to koń?", true, "Zebra ma kopyta."));
        return pytania;
    }
}