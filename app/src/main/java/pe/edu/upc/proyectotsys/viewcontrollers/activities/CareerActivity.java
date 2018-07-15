package pe.edu.upc.proyectotsys.viewcontrollers.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Career;
import pe.edu.upc.proyectotsys.models.Grade;
import pe.edu.upc.proyectotsys.models.Universities;
import pe.edu.upc.proyectotsys.models.advisor_detail;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorDetailInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.CareerInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.GradeInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.UniversitiesInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CareerActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spnGrade;
    private Spinner spnGrade2;
    private Spinner spnGrade1;
    List<Career> careerList = new ArrayList<Career>();
    List<Grade> gradeList = new ArrayList<Grade>();
    List<Universities> universitiesList = new ArrayList<Universities>();
    private Button button_registrar;
    private EditText dniTextView, anioTextView;
    private int codGrade, codUniv, codCareer;
    private SharedPreferences pref_Session;
    private Universities oUniversities;
    private Grade oGrade;
    private Career oCareer;
    private Advisor oAdvisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CareerActivity.this.onBackPressed();
            }
        });

        pref_Session = this.getSharedPreferences("SessionUser", Context.MODE_PRIVATE);

        spnGrade = (Spinner) findViewById(R.id.spnGrade);
        spnGrade2 = (Spinner) findViewById(R.id.spnGrade2);
        spnGrade1 = (Spinner) findViewById(R.id.spnGrade1);
        dniTextView = (EditText) findViewById(R.id.dniTextView);
        anioTextView = (EditText) findViewById(R.id.anioEgresadoTextView);
        dniTextView.setText(pref_Session.getString("dni", "") + " - " + pref_Session.getString("name", "") + " " + pref_Session.getString("lastName", ""));
        oAdvisor = new Advisor(pref_Session.getString("dni", ""));
        dniTextView.setEnabled(false);
        dniTextView.setFocusable(false);
        dniTextView.setTextColor(Color.GRAY);
        button_registrar = (Button) findViewById(R.id.button_registrar);
        ListarGrados();
        ListarCarreras();
        ListarUniversidades();

        button_registrar.setOnClickListener(this);
    }


    private void ListarGrados(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        GradeInterface servicio = restAdapter.create(GradeInterface.class);

        servicio.getGrade(new Callback<List<Grade>>() {
                              @Override
                              public void success(List<Grade> grade, Response response) {
                                  for (int indice = 0; indice < grade.size(); indice++) {
                                      gradeList.add(new Grade(
                                                      grade.get(indice).getCode_grade(),
                                                      grade.get(indice).getDescription_grade()
                                              )
                                      );
                                  }
                                  LlenarSpinnerGrados();
                              }

                              @Override
                              public void failure(RetrofitError error) {
                                  Toast.makeText(CareerActivity.this, "Demo", Toast.LENGTH_LONG).show();
                              }
                          }
        );
    }

    private void ListarCarreras(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        CareerInterface servicio = restAdapter.create(CareerInterface.class);

        servicio.getCareer(new Callback<List<Career>>() {
                              @Override
                              public void success(List<Career> career, Response response) {
                                  for (int indice = 0; indice < career.size(); indice++) {
                                      careerList.add(new Career(
                                              career.get(indice).getCode_career(),
                                              career.get(indice).getDescription_career()
                                              )
                                      );
                                  }
                                  LlenarSpinnerCarreras();
                              }

                              @Override
                              public void failure(RetrofitError error) {
                                  Toast.makeText(CareerActivity.this, "Demo", Toast.LENGTH_LONG).show();
                              }
                          }
        );
    }

    private void ListarUniversidades(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        UniversitiesInterface servicio = restAdapter.create(UniversitiesInterface.class);

        servicio.getUniversities(new Callback<List<Universities>>() {
                               @Override
                               public void success(List<Universities> universities, Response response) {
                                   for (int indice = 0; indice < universities.size(); indice++) {
                                       universitiesList.add(new Universities(
                                               universities.get(indice).getCode_university(),
                                               universities.get(indice).getDescription_university()
                                               )
                                       );
                                   }
                                   LlenarSpinnerUniversidad();
                               }

                               @Override
                               public void failure(RetrofitError error) {
                                   Toast.makeText(CareerActivity.this, "Demo", Toast.LENGTH_LONG).show();
                               }
                           }
        );
    }

    private void LlenarSpinnerGrados() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < gradeList.size(); i++) {
            lables.add(gradeList.get(i).getDescription_grade());
        }

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CareerActivity.this, android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrade.setAdapter(spinnerAdapter);

        spnGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                codGrade = gradeList.get(arg2).getCode_grade();
                oGrade = new Grade(codGrade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void LlenarSpinnerCarreras() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < careerList.size(); i++) {
            lables.add(careerList.get(i).getDescription_career());
        }

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CareerActivity.this, android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrade2.setAdapter(spinnerAdapter);

        spnGrade2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                codCareer = careerList.get(arg2).getCode_career();
                oCareer = new Career(codCareer);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void LlenarSpinnerUniversidad() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < universitiesList.size(); i++) {
            lables.add(universitiesList.get(i).getDescription_university());
        }

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CareerActivity.this, android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrade1.setAdapter(spinnerAdapter);

        spnGrade1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                codUniv = universitiesList.get(arg2).getCode_university();
                oUniversities = new Universities(codUniv);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private boolean ValidarRegistroCarreras(){
        if (anioTextView.getText().toString().isEmpty()){
            return false;
        }else if (codUniv == 0){
            return false;
        }else if (codGrade == 0){
            return false;
        }else if (codCareer == 0){
            return false;
        }else{
            return true;
        }
    }

    private void RegisterCareers() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();
        AdvisorDetailInterface servicio = restAdapter.create(AdvisorDetailInterface.class);
        advisor_detail detail = new advisor_detail(
                0,
                oAdvisor,
                oUniversities,
                oGrade,
                Integer.parseInt(anioTextView.getText().toString()),
                oCareer
        );

        servicio.RegisterAdvisorDetails(detail, new Callback<advisor_detail>() {
            @Override
            public void success(advisor_detail detail2, Response response) {
                new AlertDialog.Builder(CareerActivity.this)
                        .setTitle("Alerta: Registro Carrera")
                        .setMessage("La carrera fue registrado correctamente.")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }

            @Override
            public void failure(RetrofitError error) {
                new AlertDialog.Builder(CareerActivity.this)
                        .setTitle("Alerta: Registro Carrera")
                        .setMessage("Los datos ingresados son incorrectos, por favor valide los campos.")
                        .setNegativeButton("OK", null)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            if(ValidarRegistroCarreras()) {
                RegisterCareers();
            }else{
                new AlertDialog.Builder(CareerActivity.this)
                        .setTitle("Alerta: Registro Carrera")
                        .setMessage("Los datos ingresados son incorrectos, por favor valide los campos.")
                        .setNegativeButton("CANCEL", null)
                        .show();
            }
        } catch (Exception except) {
            Toast.makeText(CareerActivity.this, except.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
