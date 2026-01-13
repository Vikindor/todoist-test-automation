package io.github.vikindor.web.ui.pages;

import io.github.vikindor.web.ui.components.*;
import io.github.vikindor.web.ui.pages.auth.*;
import io.github.vikindor.web.ui.pages.main.InboxPage;
import io.github.vikindor.web.ui.pages.projects.*;

public class Pages {

    // Login

    public static LoginPage loginPage() {
        return new LoginPage();
    }

    public static ForgotPasswordPage forgotPasswordPage() {
        return new ForgotPasswordPage();
    }

    public static SignUpPage signUpPage() {
        return new SignUpPage();
    }

    public static GooglePage googlePage() {
        return new GooglePage();
    }

    public static FacebookPage facebookPage() {
        return new FacebookPage();
    }

    public static ApplePage applePage() {
        return new ApplePage();
    }

    // Projects

    public static ProjectsPage projects() {
        return new ProjectsPage();
    }

    public static ProjectPage project() {
        return new ProjectPage();
    }

    public static ProjectModal projectModal() {
        return new ProjectModal();
    }

    public static DeleteModal deleteProjectModal() {
        return new DeleteModal();
    }

    // Inbox

    public static InboxPage inbox() {
        return new InboxPage();
    }

    public static QuickAddModal quickAdd() {
        return new QuickAddModal();
    }

    public static DeleteModal deleteTaskModal() {
        return new DeleteModal();
    }
}
