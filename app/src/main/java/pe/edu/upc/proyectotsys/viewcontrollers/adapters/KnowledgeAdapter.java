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
import pe.edu.upc.proyectotsys.models.Knowledge;

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {
    List<Knowledge> knowledge;
    public void setDetail(List<Knowledge> lista) {
        this.knowledge = lista;
    }

    @NonNull
    @Override
    public KnowledgeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KnowledgeAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_knowledge, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeAdapter.ViewHolder holder, int position) {
        holder.txtnombre.setText(knowledge.get(position).getDni().getDni() + " - " + knowledge.get(position).getDni().getName() + " " + knowledge.get(position).getDni().getLastname());
        holder.txtTheme.setText(knowledge.get(position).getId_theme().getId_theme() + " - " + knowledge.get(position).getId_theme().getName_theme());
//        holder.txtGrado.setText(detail.get(position).getCode_grade().getDescription_grade());
        holder.txtprice.setText(Double.toString(knowledge.get(position).getPrice()));
        holder.txtCarrera.setText(knowledge.get(position).getId_theme().getCode_career().getDescription_career());
    }

    @Override
    public int getItemCount() {
        return knowledge.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView knowledgeCard;
        TextView txtnombre;
        TextView txtprice;
        TextView txtTheme;
        TextView txtCarrera;
        public ViewHolder(View itemView) {
            super(itemView);
            knowledgeCard = (CardView) itemView.findViewById(R.id.knowledgeCard);
            txtnombre = (TextView) itemView.findViewById(R.id.txtNombreAsesor);
            txtprice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtTheme = (TextView) itemView.findViewById(R.id.txtTheme);
            txtCarrera = (TextView) itemView.findViewById(R.id.txtCarrera);
        }
    }
}
