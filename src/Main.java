import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

public class Main {

    private final static int REFRESH = 5 * 1000; // in ms

    public static void main(String... args) {

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                Stream<ProcessHandle> processHandleStream = ProcessHandle.allProcesses();

                processHandleStream.forEach(processHandle -> {
                    if (processHandle.info().command().toString().contains("chrome.exe")) {
                        processHandle.destroy();
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, REFRESH);

    }
}
