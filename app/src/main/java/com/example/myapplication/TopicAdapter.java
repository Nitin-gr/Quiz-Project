package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<String> topics;
    private OnTopicClickListener onTopicClickListener;

    public TopicAdapter(List<String> topics, OnTopicClickListener onTopicClickListener) {
        this.topics = topics;
        this.onTopicClickListener = onTopicClickListener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        String topic = topics.get(position);
        holder.topicText.setText(topic);

        holder.itemView.setOnClickListener(v -> onTopicClickListener.onTopicClick(topic));
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView topicText;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicText = itemView.findViewById(R.id.topicText);  // Make sure this ID matches item_topic.xml
        }
    }

    public interface OnTopicClickListener {
        void onTopicClick(String topic);
    }
}
