package main.java;
import java.net.*;
import java.io.*;

/*
Notes:
-reads in a string that contains a number, but won't find a match with if statements
-
*/

public class Server extends Thread {
  private ServerSocket serverSocket;

  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(100000);
  }

  public void run() {

    try {
      System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
      //waits until a connection to server socket is made from a client
      Socket server = serverSocket.accept();

      System.out.println("A client has connected to the server: " + server.getRemoteSocketAddress());
      DataInputStream in = new DataInputStream(server.getInputStream());

      DataOutputStream out = new DataOutputStream(server.getOutputStream());

      while( !(server.isClosed()) ){
        System.out.println("Awaiting response from client...");
        //awaits til it receives the number and takes it

        //int n = Integer.parseInt(in.readUTF());
        //System.out.println("number " + n + " was received!");
        String n = in.readUTF();

        System.out.println("Client said: " + n);
        // if(n == 0){
        //   System.out.println("Client is closing...");
        //   break;
        // }
        if(n == "stop"){
          System.out.println("Client is closing...");
          break;
        }
      }
      server.close();
      System.out.println("Client has d/ced! Server ending...");
      System.out.println("Server ended");
      //out.writeUTF("You are connected to the server: " + server.getLocalSocketAddress() + "\nGoodbye!");

    } catch (SocketTimeoutException s) {
      System.out.println("Socket timed out!");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String [] args) {
    int port = Integer.parseInt(args[0]);
    try {
      Thread t = new Server(port);
      t.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
