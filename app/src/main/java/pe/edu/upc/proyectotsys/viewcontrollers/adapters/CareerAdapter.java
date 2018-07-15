package pe.edu.upc.proyectotsys.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.advisor_detail;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.ViewHolder> {
    List<advisor_detail> detail;

    public void setDetail(List<advisor_detail> lista) {
        this.detail = lista;
    }

    @NonNull
    @Override
    public CareerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CareerAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_careers, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CareerAdapter.ViewHolder holder, int position) {
        holder.txtnombre.setText(detail.get(position).getDni().getDni() + " - " + detail.get(position).getDni().getName() + " " + detail.get(position).getDni().getLastname());
        holder.txtUniversidad.setText(detail.get(position).getCode_university().getDescription_university());
        holder.txtGrado.setText(detail.get(position).getCode_grade().getDescription_grade());
        holder.txtanio.setText(Integer.toString(detail.get(position).getYear_egress()));
        holder.txtCarrera.setText(detail.get(position).getCode_career().getDescription_career());
    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView careerCard;
        TextView txtnombre;
        TextView txtUniversidad;
        TextView txtGrado;
        TextView txtCarrera;
        TextView txtanio;

        public ViewHolder(View itemView) {
            super(itemView);
            careerCard = (CardView) itemView.findViewById(R.id.careerCard);
            txtnombre = (TextView) itemView.findViewById(R.id.txtnombre);
            txtUniversidad = (TextView) itemView.findViewById(R.id.txtUniversidad);
            txtGrado = (TextView) itemView.findViewById(R.id.txtGrado);
            txtCarrera = (TextView) itemView.findViewById(R.id.txtCarrera);
            txtanio = (TextView) itemView.findViewById(R.id.txtanio);
        }
    }
}
