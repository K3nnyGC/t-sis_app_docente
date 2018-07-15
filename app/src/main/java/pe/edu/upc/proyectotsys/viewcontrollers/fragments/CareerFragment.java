package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Career;
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.models.advisor_detail;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorDetailInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.ContractInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.CareerActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.UpdateUserActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.CareerAdapter;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.ContractAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CareerFragment extends Fragment {

    public CareerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_career, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CareerActivity.class);
                startActivity(intent);
            }
        });

        final RecyclerView careerRecyclerView;
        careerRecyclerView = (RecyclerView) view.findViewById(R.id.carreras);

        final RecyclerView.LayoutManager careerLayoutManager;
        careerLayoutManager = new LinearLayoutManager(getContext());

        final List<advisor_detail> detail = new ArrayList<advisor_detail>();

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        AdvisorDetailInterface servicio = restAdapter.create(AdvisorDetailInterface.class);

        servicio.getAllDetails(new Callback<List<advisor_detail>>() {
                                     @Override
                                     public void success(List<advisor_detail> details, Response response) {

                                         List<advisor_detail> detailF = new ArrayList<advisor_detail>();

                                         for (int indice = 0; indice < details.size(); indice++) {
                                             detailF.add(new advisor_detail(
                                                     details.get(indice).getCode_detail(),
                                                     details.get(indice).getDni(),
                                                     details.get(indice).getCode_university(),
                                                     details.get(indice).getCode_grade(),
                                                     details.get(indice).getYear_egress(),
                                                     details.get(indice).getCode_career()
                                                     )
                                             );
                                             //resultadotextView.setText(contracts.get(indice).toString());
                                         }

                                         CareerAdapter careerAdapter;
                                         careerAdapter = new CareerAdapter();
                                         careerAdapter.setDetail(detailF);

                                         careerRecyclerView.setLayoutManager(careerLayoutManager);
                                         careerRecyclerView.setAdapter(careerAdapter);

                                     }

                                     @Override
                                     public void failure(RetrofitError error) {
                                         //
                                     }
                                 }
        );



        return view;

    }
}
