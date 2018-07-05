package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.ContractInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.MainActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.activities.MenuActivity;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.ContractAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link HistorialFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link HistorialFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HistorialFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;



    public HistorialFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HistorialFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HistorialFragment newInstance(String param1, String param2) {
//        HistorialFragment fragment = new HistorialFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final TextView resultadotextView;
        final View view = inflater.inflate(R.layout.fragment_historial, container, false);
        resultadotextView = (TextView)view.findViewById(R.id.texttest);

        final RecyclerView contractRecyclerView;
        contractRecyclerView = (RecyclerView) view.findViewById(R.id.contratos);

        final RecyclerView.LayoutManager contractLayoutManager;
        contractLayoutManager = new LinearLayoutManager(getContext());



        final List<Contract> contratos = new ArrayList<Contract>();
        //contratos = List<Contract>;

        contratos.add(new Contract(0,0,"43545543","12-23-2019"));
        contratos.add(new Contract(0,0,"43545543","12-23-2019"));
        contratos.add(new Contract(0,0,"43545543","12-23-2019"));





        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        ContractInterface servicio = restAdapter.create(ContractInterface.class);

        servicio.getContracts(new Callback<List<Contract>>() {
            @Override
            public void success(List<Contract> contracts, Response response) {

                List<Contract> contratosF = new ArrayList<Contract>();

                for(int indice = 0;indice<contracts.size();indice++)
                {
                    contratosF.add(new Contract(contracts.get(indice).getCode_grade(),
                                                contracts.get(indice).getCode_knowledge(),
                                                contracts.get(indice).getDni_student(),
                                                contracts.get(indice).getDate_advisory()
                                                )
                                );
                    resultadotextView.setText(contracts.get(indice).toString());
                }

                ContractAdapter contractAdapter;
                contractAdapter = new ContractAdapter();
                contractAdapter.setContract(contratosF);

                contractRecyclerView.setLayoutManager(contractLayoutManager);
                contractRecyclerView.setAdapter(contractAdapter);

            }

            @Override
            public void failure(RetrofitError error) {
                resultadotextView.setText(error.getMessage());

            }
        }
        );


        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
