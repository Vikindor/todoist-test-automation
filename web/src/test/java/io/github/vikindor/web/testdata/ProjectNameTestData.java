package io.github.vikindor.web.testdata;

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
