package com.example.newsapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newsapp.models.Article;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
    private final Context context;
    private List<Article> list;

    public ArticleAdapter(Context context, List<Article> list) {
        this.context = context;
        this.list = list;
    }

    public void setFilter(List<Article> filtered) {
        this.list = filtered;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Article getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);

        TextView title = view.findViewById(android.R.id.text1);
        TextView views = view.findViewById(android.R.id.text2);

        Article article = list.get(i);
        title.setText(article.getTitle());
        views.setText("Lượt xem: " + article.getViews());

        return view;
    }
}
