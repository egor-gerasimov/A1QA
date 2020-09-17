import static framework.utils.Logger.*;
import static org.testng.Assert.assertTrue;

import framework.driver.Driver;
import framework.utils.PropertyManager;
import framework.utils.Waits;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import project.form.StoreForm;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        PropertyManager.readProperties();
        clearLog();
        Driver.maximizeWindow();
        Waits.setImplicitlyWait();
    }

    @BeforeMethod
    public void openPage() {
        Driver.getMainUrl();
        StoreForm storeForm = new StoreForm();
        assertTrue(storeForm.atPage(), "Wrong page");
    }

    @AfterClass
    public void closeDriver() {
        Driver.quitDriver();
    }
}
