package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void generateTwoFilesWithChangedContents() {
        String expected = """
                {
                 - age: 42
                 + age: 35
                 - name: John
                 + name: Kate
                 - sex: male
                 + sex: female
                 - status: subscriber
                 + status:\s
                   surname: Smith
                 + title: Ms
                }""";
        String actual = Differ.generate(
                "src/test/resources/test-file-1.1.json",
                "src/test/resources/test-file-1.2.json"
        );

        assertEquals(expected, actual);
    }

    @Test
    void generateTwoEmpty() {
        String expected = """
                {
                }""";
        String actual = Differ.generate(
                "src/test/resources/test-file-2.1.json",
                "src/test/resources/test-file-2.2.json"
        );

        assertEquals(expected, actual);
    }

    @Test
    void generateAllDeleted() {
        String expected = """
                {
                 - age: 35
                 - name: Kate
                 - sex: female
                 - status:\s
                 - surname: Smith
                 - title: Ms
                }""";
        String actual = Differ.generate(
                "src/test/resources/test-file-3.1.json",
                "src/test/resources/test-file-3.2.json"
        );

        assertEquals(expected, actual);
    }

    @Test
    void generateAllAdded() {
        String expected = """
                {
                 + age: 42
                 + name: John
                 + sex: male
                 + status: subscriber
                 + surname: Smith
                }""";
        String actual = Differ.generate(
                "src/test/resources/test-file-4.1.json",
                "src/test/resources/test-file-4.2.json"
        );

        assertEquals(expected, actual);
    }
}
