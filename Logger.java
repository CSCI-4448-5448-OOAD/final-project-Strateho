import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger implements Observer {
    private static Logger instance;
    private PrintWriter logFile;

    public Logger(int turnNumber) {
        String folderPath = "./logs"; // makes path to where to store the log files
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // create the log folder if it doesn't exist yet
        }

        try {
            String filePath = folderPath + "/Logger-" + turnNumber + ".txt";
            logFile = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance(int turnNumber) {
        if (instance == null) {
            instance = new Logger(turnNumber);
        }
        return instance;
    }

    @Override
    public void update(String s) {  //updates the info into the logger
        logFile.println(s);
    }

    public void closeLogFile() { // closes the file
        if (logFile != null) {
            logFile.close();
        }
    }
}