package io.github.vikindor.web.extensions;

import io.github.vikindor.web.configs.ProjectConfig;
import io.github.vikindor.web.context.CurrentUser;
import io.github.vikindor.web.helpers.AuthHelper;
import io.github.vikindor.web.utils.AllureAttach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class WithLoginExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final ProjectConfig CONFIG = ConfigFactory.create(ProjectConfig.class);

    private static final BlockingQueue<Integer> AVAILABLE_ACCOUNTS = createAccountPool();

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(WithLoginExtension.class);

    private static final String ACCOUNT_INDEX = "accountIndex";

    private static BlockingQueue<Integer> createAccountPool() {
        int accountsCount = CONFIG.todoistAccountsCount();

        BlockingQueue<Integer> accounts = new ArrayBlockingQueue<>(accountsCount);
        IntStream.range(0, accountsCount).forEach(accounts::add);

        return accounts;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        if (!requiresLogin(context)) {
            return;
        }

        int accountIndex = acquireAccount();
        context.getStore(NAMESPACE).put(ACCOUNT_INDEX, accountIndex);

        try {
            String email = CONFIG.todoistEmail(accountIndex);

            CurrentUser.setAccountIndex(accountIndex);
            AllureAttach.userSession(accountIndex, email);
            AuthHelper.apiLoginAndStabilize(accountIndex);
        } catch (RuntimeException | Error exception) {
            releaseAccount(context);
            throw exception;
        }
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        releaseAccount(context);
    }

    private boolean requiresLogin(ExtensionContext context) {
        return context.getTestMethod()
                .map(method -> method.isAnnotationPresent(WithLogin.class))
                .orElse(false)
                || context.getTestClass()
                .map(testClass -> testClass.isAnnotationPresent(WithLogin.class))
                .orElse(false);
    }

    private int acquireAccount() {
        try {
            return AVAILABLE_ACCOUNTS.take();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();

            throw new ExtensionConfigurationException(
                    "Interrupted while waiting for a free Todoist account",
                    exception
            );
        }
    }

    private void releaseAccount(ExtensionContext context) {
        Integer accountIndex = context.getStore(NAMESPACE)
                .remove(ACCOUNT_INDEX, Integer.class);

        if (accountIndex == null) {
            return;
        }

        CurrentUser.clearAccountIndex();

        if (!AVAILABLE_ACCOUNTS.offer(accountIndex)) {
            throw new ExtensionConfigurationException(
                    "Todoist account was returned to the pool more than once"
            );
        }
    }
}
