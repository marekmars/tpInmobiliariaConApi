package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle.pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.models.Pago;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;

    public PagosAdapter(Context context, List<Pago> pagos, LayoutInflater li) {
        this.context = context;
        this.pagos = pagos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codPago.setText(pagos.get(position).getIdPago()+"");
        holder.nroPago.setText(pagos.get(position).getNumero()+"");
        holder.codContrato.setText(pagos.get(position).getContrato().getId()+"");
        holder.monto.setText(pagos.get(position).getImporte()+"");
        holder.fechaPago.setText(pagos.get(position).getFechaDePago());


    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codPago;
        TextView nroPago;
        TextView monto;
        TextView codContrato;
        TextView fechaPago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codPago = itemView.findViewById(R.id.tvCodPagoItem);
            nroPago = itemView.findViewById(R.id.tvNroPagoItem);
            monto = itemView.findViewById(R.id.tvMontoItem);
            codContrato = itemView.findViewById(R.id.tvCodContratoItem);
            fechaPago = itemView.findViewById(R.id.tvFechaPagoItem);
        }
    }
}
