package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileComparatorTest {

    @Test
    void compareTwoFilesWithChangedContentsTest() {
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
        String actualJson = Formatter.format(
                "src/test/resources/test-file-1.1.json",
                "src/test/resources/test-file-1.2.json",
                "stylish"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-1.1.yml",
                "src/test/resources/test-file-1.2.yml",
                "stylish"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateTwoEmptyTest() {
        String expected = """
                {
                }""";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-2.1.json",
                "src/test/resources/test-file-2.2.json",
                "stylish"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-2.1.yml",
                "src/test/resources/test-file-2.2.yml",
                "stylish"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateAllDeletedTest() {
        String expected = """
                {
                  - age: 35
                  - name: Kate
                  - sex: female
                  - status:\s
                  - surname: Smith
                  - title: Ms
                }""";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-3.1.json",
                "src/test/resources/test-file-3.2.json",
                "stylish"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-3.1.yml",
                "src/test/resources/test-file-3.2.yml",
                "stylish"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateAllAddedTest() {
        String expected = """
                {
                  + age: 42
                  + name: John
                  + sex: male
                  + status: subscriber
                  + surname: Smith
                }""";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-4.1.json",
                "src/test/resources/test-file-4.2.json",
                "stylish"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-4.1.yml",
                "src/test/resources/test-file-4.2.yml",
                "stylish"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateNestedObjectsTest() {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-5.1.json",
                "src/test/resources/test-file-5.2.json",
                "stylish"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-5.1.yml",
                "src/test/resources/test-file-5.2.yml",
                "stylish"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generatePlainStyleTest() {
        String expected = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-5.1.json",
                "src/test/resources/test-file-5.2.json",
                "plain"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-5.1.yml",
                "src/test/resources/test-file-5.2.yml",
                "plain"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generatePlainStyleEmptyTest() {
        String expected = "";
        String actualJson = Formatter.format(
                "src/test/resources/test-file-2.1.json",
                "src/test/resources/test-file-2.2.json",
                "plain"
        );
        String actualYaml = Formatter.format(
                "src/test/resources/test-file-2.1.yml",
                "src/test/resources/test-file-2.2.yml",
                "plain"
        );

        assertEquals(expected, actualJson);
        assertEquals(expected, actualYaml);
    }

    @Test
    void generateJSONStyleTest() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> expectedMap = new TreeMap<>();

        try {
            expectedMap = mapper.readValue(new File("./src/test/resources/test-file-6.1.json"), TreeMap.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JSONObject expectedJSON = new JSONObject(expectedMap);

        String actualStr = Formatter.format(
                "src/test/resources/test-file-5.1.json",
                "src/test/resources/test-file-5.2.json",
                "json"
        );
        assertEquals(expectedJSON.toString(), actualStr);
    }
}
