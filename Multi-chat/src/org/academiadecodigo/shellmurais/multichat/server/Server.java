package org.academiadecodigo.shellmurais.multichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port = 9000;
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<ReceiverServer> userList;
    private CopyOnWriteArrayList<Room> rooms;

    public Server(){
        userList = new CopyOnWriteArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rooms = new CopyOnWriteArrayList<>();
        Room general = new Room("general");
        rooms.add(general);
    }

    public int getPort() {
        BufferedReader portGetter = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Port: ");

        String port = "";
        try {
            port = portGetter.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(port);
    }

    public void listen() throws IOException {
        Socket client = serverSocket.accept();

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        ReceiverServer userThread = new ReceiverServer(client, this, rooms.get(0));

        userList.add(userThread);

        cachedPool.submit(userThread);
    }

    public boolean isBound() {
        return serverSocket.isBound();
    }

    public void broadCast(ReceiverServer originServer, String message) {
        PrintWriter out = null;
        for (ReceiverServer user : userList) {

            if (originServer.equals(user)) {
                continue;
            }
            try {
                out = new PrintWriter(user.getClientSocket().getOutputStream(), true);
                out.println(rooms.get(0).getName() + "/ " + message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToGroup(Room room, ReceiverServer originServer, String message) {
        PrintWriter out = null;
        for (ReceiverServer user : userList) {

            if (user.getRoom().equals(room)) {
                if (!originServer.equals(user)) {
                    try {
                        out = new PrintWriter(user.getClientSocket().getOutputStream(), true);
                        out.println(room.getName() + "/ " + message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void whisper(ReceiverServer sender, String name, String message) {
        PrintWriter out = null;
        for (ReceiverServer client : userList) {
            if (!client.getName().equals(name)) {
                continue;
            }
            try {
                out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
                out.println(sender.getRoom().getName() + "/ " + sender.getName() + " whispered to you:" + message);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out = new PrintWriter(sender.getClientSocket().getOutputStream(), true);
            out.println("user not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void remove(ReceiverServer user) {
       userList.remove(user);
    }

    public void createRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public String getGroups(){
        String groups="";
        for (Room room: rooms){
            groups += room.getName() + "\n";
        }
        return groups;
    }

    public String getMembers(Room room){
        String members ="";

        for (ReceiverServer client : userList){
            if(client.getRoom().equals(room)){
                members += client.getName() + "\n";
            }
        }
        return members;
    }


    }
