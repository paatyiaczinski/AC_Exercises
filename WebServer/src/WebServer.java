import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class WebServer {
    private ServerSocket serverSocket;

    public void ServeConnect() {
        try {
        serverSocket = new ServerSocket(5030);
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public Socket init(){

        Socket clientSocket;
        try {
            return clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

