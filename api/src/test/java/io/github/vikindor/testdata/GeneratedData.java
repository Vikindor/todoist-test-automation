package io.github.vikindor.testdata;

import net.datafaker.Faker;

public class GeneratedData {

    private static final Faker faker = new Faker();

    public static String nameOfLength(int length) {
        return faker.lorem().characters(length);
    }
}
