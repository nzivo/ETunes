package ke.co.batedesigns.etunes.Model;

import java.io.Serializable;

public class AudioModel implements Serializable {
    private String aPath;
    private String aName;
    private String aAlbum;
    private String aArtist;
    private int aDuration;
    private int aYear;

    public String getaPath() {
        return aPath;
    }

    public void setaPath(String aPath) {
        this.aPath = aPath;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaAlbum() {
        return aAlbum;
    }

    public void setaAlbum(String aAlbum) {
        this.aAlbum = aAlbum;
    }

    public String getaArtist() {
        return aArtist;
    }

    public void setaArtist(String aArtist) {
        this.aArtist = aArtist;
    }

    public int getaDuration() {
        return aDuration;
    }

    public void setaDuration(int aDuration) {
        this.aDuration = aDuration;
    }

    public int getaYear() {
        return aYear;
    }

    public void setaYear(int aYear) {
        this.aYear = aYear;
    }
}
