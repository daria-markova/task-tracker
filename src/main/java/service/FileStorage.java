package service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("tasks.json");
}
