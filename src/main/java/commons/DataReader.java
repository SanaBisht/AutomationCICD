package commons;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String content to hashmap
        ObjectMapper objectMapper = new ObjectMapper();

        List<HashMap<String, String>> hashMapList = objectMapper.readValue(
                jsonContent, new TypeReference<List<HashMap<String, String>>>() {}
        );

        return hashMapList;

    }
}
