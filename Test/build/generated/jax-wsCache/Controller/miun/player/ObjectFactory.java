
package miun.player;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the miun.player package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginAndFetchPlaylistsResponse_QNAME = new QName("http://player.miun/", "loginAndFetchPlaylistsResponse");
    private final static QName _FetchVideo_QNAME = new QName("http://player.miun/", "fetchVideo");
    private final static QName _SetAuthenticationKeyResponse_QNAME = new QName("http://player.miun/", "setAuthenticationKeyResponse");
    private final static QName _FetchUserPlaylistResponse_QNAME = new QName("http://player.miun/", "fetchUserPlaylistResponse");
    private final static QName _FetchUserPlaylist_QNAME = new QName("http://player.miun/", "fetchUserPlaylist");
    private final static QName _LoginAndFetchPlaylists_QNAME = new QName("http://player.miun/", "loginAndFetchPlaylists");
    private final static QName _FetchVideoResponse_QNAME = new QName("http://player.miun/", "fetchVideoResponse");
    private final static QName _SetAuthenticationKey_QNAME = new QName("http://player.miun/", "setAuthenticationKey");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: miun.player
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FetchUserPlaylist }
     * 
     */
    public FetchUserPlaylist createFetchUserPlaylist() {
        return new FetchUserPlaylist();
    }

    /**
     * Create an instance of {@link SetAuthenticationKey }
     * 
     */
    public SetAuthenticationKey createSetAuthenticationKey() {
        return new SetAuthenticationKey();
    }

    /**
     * Create an instance of {@link FetchVideo }
     * 
     */
    public FetchVideo createFetchVideo() {
        return new FetchVideo();
    }

    /**
     * Create an instance of {@link LoginAndFetchPlaylistsResponse }
     * 
     */
    public LoginAndFetchPlaylistsResponse createLoginAndFetchPlaylistsResponse() {
        return new LoginAndFetchPlaylistsResponse();
    }

    /**
     * Create an instance of {@link FetchUserPlaylistResponse }
     * 
     */
    public FetchUserPlaylistResponse createFetchUserPlaylistResponse() {
        return new FetchUserPlaylistResponse();
    }

    /**
     * Create an instance of {@link LoginAndFetchPlaylists }
     * 
     */
    public LoginAndFetchPlaylists createLoginAndFetchPlaylists() {
        return new LoginAndFetchPlaylists();
    }

    /**
     * Create an instance of {@link FetchVideoResponse }
     * 
     */
    public FetchVideoResponse createFetchVideoResponse() {
        return new FetchVideoResponse();
    }

    /**
     * Create an instance of {@link SetAuthenticationKeyResponse }
     * 
     */
    public SetAuthenticationKeyResponse createSetAuthenticationKeyResponse() {
        return new SetAuthenticationKeyResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginAndFetchPlaylistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "loginAndFetchPlaylistsResponse")
    public JAXBElement<LoginAndFetchPlaylistsResponse> createLoginAndFetchPlaylistsResponse(LoginAndFetchPlaylistsResponse value) {
        return new JAXBElement<LoginAndFetchPlaylistsResponse>(_LoginAndFetchPlaylistsResponse_QNAME, LoginAndFetchPlaylistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchVideo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "fetchVideo")
    public JAXBElement<FetchVideo> createFetchVideo(FetchVideo value) {
        return new JAXBElement<FetchVideo>(_FetchVideo_QNAME, FetchVideo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAuthenticationKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "setAuthenticationKeyResponse")
    public JAXBElement<SetAuthenticationKeyResponse> createSetAuthenticationKeyResponse(SetAuthenticationKeyResponse value) {
        return new JAXBElement<SetAuthenticationKeyResponse>(_SetAuthenticationKeyResponse_QNAME, SetAuthenticationKeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchUserPlaylistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "fetchUserPlaylistResponse")
    public JAXBElement<FetchUserPlaylistResponse> createFetchUserPlaylistResponse(FetchUserPlaylistResponse value) {
        return new JAXBElement<FetchUserPlaylistResponse>(_FetchUserPlaylistResponse_QNAME, FetchUserPlaylistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchUserPlaylist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "fetchUserPlaylist")
    public JAXBElement<FetchUserPlaylist> createFetchUserPlaylist(FetchUserPlaylist value) {
        return new JAXBElement<FetchUserPlaylist>(_FetchUserPlaylist_QNAME, FetchUserPlaylist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginAndFetchPlaylists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "loginAndFetchPlaylists")
    public JAXBElement<LoginAndFetchPlaylists> createLoginAndFetchPlaylists(LoginAndFetchPlaylists value) {
        return new JAXBElement<LoginAndFetchPlaylists>(_LoginAndFetchPlaylists_QNAME, LoginAndFetchPlaylists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchVideoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "fetchVideoResponse")
    public JAXBElement<FetchVideoResponse> createFetchVideoResponse(FetchVideoResponse value) {
        return new JAXBElement<FetchVideoResponse>(_FetchVideoResponse_QNAME, FetchVideoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAuthenticationKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "setAuthenticationKey")
    public JAXBElement<SetAuthenticationKey> createSetAuthenticationKey(SetAuthenticationKey value) {
        return new JAXBElement<SetAuthenticationKey>(_SetAuthenticationKey_QNAME, SetAuthenticationKey.class, null, value);
    }

}
