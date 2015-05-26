package com.srini.musicplayer;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, OnCompletionListener{
	
    MediaPlayer mMediaPlayer = null;   
    private final IBinder mBinder = new LocalBinder();
    public long trackID = -1;
    public Boolean isPlaying = false;
    
    public class LocalBinder extends Binder {
    	MusicPlayerService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MusicPlayerService.this;
        }
    }
    
    @Override
    public void onDestroy() {
    	if(mMediaPlayer != null){
			mMediaPlayer.stop();
			isPlaying = false;
			mMediaPlayer = null;
		}
    	super.onDestroy();
    }
    
    
	@Override
	public IBinder onBind(Intent intent){
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		if(mMediaPlayer != null){
			mMediaPlayer.stop();
			isPlaying = false;
			mMediaPlayer = null;
		}
		return super.onUnbind(intent);
	}

    /** Called when MediaPlayer is ready */
	@Override
    public void onPrepared(MediaPlayer player) {
		if(trackID != -1){
			player.start();
			isPlaying = true;
		}
    }
    
    public void initPlayer(){
    	mMediaPlayer = new MediaPlayer();
    	mMediaPlayer.setLooping(false);
    	mMediaPlayer.setOnCompletionListener(this);
        //mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
    }
    
    public void play(){
    	mMediaPlayer.start();
    	isPlaying = true;
    }
    
    public void pause(){
    	mMediaPlayer.pause();
    	isPlaying = false;
    }
    
    public void setSong(String song, long trackID){
    	try {
    		mMediaPlayer.reset();
			mMediaPlayer.setDataSource(song);
			this.trackID = trackID;
			mMediaPlayer.setOnPreparedListener(this);
	        mMediaPlayer.prepareAsync(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public JSONObject getCurrentTrackDetails(){
    	JSONObject trackDetails = new JSONObject();
    	try {
			trackDetails.put("id", trackID);
			trackDetails.put("currentPosition", mMediaPlayer.getCurrentPosition());
			trackDetails.put("isPlaying", isPlaying);
			trackDetails.put("isPaused", false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return trackDetails;
    }


	@Override
	public void onCompletion(MediaPlayer mp) {
		isPlaying = false;		
	}
}
