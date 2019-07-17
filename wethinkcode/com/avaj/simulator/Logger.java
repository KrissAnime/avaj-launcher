package wethinkcode.com.avaj.simulator;

import java.io.*;

public class Logger {
    private File logfile = new File("simulation.txt");

    public Logger(String log) {
        try {
            FileWriter logWriter = new FileWriter(logfile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(logWriter);
            bufferedWriter.write(log);
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Log file was not found");
        } catch (IOException e) {
            System.out.println("Unable to write to log file");
            System.exit(2);
        }
    }
}
