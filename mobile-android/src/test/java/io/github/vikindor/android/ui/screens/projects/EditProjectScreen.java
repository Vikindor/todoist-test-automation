package io.github.vikindor.android.ui.screens.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class EditProjectScreen {

    private final SelenideElement
            nameInput = $(id("com.todoist:id/name")),
            doneButton = $(id("com.todoist:id/menu_form_submit"));

    public EditProjectScreen setName(String name){
        nameInput.click();
        nameInput.sendKeys(name);
        return this;
    }

    public EditProjectScreen tapDone(){
        doneButton.click();
        return this;
    }
}
