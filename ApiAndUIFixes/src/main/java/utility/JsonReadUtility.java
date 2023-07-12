package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonReadUtility {

    public <T> T jsonToPojo(Class<T> myClassToDeserialize, String fileName) throws IOException {
        File file = new File(this.getClass().getResource("/" + fileName).getFile());

        return new ObjectMapper().readValue(file, myClassToDeserialize);
    }
}
