package pe.edu.upc.proyectotsys.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;

public class AdvisorAdapter extends RecyclerView.Adapter<AdvisorAdapter.ViewHolder> {
    private List<Advisor> advisor;

    @NonNull
    @Override
    public AdvisorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_advisor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdvisorAdapter.ViewHolder holder, final int position) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        Picasso.with(holder.pictureImageView.getContext())
                .load(R.drawable.fondoprincipal2);

        holder.nameTextView.setText(advisor.get(position).getName() + " " + advisor.get(position).getLastname());
        holder.dniTextView.setText("DNI: " + advisor.get(position).getDni());
        holder.directionTextView.setText("Dirección: " + advisor.get(position).getAddress());

//        holder.advisorCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putString("curImagen", advisor.get(position).getPicture());
//                bundle.putString("curNombre", advisor.get(position).getName() + " " + advisor.get(position).getLastname());
//                bundle.putString("curDni", advisor.get(position).getDni());
//                bundle.putString("curBio", advisor.get(position).getAddress());
//                Intent iconIntent = new Intent(view.getContext(), .class);
//                iconIntent.putExtras(bundle);
//                view.getContext().startActivity(iconIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return advisor.size();
    }

    public AdvisorAdapter(List<Advisor> advisor) {
        this.advisor = advisor;
    }

    public List<Advisor> getItems(){
        return this.advisor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView advisorCardView;
        public ImageView pictureImageView;
        public TextView nameTextView;
        public TextView dniTextView;
        public TextView directionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            advisorCardView = (CardView) itemView.findViewById(R.id.advisor_card);
            pictureImageView = (ImageView) itemView.findViewById(R.id.pictureImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            dniTextView = (TextView) itemView.findViewById(R.id.dniTextView);
            directionTextView = (TextView) itemView.findViewById(R.id.directionTextView);
        }
    }
}
