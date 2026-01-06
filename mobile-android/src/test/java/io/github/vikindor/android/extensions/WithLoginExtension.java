package io.github.vikindor.android.extensions;

import io.github.vikindor.android.configs.ConfigProvider;
import io.github.vikindor.android.helpers.AuthHelper;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class WithLoginExtension implements BeforeTestExecutionCallback {

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

        AuthHelper.login(ConfigProvider.config().todoistEmail(), ConfigProvider.config().todoistPassword());
    }
}
