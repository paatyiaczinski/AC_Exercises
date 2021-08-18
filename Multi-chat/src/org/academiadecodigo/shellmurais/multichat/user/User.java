package org.academiadecodigo.shellmurais.multichat.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class User implements Runnable{
    private Socket userSocket;
    private String name;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader console;

    public User () {
        createSocket(9000);
        console= new BufferedReader(new InputStreamReader(System.in));
        name = setName();
        out.println("/create:" + name + " is now in this room");
    }


    public String getName() {
        return name;
    }
    private void createSocket() {
    }
    public String setName() {
        BufferedReader nameGetter = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Name: ");

        try {
            return name = nameGetter.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public int getPort(){
        BufferedReader getPort = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Port: ");

        String port = "";
        try {
            port = getPort.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(port);
    }

    public boolean isBound(){
        return userSocket.isBound();
    }

    @Override
    public void run() {
        while (true){
            sendInfo();
        }
    }

    private void sendInfo() {
        try {
            String message = console.readLine();

            String[] splitMessage = message.split(" ");

            switch (splitMessage[0]) {
                case "/quit":
                    out.println("/quit:" + name + " left this chat");
                    System.exit(0);
                    break;

                case "/name":
                    out.println("/name:" + name + " changed name to :" + setName());
                    break;

                case "/crtGroup":
                    createGroup();
                    //createSocket();
                    break;

                case "/chgGroup":
                    changeGroup();
                    break;

                case "/help":
                    System.out.println("/quit         leave chat\n" +
                            "/name         change name\n" +
                            "/crtGroup     create a new group\n" +
                            "/chgGroup     change group\n" +
                            "/gen message  send message to all participants\n" +
                            "/whisper      send private message");
                    break;


                case "/gen":
                    generalSend(message);
                    break;

                case "/whisper":
                    whisper();
                    break;

                case "/whois":
                    out.println("/whois:");
                    break;

                case "/groups":
                    out.println("/groups:");
                    break;

                default:
                    out.println(name + ": " + message);
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void whisper() {
        BufferedReader privateMessage = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please write according to the example: \n" +
                "personName:message");
        try {
            out.println("/whisper:" + privateMessage.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeGroup() {
        BufferedReader roomName = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the name of group:");
        String newRoom = "";
        try {
            newRoom = roomName.readLine();
            out.println("/chgGroup:" + newRoom);
            System.out.println("You moved to group " + newRoom);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createGroup() {
        BufferedReader room = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the name of the new group:");
        String roomName = null;
        try {
            roomName = room.readLine();
            out.println("/crtGroup:" + roomName);
            System.out.println("You created a new group, " + roomName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveInfo() {
        try {
            String serverMessage = in.readLine();

            if (serverMessage == null) {
                System.out.println("Connection closed.");
                System.exit(0);
            }

            System.out.println(serverMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createSocket(int port) {
        try {
            userSocket = new Socket(InetAddress.getLocalHost(), port);
            in = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            out = new PrintWriter(userSocket.getOutputStream(), true);

            if (name != null) {
                out.println(name + ": is now in this room");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generalSend(String message) {
        out.println("/gen:" + message.substring(message.indexOf(" ") + 1));
    }

}
