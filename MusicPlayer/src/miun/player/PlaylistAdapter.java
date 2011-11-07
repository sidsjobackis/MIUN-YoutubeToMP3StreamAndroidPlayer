package miun.player;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class PlaylistAdapter extends ArrayAdapter<Playlist>{

	private ArrayList<Playlist> playlistItems;
	
	public PlaylistAdapter(Context context, int textViewResourceId, ArrayList<Playlist> playlistItems) {
        super(context, textViewResourceId, playlistItems);
        this.playlistItems = playlistItems;
	}
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        if (v == null) {
	            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.playlist_object, null);
	        }
	        Playlist playlist = playlistItems.get(position);
	        if (playlist != null) {
	                TextView tv_playlistObject = (TextView) v.findViewById(R.id.tv_playlist_object_text);
	                if (tv_playlistObject != null) {
	                	tv_playlistObject.setText(playlist.getTitle());                            }
	        }
	        return v;
	}

}
