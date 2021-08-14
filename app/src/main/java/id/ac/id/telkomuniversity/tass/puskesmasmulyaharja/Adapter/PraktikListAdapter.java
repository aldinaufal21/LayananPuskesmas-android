package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Dokter;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Poli;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Praktik;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.R;

public class PraktikListAdapter extends RecyclerView.Adapter<PraktikListAdapter.PraktikViewHolder> {
    private ArrayList<Praktik> list;
    private final OnItemClickListener listener;

    public PraktikListAdapter(ArrayList<Praktik> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PraktikListAdapter.PraktikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dokter, parent, false);
        return new PraktikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PraktikListAdapter.PraktikViewHolder holder, int position) {
        Praktik praktik = list.get(position);
        Dokter dokter = praktik.getDokter();
        Poli poli = dokter.getPoli();
        holder.nama.setText(dokter.getNama());
        holder.jenis_kelamin.setText(dokter.getJenis_kelamin());
        holder.ttl.setText(dokter.getTtl());
        holder.alamat.setText(dokter.getAlamat());
        holder.poli.setText(poli.getNama_poli());
        holder.shift.setText(praktik.getJamShift());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(praktik));

        if (dokter.getJenis_kelamin().equalsIgnoreCase("Laki-Laki")){
            holder.foto.setImageResource(R.drawable.doctor_male);
        } else {
            holder.foto.setImageResource(R.drawable.doctor_female);
        }
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public class PraktikViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, jenis_kelamin, ttl, alamat, poli, shift;
        private ImageView foto;

        public PraktikViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.itemDokterNama);
            jenis_kelamin = itemView.findViewById(R.id.itemDokterJenisKelamin);
            ttl = itemView.findViewById(R.id.itemDokterTTL);
            alamat = itemView.findViewById(R.id.itemDokterAlamat);
            foto = itemView.findViewById(R.id.itemDokterFoto);
            poli = itemView.findViewById(R.id.itemDokterPoli);
            shift = itemView.findViewById(R.id.itemDokterJamShift);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Praktik item);
    }
}
