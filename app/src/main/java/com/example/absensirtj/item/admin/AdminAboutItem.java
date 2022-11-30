package com.example.absensirtj.item.admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.models.AboutModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import info.androidhive.fontawesome.FontDrawable;

public class AdminAboutItem extends RecyclerView.Adapter<AdminAboutItem.ItemRowHolder>
{
    private Context mContext;
    private List<AboutModel> datalist;
    private int rowLayout;

    public AdminAboutItem(Context mContext, List<AboutModel> datalist, int rowLayout) {
        this.mContext = mContext;
        this.datalist = datalist;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @NotNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminAboutItem.ItemRowHolder holder, int position) {
        final AboutModel singleItem = datalist.get(position);
        holder.phone.setText(singleItem.getPhone());
        holder.email.setText(singleItem.getMail());
        holder.alamat.setText(singleItem.getAdress());
        holder.moto.setText(singleItem.getMoto());
        holder.logoKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.URL_WEBSITEPT));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });
        holder.kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(mContext, AdminActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(a);
            }
        });
        holder.phoneklik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+singleItem.getPhone();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });
        FontDrawable drawablePhone = new FontDrawable(mContext,R.string.fa_phone_solid,true,false);
        drawablePhone.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        holder.phoneklik.setImageDrawable(drawablePhone);
        FontDrawable drawableMail = new FontDrawable(mContext,R.string.fa_envelope,true,false);
        drawableMail.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        holder.gmailklik.setImageDrawable(drawableMail);
    }

    @Override
    public int getItemCount() {
        return (null != datalist ? datalist.size() : 0);
    }

    static class ItemRowHolder extends RecyclerView.ViewHolder{
        ImageView logoKlik,phoneklik,gmailklik;
        TextView phone,email,moto,alamat,kembali;
        public ItemRowHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            phoneklik = itemView.findViewById(R.id.phoneClickadmin);
            logoKlik = itemView.findViewById(R.id.logoptklik);
            phone = itemView.findViewById(R.id.smartphoneaboutadmintext);
            email = itemView.findViewById(R.id.emailaboutadmintext);
            moto = itemView.findViewById(R.id.motoperusahaanaboutadmin);
            alamat = itemView.findViewById(R.id.alamatperusahaanaboutadmin);
            kembali = itemView.findViewById(R.id.buttonkembaliaboutadminklik);
            gmailklik = itemView.findViewById(R.id.gmailClickUsers);
        }
    }
}
