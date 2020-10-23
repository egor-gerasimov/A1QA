package forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PhotoForm extends Form {

    private final ILink photo = getElementFactory().getLink(By.xpath("//*[@id='pv_photo']/img"), "Photo");

    public PhotoForm() {
        super(By.className("pv_cont"), "Photo");
    }

    public String getPhotoURL() {
        return photo.getHref();
    }
}
