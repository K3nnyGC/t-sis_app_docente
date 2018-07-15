package pe.edu.upc.proyectotsys.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView correoTextView;
    TextView passwordTextView;
    TextView texto2TextView, textErrorTextView;
    Button button_inicio;
    CheckBox chkGuardarCredenciales;
    private SharedPreferences prefs;
    private SharedPreferences pref_Session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        pref_Session = getSharedPreferences("SessionUser", Context.MODE_PRIVATE);

        if (ValidarUsuarioLogueado()) {
            Intent myMenu = new Intent(this, MenuActivity.class);
            startActivity(myMenu);
            finish();
        } else {
            correoTextView = (TextView) findViewById(R.id.correoTextView);
            passwordTextView = (TextView) findViewById(R.id.passwordTextView);
            texto2TextView = (TextView) findViewById(R.id.texto2TextView);
            textErrorTextView = (TextView) findViewById(R.id.textErrorTextView);
            button_inicio = (Button) findViewById(R.id.button_inicio);
            chkGuardarCredenciales = (CheckBox) findViewById(R.id.chkGuardarCredenciales);

            button_inicio.setOnClickListener(this);

            texto2TextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View viewIn) {
                    try {
                        Intent myRegister = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(myRegister);
                    } catch (Exception except) {
                        Toast.makeText(MainActivity.this, except.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void GuardarCredenciales(String email, String Password){
        if(chkGuardarCredenciales.isChecked()){
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("email", email);
            edit.putString("password", Password);
            edit.apply();
        }
    }

    private void GuardarDatosSession(Advisor oAdvisor) {
        SharedPreferences.Editor edit = pref_Session.edit();
        edit.putString("dni", oAdvisor.getDni());
        edit.putString("name", oAdvisor.getName());
        edit.putString("lastName", oAdvisor.getLastname());
        edit.putString("email", oAdvisor.getEmail());
        edit.putString("direction", oAdvisor.getAddress());
        edit.putString("password", oAdvisor.getPassword());
        edit.putString("phone", oAdvisor.getPhone());
        edit.putString("token", oAdvisor.getToken());
        edit.putInt("status", oAdvisor.getStatus_advisor());
        edit.putString("picture", oAdvisor.getPicture());
        edit.apply();
    }

    private boolean ValidarUsuarioLogueado(){
        if (pref_Session.getString("token", "").isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private void LoginUser(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();
        AdvisorInterface servicio = restAdapter.create(AdvisorInterface.class);
        Advisor advisor = new Advisor(
                correoTextView.getText().toString(),
                passwordTextView.getText().toString());
        servicio.LoginUser(advisor, new Callback<Advisor>() {

            @Override
            public void success(Advisor advisor2, Response response) {
//                if (advisor2.getStatus_advisor() == 0){
//                    textErrorTextView.setText("La cuenta no esta activa, por favor active su cuenta siguiendo los pasos que se le indica en el correo que se le envio.");
//                }else {
                    GuardarDatosSession(advisor2);
                    GuardarCredenciales(correoTextView.getText().toString(), passwordTextView.getText().toString());
                    Intent myLogin = new Intent(MainActivity.this, MenuActivity.class);
                    myLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(myLogin);
//                }
            }
            @Override
            public void failure(RetrofitError error) {
                textErrorTextView.setText("El email o el password ingresados son incorrectos.");
            }
        });
    }

    private boolean ValidarCamposLogin(){
        if (correoTextView.getText().toString().isEmpty()){
            textErrorTextView.setText("El email o el password ingresados son incorrectos.");
            return false;
        }else if (passwordTextView.getText().toString().isEmpty()){
            textErrorTextView.setText("El email o el password ingresados son incorrectos.");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onClick(View viewIn) {
        try {
            if(ValidarCamposLogin()) {
                LoginUser();
            }
        } catch (Exception except) {
            Toast.makeText(MainActivity.this, except.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

