package example;

import commons.DataReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProviders extends DataReader {

    @DataProvider
    public Object[][] getCorrectLoginCreds(){
        return new Object[][] {
                {"sanakhan@gmail.com","Sana#123456"}
        };
    }

    @DataProvider
    public Object[][] getIncorrectLoginCreds(){
        return new Object[][] {
                {"sana@gmail.com","23456"},
                {"sanakhan@gmail.com","Sana#"}
        };
    }

    @DataProvider
    public Object[][] getCorrectLoginCredsAsMap(){
        Map<String,String> map = new HashMap<>();
        map.put("email","sanakhan@gmail.com");
        map.put("pwd","Sana#123456");
        return new Object[][]{{map}};
    }

    @DataProvider
    public Object[][] getIncorrectLoginCredsAsMap(){
        Map<String,String> map1 = new HashMap<>();
        map1.put("email","sanakhan@gmail.com");
        map1.put("pwd","Sana#123456");

        Map<String,String> map2 = new HashMap<>();
        map2.put("email","sanakhan@gmail.com");
        map2.put("pwd","Sana#123456");

        return new Object[][] {
                {map1},
                {map2}
        };
    }

    @DataProvider
    public Object[][] getIncorrectLoginCredsAsMap2() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/inputFiles/incorrectLoginCreds.json");
        return new Object[][]{{data.get(0)},{data.get(0)}};
    }


}

