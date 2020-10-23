package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PhotoForm extends Form {

    private final ILink photo = getElementFactory().getLink(By.xpath("//*[@id='pv_photo']/img"), "Photo");
    private final IButton btnClose = getElementFactory().getButton(By.className("pv_close_btn"), "Close");

    public PhotoForm() {
        super(By.className("pv_cont"), "Photo");
    }

    public String getPhotoURL() {
        return photo.getAttribute("src");
    }

    public void close() {
        btnClose.click();
    }
}
