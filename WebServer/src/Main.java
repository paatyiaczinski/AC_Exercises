import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        WebServer server = new WebServer();
        ExecutorService executorService = Executors.newCachedThreadPool();

        while(true){

            Socket connection = server.init();

            executorService.submit(new Task(connection));

        }

    }
}
