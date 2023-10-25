package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import hexlet.code.exception.ExtensionNotSupportedException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> extractMap(String filepath) throws ExtensionNotSupportedException {
        Map<String, Object> map = new TreeMap<>();
        String normalizedPathString = Paths.get(filepath).toAbsolutePath().normalize().toString();
        String extension = FilenameUtils.getExtension(normalizedPathString);
        ObjectMapper objectMapper;

        if (extension.equals("json")) {
            objectMapper = new ObjectMapper();
        } else if (extension.equals("yml") || extension.equals("yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new ExtensionNotSupportedException();
        }

        try {
            map = objectMapper.readValue(new File(normalizedPathString), new TypeReference<>() { });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return map;
    }

    public static void saveAsJSON(String json, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
