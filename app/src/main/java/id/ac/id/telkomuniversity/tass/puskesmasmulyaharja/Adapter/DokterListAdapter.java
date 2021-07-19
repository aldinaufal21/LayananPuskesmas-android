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
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.R;

public class DokterListAdapter extends RecyclerView.Adapter<DokterListAdapter.DokterViewHolder> {
    private ArrayList<Dokter> list;
    private final OnItemClickListener listener;

    public DokterListAdapter(ArrayList<Dokter> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DokterListAdapter.DokterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dokter, parent, false);
        return new DokterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterListAdapter.DokterViewHolder holder, int position) {
        Dokter item = list.get(position);
        holder.nama.setText(item.getNama());
        holder.jenis_kelamin.setText(item.getJenis_kelamin());
        holder.ttl.setText(item.getTtl());
        holder.alamat.setText(item.getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public class DokterViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, jenis_kelamin, ttl, alamat;
        private ImageView foto;

        public DokterViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.itemDokterNama);
            jenis_kelamin = itemView.findViewById(R.id.itemDokterJenisKelamin);
            ttl = itemView.findViewById(R.id.itemDokterTTL);
            alamat = itemView.findViewById(R.id.itemDokterAlamat);
            foto = itemView.findViewById(R.id.itemDokterFoto);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Dokter item);
    }
}
