package io.github.vikindor.web.context;

public final class CurrentUser {

    private static final ThreadLocal<Integer> ACCOUNT_INDEX = new ThreadLocal<>();

    private CurrentUser() {}

    public static void setAccountIndex(int index) {
        ACCOUNT_INDEX.set(index);
    }

    public static int getAccountIndex() {
        Integer index = ACCOUNT_INDEX.get();
        if (index == null) {
            throw new IllegalStateException("CurrentUser not initialized. Missing WithLoginExtension?");
        }
        return index;
    }

    public static void clearAccountIndex() {
        ACCOUNT_INDEX.remove();
    }
}
