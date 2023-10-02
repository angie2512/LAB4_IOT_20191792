package com.example.lab4_iot_20191792.magnetometro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4_iot_20191792.R;
import com.example.lab4_iot_20191792.dto.Person;

import java.util.ArrayList;
import java.util.List;

public class ListaMagnetAdapter extends RecyclerView.Adapter<ListaMagnetAdapter.MagnetometroViewHolder> {
    private List<Person> listaMagnet;
    private Context context;
    private PersonasMagnetometroVM personasMagnetometroVM;

    @NonNull
    @Override
    public MagnetometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paraadapter, parent, false);
        return new MagnetometroViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MagnetometroViewHolder holder, int position) {
        Person persona = listaMagnet.get(position);
        holder.persona = persona;
        ImageView fotoPersona = holder.itemView.findViewById(R.id.imagenPersona);
        TextView genero = holder.itemView.findViewById(R.id.generoPersona);
        TextView phone = holder.itemView.findViewById(R.id.phonePersona);
        TextView email = holder.itemView.findViewById(R.id.emailPersona);
        TextView nombre = holder.itemView.findViewById(R.id.nombrePersona);
        TextView ciudad = holder.itemView.findViewById(R.id.ciudadPersona);
        TextView pais = holder.itemView.findViewById(R.id.paisPersona);
        ImageView eliminarPersona = holder.itemView.findViewById(R.id.imageView2);
        Glide.with(context).load(persona.getPicture().getLarge()).into(fotoPersona);
        genero.setText("Género: "+persona.getGender());
        phone.setText("Phone: "+ persona.getPhone());
        email.setText("Email: "+persona.getEmail());
        ciudad.setText("Ciudad: "+persona.getLocation().getCity());
        pais.setText("País: "+persona.getLocation().getCountry());
        nombre.setText(persona.getName().getTitle()+" "+persona.getName().getFirst()+" "+persona.getName().getLast());
        eliminarPersona.setOnClickListener(view -> {
            ArrayList<Person> listaActual = personasMagnetometroVM.getListaPersonasMagnetometro().getValue();
            listaActual.remove(persona);
            personasMagnetometroVM.getListaPersonasMagnetometro().postValue(listaActual);
            notifyDataSetChanged();
        });
    }
    @Override
    public int getItemCount() {
        return listaMagnet.size();
    }
    public class MagnetometroViewHolder extends RecyclerView.ViewHolder{
        Person persona;
        public MagnetometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public List<Person> getListaMagnet() {
        return listaMagnet;
    }

    public void setListaMagnet(List<Person> listaMagnet) {
        this.listaMagnet = listaMagnet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PersonasMagnetometroVM getPersonasMagnetometroVM() {
        return personasMagnetometroVM;
    }

    public void setPersonasMagnetometroVM(PersonasMagnetometroVM personasMagnetometroVM) {
        this.personasMagnetometroVM = personasMagnetometroVM;
    }
}
