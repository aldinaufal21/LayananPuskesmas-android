package id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.Model.Pemeriksaan;
import id.ac.id.telkomuniversity.tass.puskesmasmulyaharja.R;

public class PemeriksaanListAdapter extends RecyclerView.Adapter<PemeriksaanListAdapter.PemeriksaanViewHolder> {
    private ArrayList<Pemeriksaan> list;
    private final OnItemClickListener listener;

    public PemeriksaanListAdapter(ArrayList<Pemeriksaan> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PemeriksaanListAdapter.PemeriksaanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pemeriksaan, parent, false);
        return new PemeriksaanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemeriksaanListAdapter.PemeriksaanViewHolder holder, int position) {
        Pemeriksaan item = list.get(position);
        holder.nama.setText(item.getPasien().getNama());
        int status = item.getStatus();
        if(status >= 2 && status <= 4) {
            holder.status.setText("Pemeriksaan Online");
            holder.icon.setImageResource(R.drawable.online_icon);
        } else if(status == 5) {
            holder.status.setText("Pemeriksaan Offline");
            holder.icon.setImageResource(R.drawable.offline_icon);
        }
        holder.tanggal.setText(item.getTanggal());
        holder.statusDetail.setText(item.getStatusDetail());
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

    public class PemeriksaanViewHolder extends RecyclerView.ViewHolder {
        private TextView status, nama, tanggal, statusDetail;
        private ImageView icon;

        public PemeriksaanViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.itemPemeriksaanStatus);
            nama = itemView.findViewById(R.id.itemPemeriksaanNama);
            tanggal = itemView.findViewById(R.id.itemPemeriksaanTanggal);
            statusDetail = itemView.findViewById(R.id.itemPemeriksaanStatusDetail);
            icon = itemView.findViewById(R.id.itemPemeriksaanIconStatus);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Pemeriksaan item);
    }
}
