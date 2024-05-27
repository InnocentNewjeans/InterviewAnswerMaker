package com.ssafy.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Service
public class PythonService {

    private static final Logger logger = Logger.getLogger(PythonService.class.getName());

    public String runPythonScript(String question, String position) {
        try {
            logger.info("Running Python script with question: " + question + " and position: " + position);

            String gitBashPath = "C:/Program Files/Git/bin/bash.exe";

            ProcessBuilder pb = new ProcessBuilder(gitBashPath, "-c", "C:/Users/findu/Desktop/finalpjt/backend/src/bin/bash/run_model.sh \"" + question + "\" \"" + position + "\"");
            pb.redirectErrorStream(true);
            pb.directory(new File("C:/Users/findu/Desktop/finalpjt/backend/src/bin/bash"));

            Process process = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                logger.info(line);
                result.append(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Python script execution failed with exit code " + exitCode);
            }
            logger.info("Python script executed successfully. Output: " + result.toString());
            return result.toString();
        } catch (Exception e) {
            logger.severe("Error running Python script: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
