package io.github.vikindor.android.testdata;

import java.util.stream.Stream;

public class ProjectNameTestData {

    public static Stream<String> validNames() {
        return Stream.of(
                ".",
                "1",
                "P",
                "テストデータ",
                "Проект",
                GeneratedData.nameOfLength(120)
        );
    }
}
