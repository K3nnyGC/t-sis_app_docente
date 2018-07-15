package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.models.Grade;
import pe.edu.upc.proyectotsys.models.Knowledge;
import pe.edu.upc.proyectotsys.models.advisor_detail;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorDetailInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.ContractInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.GradeInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.KnowledgeInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.CareerActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.KnowledgeActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.CareerAdapter;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.ContractAdapter;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.KnowledgeAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TemasFragment extends Fragment {

    public TemasFragment() {
        // Required empty public constructor
    }

    private TextView txtText;
    private Spinner spnGrade;
    List<Knowledge> detailK = new ArrayList<Knowledge>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temas, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KnowledgeActivity.class);
                startActivity(intent);
            }
        });

        final RecyclerView temasRecyclerView;
        temasRecyclerView = (RecyclerView) view.findViewById(R.id.temas);

        final RecyclerView.LayoutManager temasLayoutManager;
        temasLayoutManager = new LinearLayoutManager(getContext());

        final List<Knowledge> knowledge = new ArrayList<Knowledge>();

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        KnowledgeInterface servicio = restAdapter.create(KnowledgeInterface.class);

        servicio.getAllKnowledge(new Callback<List<Knowledge>>() {
                                   @Override
                                   public void success(List<Knowledge> knowledges, Response response) {
                                       for (int indice = 0; indice < knowledges.size(); indice++) {
                                           detailK.add(new Knowledge(
                                                   knowledges.get(indice).getCode_knowledge(),
                                                   knowledges.get(indice).getDni(),
                                                   knowledges.get(indice).getId_theme(),
                                                   knowledges.get(indice).getPrice()
                                                   )
                                           );
                                       }

                                       KnowledgeAdapter knowledgeAdapter;
                                       knowledgeAdapter = new KnowledgeAdapter();
                                       knowledgeAdapter.setDetail(detailK);

                                       temasRecyclerView.setLayoutManager(temasLayoutManager);
                                       temasRecyclerView.setAdapter(knowledgeAdapter);

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
