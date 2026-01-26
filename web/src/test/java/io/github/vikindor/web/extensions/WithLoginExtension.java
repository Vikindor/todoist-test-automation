package io.github.vikindor.web.extensions;

import io.github.vikindor.web.configs.ProjectConfig;
import io.github.vikindor.web.context.CurrentUser;
import io.github.vikindor.web.helpers.AuthHelper;
import io.github.vikindor.web.utils.AllureAttach;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class WithLoginExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        boolean withLogin =
                context.getTestMethod()
                       .map(method -> method.isAnnotationPresent(WithLogin.class))
                       .orElse(false)
                        || context.getTestClass()
                                  .map(clazz -> clazz.isAnnotationPresent(WithLogin.class))
                                  .orElse(false);

        if (!withLogin) {
            return;
        }

        int accountsCount = config.todoistAccountsCount();
        long threadId = Thread.currentThread().getId();
        int accountIndex = (int) (threadId % accountsCount);
        String email = config.todoistEmail(accountIndex);

        AllureAttach.userSession(accountIndex, email);

        CurrentUser.setAccountIndex(accountIndex);

        AuthHelper.apiLoginAndStabilize(accountIndex);
    }

    @Override
    public void afterTestExecution(@NonNull ExtensionContext context) {
        CurrentUser.clearAccountIndex();
    }
}
