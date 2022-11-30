package com.example.absensirtj.item.users;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensirtj.R;
import com.example.absensirtj.activity.admin.AdminActivity;
import com.example.absensirtj.activity.users.UsersActivity;
import com.example.absensirtj.constants.Constant;
import com.example.absensirtj.models.SupportModel;
import com.example.absensirtj.utils.PicassoTrustAll;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import info.androidhive.fontawesome.FontDrawable;

public class UsersSupportItem extends RecyclerView.Adapter<UsersSupportItem.ItemRowHolder>
{
    private Context mContext;
    private List<SupportModel> datalist;
    private int rowLayout;

    public UsersSupportItem(Context mContext, List<SupportModel> datalist, int rowLayout) {
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
    public void onBindViewHolder(@NonNull @NotNull UsersSupportItem.ItemRowHolder holder, int position) {
        final SupportModel singleItem = datalist.get(position);
        holder.nama.setText(singleItem.getNames());
        holder.phone.setText(singleItem.getTlp());
        holder.email.setText(singleItem.getMail());
        String htmlText = singleItem.getAlamet();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.alamat.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.alamat.setText(Html.fromHtml(htmlText));
        }
        if (!singleItem.getFoto().isEmpty()){
            PicassoTrustAll.getInstance(mContext)
                    .load(Constant.IMAGESSUPPORT + singleItem.getFoto())
                    .resize(250,250)
                    .into(holder.profilesuport);
        }
        holder.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gith = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.URL_GITHUB));
                gith.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(gith);
            }
        });
        holder.portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cv = new Intent(Intent.ACTION_VIEW,Uri.parse(Constant.URL_PORTFOLIOSUPORT));
                cv.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(cv);
            }
        });
        holder.kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(mContext, UsersActivity.class);
                admin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(admin);
            }
        });
        FontDrawable drawable = new FontDrawable(mContext,R.string.fa_github,true,false);
        holder.github.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return (null != datalist ? datalist.size() : 0);
    }

    static class ItemRowHolder extends RecyclerView.ViewHolder{
        ImageView profilesuport,github,portfolio;
        TextView nama,phone,email,alamat;
        Button kembali;
        public ItemRowHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            profilesuport = itemView.findViewById(R.id.profilesuportusers);
            github = itemView.findViewById(R.id.githubuserstextusers);
            portfolio = itemView.findViewById(R.id.portfolioClickUsers);
            nama = itemView.findViewById(R.id.namasupportusers);
            phone = itemView.findViewById(R.id.phonesupportaboutusers);
            email = itemView.findViewById(R.id.emailsupportaboutusers);
            alamat = itemView.findViewById(R.id.alamatsupportaboutusers);
            kembali = itemView.findViewById(R.id.buttonkembalikliksupportusers);

        }
    }
}
