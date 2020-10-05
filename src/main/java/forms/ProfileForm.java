package forms;

import static utils.StringUtils.toMultiOS;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;

public class ProfileForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ICheckBox unselectAll = elementFactory.getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "Unselect All");
    private final IButton uploadImage = elementFactory.getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload image");
    private final IButton next = elementFactory.getButton(By.xpath("//button[.='Next']"), "Next");

    private final By interestsLocator = By.xpath("//div[@class='avatar-and-interests__interests-list__item']//label");

    private final String imagePath = toMultiOS(System.getProperty("user.dir") + "/src/test/resources/great.png");
    private final String uploadScriptPath = toMultiOS(System.getProperty("user.dir") + "/src/test/resources/fileUpload.exe");

    public ProfileForm(By locator, String name) {
        super(locator, name);
    }

    public List<ICheckBox> getInterests() {
        List<ICheckBox> interests = elementFactory.findElements(interestsLocator, ElementType.CHECKBOX);
        interests.removeIf((o) -> o.getAttribute("for").equals("interest_selectall") ||
            o.getAttribute("for").equals("interest_unselectall"));
        return interests;
    }

    public void checkThreeInterests() {
        unselectAll.click();
        List<ICheckBox> interests = getInterests();
        for (int i = 0; i < 3; i++) {
            int rand = new Random().nextInt(interests.size());
            interests.get(rand).click();
            interests.remove(rand);
        }
    }

    public void uploadImage() {
        uploadImage.click();
        uploadScriptImage();
    }

    private void uploadScriptImage() {
        try {
            Process p = Runtime.getRuntime().exec(uploadScriptPath + " " + imagePath);
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickNext() {
        next.click();
    }
}
