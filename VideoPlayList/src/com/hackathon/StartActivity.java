package com.hackathon;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class StartActivity extends ListActivity {

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setListAdapter(new VideoAdapter(this, new MockYouTubeResults().createSampleVideoList()));

        setContentView(R.layout.playlist_layout);
    }



    class VideoAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<YouTubeVideo> videos;

        public VideoAdapter(Context ctx, ArrayList<YouTubeVideo> videos) {
            inflater = LayoutInflater.from(ctx);
            this.videos = videos;
        }

        @Override
        public int getCount() {
            return videos.size();
        }

        @Override
        public Object getItem(int position) {
            return videos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return videos.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            VideoViewHolder holder;
            YouTubeVideo vid = videos.get(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.playlist_item_layout, null);
                holder = new VideoViewHolder();
                holder.thumbnailIV = (ImageView) convertView.findViewById(R.id.video_thumbnail);
                holder.titleTV = (TextView) convertView.findViewById(R.id.video_title);
                holder.title2TV = (TextView) convertView.findViewById(R.id.video_title_line_2);
                convertView.setTag(holder);
            } else {
                holder = (VideoViewHolder) convertView.getTag();
            }
            holder.id = vid.getId();
            holder.titleTV.setText(vid.getVideoTitle());
            holder.title2TV.setText(vid.getVideoTitle2());
            //TODO: set image view

            return convertView;
        }
    }

    class VideoViewHolder {
        public long id;
        public ImageView thumbnailIV;
        public TextView titleTV;
        public TextView title2TV;
        
    }
}