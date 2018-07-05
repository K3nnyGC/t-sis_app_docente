package pe.edu.upc.proyectotsys.viewcontrollers.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Contract;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {

    List<Contract> contracts;

    public void setContract(List<Contract> lista){ this.contracts = lista;}

    @Override
    public ContractAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_contract, parent, false)
        );
    }

    public void onBindViewHolder(ContractAdapter.ViewHolder holder, int position){
        holder.dniTextView.setText(contracts.get(position).getDni_student());
        holder.grado.setText(contracts.get(position).getCodeGradeS());
        holder.tema.setText(contracts.get(position).getCodeKnowledgeS());
        holder.fecha.setText(contracts.get(position).getDate_advisory());


    }

    @Override
    public int getItemCount(){
        return contracts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView contratcCardView;
        TextView dniTextView;
        TextView grado;
        TextView tema;
        TextView fecha;
        public ViewHolder(View itemView){
            super(itemView);
            contratcCardView = (CardView) itemView.findViewById(R.id.contractCard);
            dniTextView = (TextView) itemView.findViewById(R.id.dni_estudiante);
            grado = (TextView) itemView.findViewById(R.id.grado);
            tema = (TextView) itemView.findViewById(R.id.tema);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
        }
    }

}
