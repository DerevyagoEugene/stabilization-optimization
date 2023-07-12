package utility;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static datareadmain.DataRead.*;

public class LogsUtility {

    public static List<String> getLogsOfTest() throws IOException {
        return Files.readAllLines(Paths.get(pathsDTO.pathToLogFile));
    }
}
