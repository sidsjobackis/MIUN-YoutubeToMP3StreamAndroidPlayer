package miun.player;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SongAdapter extends ArrayAdapter<String>{

	private ArrayList<String> songItems;
	
	public SongAdapter(Context context, int textViewResourceId, ArrayList<String> songItems) {
        super(context, textViewResourceId, songItems);
        this.songItems = songItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        if (v == null) {
	            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.playlist_object, null);
	        }
	        String song = songItems.get(position);
	        if (song != "") {
	                TextView tv_songObject = (TextView) v.findViewById(R.id.tv_song_object_text);
	                if (tv_songObject != null) {
	                	tv_songObject.setText( song );                            
	                }	
	        }
	        return v;
	}

}
