package io.github.vikindor.android.testdata;

import java.util.stream.Stream;

public class TaskNameTestData {

    public static Stream<String> validNames() {
        return Stream.of(
                ".",
                "1",
                "T",
                "テストデータ",
                "Задача",
                GeneratedData.nameOfLength(500)
        );
    }
}
