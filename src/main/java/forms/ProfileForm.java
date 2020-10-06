package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import utils.Utils;

public class ProfileForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ICheckBox ckbUnselectAll = elementFactory.getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "Unselect All");
    private final IButton btnUploadImage = elementFactory.getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload image");
    private final IButton btnNext = elementFactory.getButton(By.xpath("//button[.='Next']"), "Next");

    private final By interestsLocator = By.xpath("//div[@class='avatar-and-interests__interests-list__item']//label");

    public ProfileForm() {
        super(By.className("avatar-and-interests__form"), "Profile");
    }

    public List<ICheckBox> getInterests() {
        List<ICheckBox> interests = elementFactory.findElements(interestsLocator, ElementType.CHECKBOX);
        interests.removeIf((o) -> o.getAttribute("for").equals("interest_selectall") ||
            o.getAttribute("for").equals("interest_unselectall"));
        return interests;
    }

    public void checkThreeInterests() {
        ckbUnselectAll.click();
        List<ICheckBox> interests = getInterests();
        for (int i = 0; i < 3; i++) {
            int rand = new Random().nextInt(interests.size());
            interests.get(rand).click();
            interests.remove(rand);
        }
    }

    public void uploadImage() {
        btnUploadImage.click();
        Utils.autoUploadImage();
    }

    public void clickNext() {
        btnNext.click();
    }
}
