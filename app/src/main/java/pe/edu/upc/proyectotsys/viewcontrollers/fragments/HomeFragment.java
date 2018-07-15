package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.MainActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.AdvisorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    List<Advisor> advisor;
    RecyclerView.LayoutManager advisorLayoutManager;
    RecyclerView advisorRecyclerView;
    AdvisorAdapter advisorAdapter;
    TextView textWelcomeTextView;
    Button button_cerrar;
    private SharedPreferences pref_Session;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textWelcomeTextView = (TextView) view.findViewById(R.id.textWelcomeTextView);
        button_cerrar = (Button) view.findViewById(R.id.button_cerrar);
        pref_Session = this.getActivity().getSharedPreferences("SessionUser", Context.MODE_PRIVATE);


        textWelcomeTextView.setText("Su token es: " + pref_Session.getString("token", ""));

        button_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion();
                RegresarLogin();
            }
        });
        return view;
    }

    private void RegresarLogin(){
        Intent myLogin = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        myLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myLogin);
    }

    private void CerrarSesion(){
        pref_Session.edit().clear().apply();
    }
}
