package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> convertToMap(String filepath) {
        Map<String, Object> map = new TreeMap<>();
        Path normalizedFilePath = Paths.get(filepath).toAbsolutePath().normalize();
        String extension = FilenameUtils.getExtension(normalizedFilePath.toString());
        ObjectMapper objectMapper;

        if (extension.equals("json")) {
            objectMapper = new ObjectMapper();
        } else {
            objectMapper = new ObjectMapper(new YAMLFactory());
        }

        try {
            map = objectMapper.readValue(new File(normalizedFilePath.toString()), new TypeReference<>() { });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return map;
    }

    public static void convertToJSONFile(Map<String, Map<String, Object>> map, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(fileName);
            objectMapper.writeValue(file, map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
