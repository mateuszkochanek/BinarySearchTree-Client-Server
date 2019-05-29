package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import binarytree.BinaryTree;

public class ServerMain 
{
    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = "";
  
    ServerMain() { 
      try {
        server = new ServerSocket(4444); 
      } 
      catch (IOException e) {
        System.out.println("Could not listen on port 4444"); System.exit(-1);
      }
    }
  
    public void listenSocket() {
      try {
        client = server.accept();
      } 
      catch (IOException e) {
        System.out.println("Accept failed: 4444"); System.exit(-1);
      }
      try {
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
      } 
      catch (IOException e) {
        System.out.println("Accept failed: 4444"); System.exit(-1);
      }
      while(line != null) {
        try {
          line = in.readLine();
          System.out.println(line);
          out.println("-> ("+line+")");
        } 
        catch (IOException e) {
          System.out.println("Read failed"); System.exit(-1);
        } 
      }
    }
  
    protected void finalize() {
      try {
        in.close();
        out.close();
        client.close();
        server.close();
      } 
      catch (IOException e) {
        System.out.println("Could not close."); System.exit(-1);
      }
    }
  
    public static void main(String[] args) {
    ServerMain server = new ServerMain();
      server.listenSocket();
    }
    
}
