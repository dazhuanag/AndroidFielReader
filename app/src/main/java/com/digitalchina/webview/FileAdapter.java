package com.digitalchina.webview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FileMoudel> fileMoudelList;
    private Context context;
    private LayoutInflater inflater;


    public FileAdapter(List<FileMoudel> fileMoudelList, Context context) {
        this.fileMoudelList = fileMoudelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
