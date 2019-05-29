package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SocketClient {
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	int option;
	String value;
	Scanner input = new Scanner(System.in);

	public SocketClient(int type) {
		listenSocket();
		out.println(type);
		try {
			System.out.print(in.readLine());
		} catch (IOException ex) {
			System.out.print("nie pyklo");
		}
		printInstructions();
		sendInstructions();

	}

	private void printInstructions() {
		System.out.println("\nInstrukcje jakie mozesz wykonać na drzewie to: (wybierz 1, 2, 3 lub 4)");
		System.out.println("1.Dodaj\n2.Usun\n3.Narysuj\n4.Koniec");
	}

	private void sendInstructions() {
		do{
			try {
                option = input.nextInt();
                if (option == 1 || option == 2) {
					out.println(option);
					value = input.nextLine();
					out.println(value);
				} else if(option == 3 || option == 4) {
					out.println(option);
				} else {
					System.out.println("Proszę wpisać 1,2,3 lub 4");
				}
            } catch (InputMismatchException e) {
                System.out.println("zły typ danych, proszę wpisać 1,2,3 lub 4");
                input.next();
            }

		}while(option != 4);
	}

	public void listenSocket() {
		try {
			socket = new Socket("localhost", 4444);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: localhost");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Brak I/O");
			System.exit(1);
		}
	}
}