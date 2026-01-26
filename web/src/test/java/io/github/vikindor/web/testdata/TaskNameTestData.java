package io.github.vikindor.web.testdata;

import java.util.stream.Stream;

public class TaskNameTestData {

    public static Stream<String> validNames() {
        return Stream.of(
                ".",
                "1",
                "X",
                "テストデータ",
                "Имя",
                "!@#$%^&*()_+-={}[]", // ASCII
                "№✓★", // Unicode
                GeneratedData.nameOfLength(500)
        );
    }
}
