package main.java;

import java.net.*;
import java.io.*;
import java.util.*;

/*
Notes:
-reads in a string that contains a number, but won't find a match with if statements
-could be Scanner problem involved with the way it deals with Strings i forgot
-
*/

public class Client {

  public static void main (String[] args) {
    String serverName = args[0];
    int port = Integer.parseInt(args[1]);

    try {
      System.out.println("Connecting to " + serverName + " on port " + port + "...");
      Socket client = new Socket(serverName, port);

      System.out.println("Just connected to " + client.getRemoteSocketAddress() + " successfully");
      //sets up output stream to server
      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);

      //writeUTF sends message to the server
      //out.writeUTF("Hello from " + client.getLocalSocketAddress());

      //sets up input stream to receive from server
      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);

      Scanner s = new Scanner(System.in);
      String text;

      //if the client is still running, print stuff out
      //if not, then close it
      while( !(client.isClosed()) ){
        //num = s.nextLine();
        System.out.println("Send a message to server: ");
        text = s.nextLine();
        System.out.println("Sending: " + text);
        //System.out.println(str.length());
        if(text == "stop"){
          //out.writeUTF("0");
          out.writeUTF("0");
          client.close();
          System.out.println("Client pressed 0! Now closing... ");
          break;
        }
        //out.writeUTF(Integer.toString(num));
        in.writeUTF("");
        System.out.println("Client still on? : " + !client.isClosed());
      }


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
