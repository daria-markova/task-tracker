package com.daria.tasktracker.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class FileStorage {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("tasks.json");
}
