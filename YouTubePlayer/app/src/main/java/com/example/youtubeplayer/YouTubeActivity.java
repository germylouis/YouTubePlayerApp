package com.example.youtubeplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YouTubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBa-VabhhOceOIBbIOf5o9pcUkhP6qSDlc";
    static final String YOTUBE_VIDEO_ID = "q_hBf1ghAGE";
    static final String YOUTUBE_PLAYLIST = "PLcq6prxrEEUrogAxV3vptY16QpKUFQyYb";

    //https://www.youtube.com/watch?v=q_hBf1ghAGE&list=PLcq6prxrEEUrogAxV3vptY16QpKUFQyYb&index=65&t=0s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_you_tube, null);
        setContentView(layout);

//        Button button1 = new Button(this);
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
//        button1.setText("Button added");
//        layout.addView(button1);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d( TAG, "onInitializationSuccess: provider is " + provider.getClass().toString() );
        Toast.makeText( this, "initialized Youtube player successfully", Toast.LENGTH_LONG ).show();

        youTubePlayer.setPlaybackEventListener( playbackEventListener );
        youTubePlayer.setPlayerStateChangeListener( playerStateChangeListener );
        if (!wasRestored){
            youTubePlayer.cueVideo( YOTUBE_VIDEO_ID );
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText( YouTubeActivity.this, "Video playing is ok", Toast.LENGTH_SHORT ).show();

        }

        @Override
        public void onPaused() {
            Toast.makeText( YouTubeActivity.this, "Video is paused", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText( YouTubeActivity.this, "Video stopped", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private  YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText( YouTubeActivity.this, "Click add now", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText( YouTubeActivity.this, "Video has started", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText( YouTubeActivity.this, "Video completed", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
