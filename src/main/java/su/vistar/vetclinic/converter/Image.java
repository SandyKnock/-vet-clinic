package su.vistar.vetclinic.converter;

/**
 * Created by Владислав on 13.03.2017.
 */
public class Image {
    private String id;
    private byte[] image;

    public Image(String id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
