import java.nio.file.*;
import java.util.List;

/**
 * Created by Aaron on 28/06/2017.
 */
public class WatcherTest {
    public static void main(String[] args) {
        Path this_dir = Paths.get("/Users/Aaron/Documents/facishare/code/gitfirstshare/opensourcestudy/iotest/src/main/java");
        System.out.println("Now watching the current directory ...");

        try {
            WatchService watcher = this_dir.getFileSystem().newWatchService();
            this_dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watckKey = watcher.take();
            List<WatchEvent<?>> events = watckKey.pollEvents();
            for (WatchEvent event : events) {
                System.out.println("test");
                System.out.println(event.kind()+"\t" + event.context().toString() + "'.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
