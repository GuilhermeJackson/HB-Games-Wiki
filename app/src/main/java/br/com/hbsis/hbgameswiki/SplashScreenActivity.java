package br.com.hbsis.hbgameswiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    Animation fromtop, frombottom,fromleft,fromright,alphanimation;
    ImageView hbgames,wiki,logomeio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen1);
        delaySplash();

    }

    //Metodo que inicia a main activity depois da splash
    private void mostrarMainActivity() {
        Intent intent = new Intent(
                SplashScreenActivity.this, MainActivity.class
        );
        startActivity(intent);
        finish();
    }

    //Metodo que inicia o login
    private void mostrarLogin() {
        Intent intent = new Intent(
                SplashScreenActivity.this, LoginActivity.class
        );
        startActivity(intent);
        finish();
    }

    //Splash screen
    private void mostrarSplash(int delay) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

                if (preferences.contains("open")) {
                    mostrarLogin();
                } else {
                    adicionarPreferenceJaAbriu(preferences);
                    mostrarLogin();
                }
            }
        }, delay);

        animationSplash();

    }
    //Metodo que guarda a informação de que o aplicativo ja foi aberto outras vezes

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("open", true);
        editor.commit();
    }

    //Animação da SplashScreen

    private void animationSplash() {
        logomeio = findViewById(R.id.logomeio);
        hbgames = findViewById(R.id.hbgames);
        wiki = findViewById(R.id.wiki);


        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromleft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        fromright = AnimationUtils.loadAnimation(this, R.anim.fromright);
        alphanimation = AnimationUtils.loadAnimation(this, R.anim.alphanimation);


        hbgames.startAnimation(fromright);
        logomeio.startAnimation(alphanimation);
        wiki.startAnimation(fromleft);

    }

    //Metodo que inicia a splash com o delay determinado;
    //Se o aplicativo nunca foi aberto o delay é maior

    private void delaySplash(){

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

        if (preferences.contains("open")) {
            mostrarSplash(1500);
        } else {
            adicionarPreferenceJaAbriu(preferences);
            mostrarSplash(2000);
        }
    }
}
