package com.example.youtubedownloader;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import at.huber.youtubeExtractor.YtFile;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    Context context;
    SparseArray<YtFile> files;
    OnItemClickListener onItemClickListener;

    public ResultAdapter(Context context, SparseArray<YtFile> files) {
        this.context = context;
        this.files = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.result_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.res.setText(String.valueOf(files.get(files.keyAt(position)).getFormat().getHeight()));
        holder.ext.setText(String.valueOf(files.get(files.keyAt(position)).getFormat().getExt()));
        holder.download.setOnClickListener(view -> holder.itemView.performClick());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(files.get(files.keyAt(position))));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView res, ext;
        MaterialButton download;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            res = itemView.findViewById(R.id.list_item_res);
            ext = itemView.findViewById(R.id.list_item_ext);
            download = itemView.findViewById(R.id.list_item_download);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(YtFile ytFile);
    }
}