package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public SocketClient(int type){
      listenSocket();
      printinstructions ();
      out.println(type);
    }
 
    private void printinstructions (){
      System.out.println("Instrukcje jakie mozesz wykonać na drzewie to: (wybierz 1, 2, 3 lub 4)");
      System.out.println("1.Dodaj\n2.Usun\n3.Narysuj\n4.Koniec");
    }

    public void listenSocket(){
        try {
          socket = new Socket("localhost", 4444);
          out = new PrintWriter(socket.getOutputStream(), true);
          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
           System.out.println("Unknown host: localhost"); System.exit(1);
        } catch  (IOException e) {
           System.out.println("Brak I/O"); System.exit(1);
        }
    }
}