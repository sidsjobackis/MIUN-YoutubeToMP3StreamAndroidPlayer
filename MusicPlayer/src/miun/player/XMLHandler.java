package miun.player;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLHandler extends DefaultHandler {

	private String youtubeURL;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		youtubeURL = new String(ch, start, length);
		Log.i("XMLHANDLER", "Youtube string saved successfully");
	}
	
	
	public String returnYoutubeURL() {
		return this.youtubeURL;
	}

}
