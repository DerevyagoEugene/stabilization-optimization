package datareadmain;

import datareadmain.models.PathsDTO;
import datareadmain.models.ScriptsDTO;
import utility.JsonReadUtility;

import java.io.IOException;

public class DataRead {

    public static PathsDTO pathsDTO;

    static {
        try {
            pathsDTO = new JsonReadUtility().jsonToPojo(PathsDTO.class, "Paths.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ScriptsDTO scriptsDTO;

    static {
        try {
            scriptsDTO = new JsonReadUtility().jsonToPojo(ScriptsDTO.class, "Scripts.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
