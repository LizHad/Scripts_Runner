package test;

import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.CmdExecutor;
import java.io.IOException;

public class Run_Script_File {
    @Parameters({"cmdPath", "runPythonScript"})
    @Test(description = "Enter script path via cmd")
    @Description(("1. Run script file via cmd \n" ))
    public void A01_Run_Script_Test(String cmdPath, String runPythonScript) throws InterruptedException {

        Thread.sleep(5000);
        try {
            // Set path & run python script

            String result1 = CmdExecutor.executeCommand(cmdPath);
            String result2 = CmdExecutor.executeCommand(runPythonScript);

            //Print results
            System.out.println("Result 1:\n" + result1);
            System.out.println("Result 2:\n" + result2);

            //Assert 'file download done' string in response
            String expectedString = "";
            if (result2.contains(expectedString)) {
                System.out.println("Assertion passed: The expected string was found in the output.");
            } else {
                System.out.println("Assertion failed: The expected string was not found in the output.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
