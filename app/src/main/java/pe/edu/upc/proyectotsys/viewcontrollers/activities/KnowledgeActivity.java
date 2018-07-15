package pe.edu.upc.proyectotsys.viewcontrollers.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Category;
import pe.edu.upc.proyectotsys.models.Grade;
import pe.edu.upc.proyectotsys.models.Knowledge;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.CategoryInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.GradeInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.KnowledgeInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KnowledgeActivity extends AppCompatActivity  implements View.OnClickListener {
    private SharedPreferences pref_Session;
    private EditText dniTextView, priceTextView;
    private Button button_registrar;
    private Spinner spnGrade1;
    private int codTheme;
    private Advisor oAdvisor;
    private Category oCategory;
    List<Category> categorieList = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KnowledgeActivity.this.onBackPressed();
            }
        });

        pref_Session = this.getSharedPreferences("SessionUser", Context.MODE_PRIVATE);
        dniTextView = (EditText) findViewById(R.id.dniTextView);
        priceTextView = (EditText) findViewById(R.id.priceTextView);
        spnGrade1 = (Spinner) findViewById(R.id.spnGrade1);
        button_registrar = (Button) findViewById(R.id.button_registrar);
        dniTextView.setText(pref_Session.getString("dni", "") + " - " + pref_Session.getString("name", "") + " " + pref_Session.getString("lastName", ""));
        oAdvisor = new Advisor(pref_Session.getString("dni", ""));
        dniTextView.setEnabled(false);
        dniTextView.setFocusable(false);
        dniTextView.setTextColor(Color.GRAY);

        ListarTemas();

        button_registrar.setOnClickListener(this);
    }

    private void ListarTemas(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        CategoryInterface servicio = restAdapter.create(CategoryInterface.class);

        servicio.getCategories(new Callback<List<Category>>() {
                              @Override
                              public void success(List<Category> categories, Response response) {
                                  for (int indice = 0; indice < categories.size(); indice++) {
                                      categorieList.add(new Category(
                                              categories.get(indice).getId_theme(),
                                              categories.get(indice).getName_theme()
                                              )
                                      );
                                  }
                                  LlenarSpinnerCategory();
                              }

                              @Override
                              public void failure(RetrofitError error) {
                                  Toast.makeText(KnowledgeActivity.this, "Demo", Toast.LENGTH_LONG).show();
                              }
                          }
        );
    }

    private void LlenarSpinnerCategory(){
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < categorieList.size(); i++) {
            lables.add(categorieList.get(i).getName_theme());
        }

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(KnowledgeActivity.this, android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrade1.setAdapter(spinnerAdapter);

        spnGrade1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                codTheme = categorieList.get(arg2).getId_theme();
                oCategory = new Category(codTheme);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private boolean ValidarRegistroKnowledge(){
        if (priceTextView.getText().toString().isEmpty()){
            return false;
        }else if (codTheme == 0){
            return false;
        }else{
            return true;
        }
    }

    private void RegisterKnowledge() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();
        KnowledgeInterface servicio = restAdapter.create(KnowledgeInterface.class);
        Knowledge knowledge = new Knowledge(
                0,
                oAdvisor,
                oCategory,
                Double.parseDouble(priceTextView.getText().toString())
        );

        servicio.RegisterKnowledge(knowledge, new Callback<Knowledge>() {
            @Override
            public void success(Knowledge advisor2, Response response) {
                new AlertDialog.Builder(KnowledgeActivity.this)
                        .setTitle("Alerta: Registro Carrera")
                        .setMessage("La carrera fue registrado correctamente.")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .show();
            }

            @Override
            public void failure(RetrofitError error) {
                new AlertDialog.Builder(KnowledgeActivity.this)
                        .setTitle("Alerta: Registro Carrera")
                        .setMessage("Los datos ingresados son incorrectos, por favor valide los campos.")
                        .setNegativeButton("OK", null)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
        startActivity(getIntent());
//        try {
//            if(ValidarRegistroKnowledge()) {
//                RegisterKnowledge();
//            }else{
//                new AlertDialog.Builder(KnowledgeActivity.this)
//                        .setTitle("Alerta: Registro Carrera")
//                        .setMessage("Los datos ingresados son incorrectos, por favor valide los campos.")
//                        .setNegativeButton("CANCEL", null)
//                        .show();
//            }
//        } catch (Exception except) {
//            Toast.makeText(KnowledgeActivity.this, except.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }
}
