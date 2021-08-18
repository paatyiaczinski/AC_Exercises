package org.academiadecodigo.shellmurais.multichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiverServer implements Runnable{
    private Socket userSocket;
    private String userMessage;
    private String name;
    private Server server;
    private BufferedReader in;
    private PrintWriter out;
    private Room room;


    public ReceiverServer (Socket userSocket, Server server, Room room) {
        this.userSocket = userSocket;
        this.userMessage = "";
        this.server = server;
        this.room = room;
        try {
            in = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            out = new PrintWriter(userSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                userMessage = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] message = userMessage.split(":");
            String splitMessage = userMessage.substring(userMessage.indexOf(":") + 1);

            if (userMessage == null) {

                System.out.println("Client connection is closed.");

                server.remove(this);
                try {
                    userSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            switch (message[0]) {

                case "/create":

                    name = message[1];
                    System.out.println(splitMessage);
                    server.broadCast(this, splitMessage);
                    break;

                case "/quit":
                    server.broadCast(this, splitMessage);
                    break;

                case "/whisper":

                    System.out.println(userMessage);
                    System.out.println(message[1]);
                    server.whisper(this, message[1], splitMessage.substring(splitMessage.indexOf(":") + 1));
                    break;

                case "/name":

                    name = message[2];
                    System.out.println(userMessage);
                    server.broadCast(this, splitMessage);
                    break;

                case "/crtGroup":
                    System.out.println(userMessage);
                    server.broadCast(this, this.name + " created a new group " + message[1]);
                    server.createRoom(new Room(message[1]));
                    break;

                case "/chgGroup":
                    System.out.println(userMessage);
                    Room newRoom = server.getRoom(message[1]);
                    if (newRoom != null) {
                        room = newRoom;
                        break;
                    } else {
                        out.println("Group not found");
                        break;
                    }

                case "/gen":
                    System.out.println(userMessage);
                    server.broadCast(this, name+ ": " + splitMessage);
                    break;

                case "/groups":
                    System.out.println(server.getGroups());
                    out.println(server.getGroups());
                    break;

                case "/whois":
                    System.out.println(server.getMembers(room));
                    out.println(server.getMembers(room));
                    break;

                default:
                    if (room.getName().equals("general")) {
                        System.out.println(userMessage);
                        server.broadCast(this, userMessage);
                        break;
                    }
                    System.out.println(userMessage);
                    server.sendToGroup(room, this, userMessage);
                    break;
            }
        }
    }


    public Socket getClientSocket() {
        return userSocket;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

}
