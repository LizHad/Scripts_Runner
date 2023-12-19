package utils;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmdExecutor {
    public static String executeCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Set the command to be executed in the CMD prompt
        processBuilder.command("cmd.exe", "/c", command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        // Wait for the process to finish
        process.waitFor();

        // Get the output of the CMD command
        InputStream inputStream = process.getInputStream();
        String output = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        return output;
    }

    public static String extractValueByKey(String output, String key) {
        // Create the regex pattern to match the key-value pair
        String regex = key + ":\\s*(.+)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(output);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null; // Key not found in the output
        }
    }
}
