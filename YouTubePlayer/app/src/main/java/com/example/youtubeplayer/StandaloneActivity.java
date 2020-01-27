package com.example.youtubeplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_standalone );

        Button btnPlayVideo = (Button) findViewById( R.id.btnPlayVideo );
        Button btnPlayList = (Button) findViewById( R.id.btnPlayList );

        btnPlayVideo.setOnClickListener( this );
        btnPlayList.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent( this, YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOTUBE_VIDEO_ID, 0, true, false );
                break;
            case R.id.btnPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent( this, YouTubeActivity.GOOGLE_API_KEY, YouTubeActivity.YOUTUBE_PLAYLIST, 0, 0, true, true );
                break;
                default:
                    break;
        }

        if (intent != null){
            startActivity( intent );
        }
    }
}
