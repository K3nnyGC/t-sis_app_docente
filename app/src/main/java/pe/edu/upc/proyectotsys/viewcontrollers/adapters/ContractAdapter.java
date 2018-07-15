package pe.edu.upc.proyectotsys.viewcontrollers.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import pe.edu.upc.proyectotsys.models.Contract;
import pe.edu.upc.proyectotsys.R;

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
        holder.dniTextView.setText(contracts.get(position).getDni().getName());
        holder.grado.setText(contracts.get(position).getCode_grade().getDescription_grade());
        holder.tema.setText(contracts.get(position).getCode_knowledge().getId_theme().getName_theme());
        holder.fecha.setText(contracts.get(position).getDate_registry());
        holder.estado.setText(ObtenerEstadoContrato(contracts.get(position).getState_contract()));
    }

    private String ObtenerEstadoContrato(int valor) {
        switch (valor) {
            case 0:
                return "Pendiente";
            case 1:
                return "Finalizado";
            case 2:
                return "Cancelado";
            default:
                return "Sin Estado";
        }
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
        TextView estado;
        public ViewHolder(View itemView){
            super(itemView);
            contratcCardView = (CardView) itemView.findViewById(R.id.contractCard);
            dniTextView = (TextView) itemView.findViewById(R.id.dni_estudiante);
            grado = (TextView) itemView.findViewById(R.id.grado);
            tema = (TextView) itemView.findViewById(R.id.tema);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            estado = (TextView) itemView.findViewById(R.id.estado);
        }
    }
}
