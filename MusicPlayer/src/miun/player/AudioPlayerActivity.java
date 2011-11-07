package miun.player;

import java.io.FileDescriptor;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.SweepGradient;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AudioPlayerActivity extends Activity{
	
	private String m3uLink;
	private MediaPlayer mp = new MediaPlayer();
	private Button playButton = null;
	private Button muteButton = null;
	private TextView backgroundTitle = null;
	private AudioManager mAm;
	private boolean mIsMute;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audioplayer);

        this.playButton = (Button) this.findViewById(R.id.bt_playbutton);
        this.muteButton = (Button) this.findViewById(R.id.bt_mute);
        this.backgroundTitle = (TextView) this.findViewById(R.id.tv_songtitle);
        
        mAm = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        
        Bundle extras = getIntent().getExtras();
        this.m3uLink = extras.getString("m3uLink");
        this.backgroundTitle.setText( extras.getString("songTitle") );
        	 
        playSong();
	    
        //If play button is clicked
        this.playButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mp.isPlaying()) {
					switchPlayButtonState(mp.isPlaying()); // Switch button icon
					mp.pause();
				} else {
					switchPlayButtonState(mp.isPlaying()); // Switch button icon
					//mp.resume(); //TODO Which one to use?
					mp.start();
				}
			}
		});
        
		// If mute button is clicked
		this.muteButton.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				switchMuteButtonState(mIsMute);
				isMute();
			}
		});
	}
	
	 
	 /**
	  * Plays given song
	  */
	 private void playSong() {
		try {
			mp.reset();
						
			mp.setDataSource(m3uLink);
			mp.prepare();
			mp.start();
			//mIsMute = false; //Set sound mutable
		} catch (IOException e) {
			Log.v(getString(R.string.app_name), e.getMessage());
		}
	}
	 
	
	 /**
	  * Mute mediaplayer sound
	  */
	 public void isMute() {
		 		 
		if (mIsMute) {
			try {
				mAm.setStreamMute(AudioManager.STREAM_MUSIC, false);
			} catch (Exception e) {
				Log.e("SET MUTE", e.getMessage() );
			}
			mIsMute = false;

		} else {
			try {
				mAm.setStreamMute(AudioManager.STREAM_MUSIC, true);
			} catch (Exception e) {
				Log.e("SET MUTE", e.getMessage() );
			}
			mIsMute = true;
		}
	 }
	 
	 
	/**
	 * Switch background text for the mute/unmute button depending on current
	 * state (audioOn)
	 */
	public void switchMuteButtonState(boolean audioOn) {
		if (audioOn) {
			this.muteButton.setText("Mute");
		} else {
			this.muteButton.setText("UnMute");
		}
	}
	
	
	/**
	 * Switch background text for the mute/unmute button depending on current
	 * state (audioOn)
	 */
	public void switchPlayButtonState(boolean isPlaying) {
		if (isPlaying) {
			this.playButton.setText("Play");
		} else {
			this.playButton.setText("Pause");
		}
	}
	 
	 
	/**
	 * Override finish method
	 */
	@Override
	public void finish() {
		mp.reset();
		Intent exit = new Intent();
		setResult(RESULT_OK, exit);
		super.finish();
	}
	
}
