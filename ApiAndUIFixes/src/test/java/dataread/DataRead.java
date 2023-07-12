package dataread;

import dataread.models.*;
import utility.JsonReadUtility;

import java.io.IOException;

public class DataRead {
    public static ConfigDataApiDTO configDataApiDTO;

    static {
        try {
            configDataApiDTO = new JsonReadUtility().jsonToPojo(ConfigDataApiDTO.class, "ConfigApi.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestDataApiDTO testDataApiDTO;

    static {
        try {
            testDataApiDTO = new JsonReadUtility().jsonToPojo(TestDataApiDTO.class, "TetsDataApi.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigDataUiDTO configDataUiDTO;

    static {
        try {
            configDataUiDTO = new JsonReadUtility().jsonToPojo(ConfigDataUiDTO.class, "ConfigUI.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}