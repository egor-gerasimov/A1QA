package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILabel lblTitle = elementFactory.getLabel(By.xpath("//div[@class='example']/*"), "Title");
    private final String idIFrame = "mce_0_ifr";
    private final IFrameForm iFrameForm = new IFrameForm(By.id(idIFrame), "iFrame");
    private final IButton btnBold = elementFactory.getButton(By.id("mceu_3"), "Bold");


    public MainForm() {
        super(By.xpath("/html"), "Main");
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public IFrameForm getIFrameForm() {
        return iFrameForm;
    }

    public String getIdIFrame() {
        return idIFrame;
    }

    public void clickBold() {
        btnBold.click();
    }
}
