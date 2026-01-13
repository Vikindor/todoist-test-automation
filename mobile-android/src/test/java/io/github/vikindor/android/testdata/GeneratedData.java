package io.github.vikindor.android.testdata;

import net.datafaker.Faker;

public class GeneratedData {

    private static final Faker faker = new Faker();

    public static String nameOfLength(int length) {
        return faker.lorem().characters(length);
    }

    public static String invalidEmail() {
        return nameOfLength(6) + "@domain";
    }

    public static String invalidPassword() {
        return faker.number().digits(8);
    }
}
