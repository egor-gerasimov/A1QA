package project.form;

import framework.driver.Driver;
import framework.form.BaseFrom;

public class StoreForm extends BaseFrom {

    public boolean atPage() {
        return Driver.getInstance().getCurrentUrl().contains("https://store.steampowered.com/");
    }
}
