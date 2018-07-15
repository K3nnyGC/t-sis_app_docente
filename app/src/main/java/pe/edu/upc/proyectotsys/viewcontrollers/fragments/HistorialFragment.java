package pe.edu.upc.proyectotsys.viewcontrollers.fragments;

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
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.ContractInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.adapters.ContractAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HistorialFragment extends Fragment {

    public HistorialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_historial, container, false);

        final RecyclerView contractRecyclerView;
        contractRecyclerView = (RecyclerView) view.findViewById(R.id.contratos);

        final RecyclerView.LayoutManager contractLayoutManager;
        contractLayoutManager = new LinearLayoutManager(getContext());

        final List<Contract> contratos = new ArrayList<Contract>();

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://t-sys-kennygonzales.c9users.io").build();

        ContractInterface servicio = restAdapter.create(ContractInterface.class);



        servicio.getAllContracts(new Callback<List<Contract>>() {
                                     @Override
                                     public void success(List<Contract> contracts, Response response) {

                                         List<Contract> contratosF = new ArrayList<Contract>();

                                         for (int indice = 0; indice < contracts.size(); indice++) {
                                             contratosF.add(new Contract(contracts.get(indice).getCode_grade(),
                                                             contracts.get(indice).getCode_knowledge(),
                                                             contracts.get(indice).getDni(),
                                                             contracts.get(indice).getDate_registry()
                                                     )
                                             );
                                         }

                                         ContractAdapter contractAdapter;
                                         contractAdapter = new ContractAdapter();
                                         contractAdapter.setContract(contratosF);

                                         contractRecyclerView.setLayoutManager(contractLayoutManager);
                                         contractRecyclerView.setAdapter(contractAdapter);

                                     }

                                     @Override
                                     public void failure(RetrofitError error) {
                                         //
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
