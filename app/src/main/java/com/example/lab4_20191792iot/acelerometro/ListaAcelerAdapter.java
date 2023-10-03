package com.example.lab4_20191792iot.acelerometro;
import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4_20191792iot.R;
import com.example.lab4_20191792iot.dto.Person;
import java.util.ArrayList;
import java.util.List;

public class ListaAcelerAdapter extends RecyclerView.Adapter<ListaAcelerAdapter.AcelerometroViewHolder> {
    private List<Person> listaAceler;
    private Context context;
    private PersonasAcelerometroVM personasAcelerometroVM;
    @NonNull
    @Override
    public AcelerometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paraadapter, parent, false);
        return new AcelerometroViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AcelerometroViewHolder holder, int position) {
        Person persona = listaAceler.get(position);
        holder.persona = persona;
        ImageView fotoPersona = holder.itemView.findViewById(R.id.imagenPersona);
        TextView genero = holder.itemView.findViewById(R.id.generoPersona);
        TextView phone = holder.itemView.findViewById(R.id.phonePersona);
        TextView email = holder.itemView.findViewById(R.id.emailPersona);
        TextView nombre = holder.itemView.findViewById(R.id.nombrePersona);
        TextView ciudad = holder.itemView.findViewById(R.id.ciudadPersona);
        TextView pais = holder.itemView.findViewById(R.id.paisPersona);
        ImageView eliminarImagen = holder.itemView.findViewById(R.id.imageView2);
        Glide.with(context).load(persona.getPicture().getLarge()).into(fotoPersona);
        genero.setText("Género: "+persona.getGender());
        phone.setText("Phone: "+ persona.getPhone());
        email.setText("Email: "+persona.getEmail());
        ciudad.setText("Ciudad: "+persona.getLocation().getCity());
        pais.setText("País: "+persona.getLocation().getCountry());
        nombre.setText(persona.getName().getTitle()+" "+persona.getName().getFirst()+" "+persona.getName().getLast());
        eliminarImagen.setOnClickListener(view -> {
            ArrayList<Person> listaActual = personasAcelerometroVM.getListaPersonasAcelerometro().getValue();
            listaActual.remove(persona);
            personasAcelerometroVM.getListaPersonasAcelerometro().postValue(listaActual);
            notifyDataSetChanged();
        });
    }
    @Override
    public int getItemCount() {
        return listaAceler.size();
    }
    public class AcelerometroViewHolder extends RecyclerView.ViewHolder{
        Person persona;
        public AcelerometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public List<Person> getListaAceler() {
        return listaAceler;
    }

    public void setListaAceler(List<Person> listaAceler) {
        this.listaAceler = listaAceler;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PersonasAcelerometroVM getPersonasAcelerometroVM() {
        return personasAcelerometroVM;
    }

    public void setPersonasAcelerometroVM(PersonasAcelerometroVM personasAcelerometroVM) {
        this.personasAcelerometroVM = personasAcelerometroVM;
    }
}
