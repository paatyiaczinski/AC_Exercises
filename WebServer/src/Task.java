import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Task implements Runnable {
    private Socket clientSocket;

    public Task(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader brose = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();

            String request = brose.readLine();


            System.out.println(request);

            String[] splitMessage = request.split(" ");
            System.out.println(splitMessage[1]);

            String response;
            String path;

        switch (splitMessage[1]) {
            case "/batata":
                path = "/Users/codecadet/Desktop/WebServer/batata/batata3.jpeg";
                response = "HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: image/jpeg \r\n" +
                        "Content-Length: " + fileSize(path) + " \r\n" +
                        "\r\n";
                break;


            case "/background.jpg":
                path = "/Users/codecadet/Desktop/WebServer/batata/background.jpg";
                response = "HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: image/jpeg \r\n" +
                        "Content-Length: " + fileSize(path) + " \r\n" +
                        "\r\n";

               break;

            default:
                path = "/Users/codecadet/Desktop/WebServer/batata/FileNotFound.html";

                response = "HTTP/1.0 404 Not Found\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length:" + fileSize(path) + "\r\n" +
                        "\r\n";
                break;


        }
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.write(getFile(path));

            System.out.println(response);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] getFile(String path) throws IOException {
        File file = new File(path);

        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
        }
    private Long fileSize(String path){
        File file = new File(path);

        return file.length();
        }

}

