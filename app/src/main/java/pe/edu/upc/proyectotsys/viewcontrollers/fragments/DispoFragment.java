package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Available;
import pe.edu.upc.proyectotsys.models.Contract;
//import pe.edu.upc.proyectotsys.models.Disponibilidad;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.ContractInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.DispoInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.ContractAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DispoFragment extends Fragment  implements View.OnClickListener {
    private Button btnFecha, btnHora, btnHoraFinal, btnRegistrar;
    private TextView txtFecha, txtHora, txtHoraFinal;
    private EditText dniTextView;
    private SharedPreferences pre_session;
    private String dniuser;
    private int dia_ini,mes_ini,anio_ini;
    private int hora_fin,minuto_fin,hora_ini,minuto_ini;
    private Advisor oAdvisor;

    public DispoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dispo, container, false);

        pre_session=this.getActivity().getSharedPreferences("SessionUser",Context.MODE_PRIVATE);

        btnFecha= view.findViewById(R.id.btnFecha);
        btnHora= view.findViewById(R.id.btnHora);
        txtFecha= view.findViewById(R.id.txtFecha);
        txtHora= view.findViewById(R.id.txtHora);
        dniTextView = view.findViewById(R.id.dniTextView);
        btnHoraFinal= view.findViewById(R.id.btnHoraFinal);
        txtHoraFinal= view.findViewById(R.id.txtHoraFinal);
        btnRegistrar= view.findViewById(R.id.button_registrar);
        dniTextView.setText(pre_session.getString("dni", "") + " - " + pre_session.getString("name", "") + " " + pre_session.getString("lastName", ""));
        oAdvisor = new Advisor(pre_session.getString("dni", ""));
        dniTextView.setEnabled(false);
        dniTextView.setFocusable(false);
        dniTextView.setTextColor(Color.GRAY);
        btnHora.setOnClickListener(this);
        btnHoraFinal.setOnClickListener(this);
        btnFecha.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v==btnFecha){

            final Calendar c= Calendar.getInstance();

            dia_ini = c.get(Calendar.DAY_OF_MONTH);
            mes_ini = c.get(Calendar.MONTH)             ;
            anio_ini = c.get(Calendar.YEAR);

            DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month=month+1;
                    if (Integer.toString(month).length() == 1){
                        txtFecha.setText(dayOfMonth+"/0"+month+"/"+year);
                    }else{
                        txtFecha.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                    dia_ini=dayOfMonth;
                    mes_ini=month;
                    anio_ini=year;
                }
            },anio_ini,mes_ini,dia_ini);
            dpd.getDatePicker().setMinDate(System.currentTimeMillis());
            dpd.show();
        }

        if (v==btnHora){

            final Calendar c= Calendar.getInstance();

            hora_ini = c.get(Calendar.HOUR_OF_DAY);
            minuto_ini = 0;

            TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (Integer.toString(hourOfDay).length() == 1){
                        txtHora.setText("0"+hourOfDay+":"+(Integer.toString(minute).length() == 1 ? "0" + minute : minute));
                    }else{
                        txtHora.setText(hourOfDay+":"+(Integer.toString(minute).length() == 1 ? "0" + minute : minute));
                    }
                    hora_ini=hourOfDay;
                    minuto_ini=minute;
                    txtHoraFinal.setText("");
                    hora_fin = 0;
                    minuto_fin = 0;
                }
            },hora_ini,minuto_ini,true);
            tpd.setTitle("Desde");
            tpd.show();
        }

        if (v==btnHoraFinal){
            final Calendar c= Calendar.getInstance();

            hora_fin = c.get(Calendar.HOUR_OF_DAY);
            minuto_fin = 0;

            TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if(hora_fin<hora_ini){
                        Toast.makeText(getContext(),"Deberá registrar una hora mayor a la inicial",Toast.LENGTH_SHORT).show();
                    }else {
                        if (Integer.toString(hourOfDay).length() == 1){
                            txtHoraFinal.setText("0"+hourOfDay+":"+(Integer.toString(minute).length() == 1 ? "0" + minute : minute));
                        }else{
                            txtHoraFinal.setText(hourOfDay+":"+(Integer.toString(minute).length() == 1 ? "0" + minute : minute));
                        }
//                        txtHoraFinal.setText(hourOfDay + ":" + minute);
                        hora_fin = hourOfDay;
                        minuto_fin = minute;
                    }
                }
            },hora_fin,minuto_fin,true);

            tpd.setTitle("Hasta");
            tpd.show();
        }

        if (v==btnRegistrar){
            dniuser=pre_session.getString("dni","");

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

            DispoInterface servicio = restAdapter.create(DispoInterface.class);
            String mes="0";
            String dia="0";
            if(mes_ini<10){
                mes="0"+mes_ini;
            }else {
                mes=String.valueOf(mes_ini);
            }
            if(dia_ini<10){
                dia="0"+dia_ini;
            }else {
                dia=String.valueOf(dia_ini);
            }
            String fecha_tomada= anio_ini+"-"+mes+"-"+dia;


            Available obj= new Available(0,oAdvisor,fecha_tomada,hora_ini,0);

            //Toast.makeText(getContext(),"Datos"+dniuser+"||"+txtFecha.getText().toString()+"||"+hora_ini+"||"+hora_fin,Toast.LENGTH_LONG).show();

            servicio.RegisterDispo( obj ,new Callback<Available>() {
                        @Override
                        public void success(Available avai, Response response) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Alerta: Registro Disponibilidad")
                                    .setMessage("La disponiblidad fue registrada correctamente.")
                                    .setNegativeButton("OK", null)
                                    .show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Alerta: Registro Disponibilidad")
                                    .setMessage(error.getMessage())
                                    .setNegativeButton("OK", null)
                                    .show();
                        }
                    }
            );

        }
    }
}
