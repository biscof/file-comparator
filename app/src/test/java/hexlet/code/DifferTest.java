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
        String actualJson = Differ.generate(
                "src/test/resources/test-file-1.1.json",
                "src/test/resources/test-file-1.2.json"
        );
        String actualYaml = Differ.generate(
                "src/test/resources/test-file-1.1.yml",
                "src/test/resources/test-file-1.2.yml"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateTwoEmpty() {
        String expected = """
                {
                }""";
        String actualJson = Differ.generate(
                "src/test/resources/test-file-2.1.json",
                "src/test/resources/test-file-2.2.json"
        );
        String actualYaml = Differ.generate(
                "src/test/resources/test-file-2.1.yml",
                "src/test/resources/test-file-2.2.yml"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
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
        String actualJson = Differ.generate(
                "src/test/resources/test-file-3.1.json",
                "src/test/resources/test-file-3.2.json"
        );
        String actualYaml = Differ.generate(
                "src/test/resources/test-file-3.1.yml",
                "src/test/resources/test-file-3.2.yml"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
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
        String actualJson = Differ.generate(
                "src/test/resources/test-file-4.1.json",
                "src/test/resources/test-file-4.2.json"
        );
        String actualYaml = Differ.generate(
                "src/test/resources/test-file-4.1.yml",
                "src/test/resources/test-file-4.2.yml"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }
}
