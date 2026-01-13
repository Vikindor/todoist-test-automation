package io.github.vikindor.android.ui.screens;

import io.github.vikindor.android.ui.components.*;
import io.github.vikindor.android.ui.screens.auth.*;
import io.github.vikindor.android.ui.screens.main.*;
import io.github.vikindor.android.ui.screens.projects.*;

public class Screens {

    // Login

    public static WelcomeScreen welcomeScreen() {
        return new WelcomeScreen();
    }

    public static GoogleScreen googleScreen() {
        return new GoogleScreen();
    }

    public static LoginScreen loginScreen() {
        return new LoginScreen();
    }

    // Projects

    public static NavigationBar navigationBar() {
        return new NavigationBar();
    }

    public static BrowseScreen browseScreen() {
        return new BrowseScreen();
    }

    public static AddProjectScreen addProjectScreen() {
        return new AddProjectScreen();
    }

    public static ProjectScreen projectScreen() {
        return new ProjectScreen();
    }

    public static EditProjectScreen editProjectScreen() {
        return new EditProjectScreen();
    }

    public static AlertDialog alertDialog() {
        return new AlertDialog();
    }

    // Tasks

    public static InboxScreen inbox() {
        return new InboxScreen();
    }

    public static QuickAddButton quickAdd() {
        return new QuickAddButton();
    }

    public static QuickAddItemContainer quickAddItemContainer() {
        return new QuickAddItemContainer();
    }

    public static CreateItemContainer createItemContainer() {
        return new CreateItemContainer();
    }

    public static ItemDetailsContainer itemDetailsContainer() {
        return new ItemDetailsContainer();
    }

    public static SnackBar snackBar() {
        return new SnackBar();
    }
}
