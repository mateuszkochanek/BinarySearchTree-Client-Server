package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SocketClient {
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	int option;
	String value;
	String line;
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
		System.out.println("1.Dodaj\n2.Usun\n3.Narysuj\n4.Znajdź\n5.Koniec\n");
	}

	private void sendInstructions() {
		do{
			try {
				option = input.nextInt();
				input.nextLine();
                if (option == 1 || option == 2 || option == 4) {
					System.out.println("Podaj wartość:");
					value = input.nextLine();
					out.println(option);
					out.println(value);
					do{
						line = in.readLine();
						System.out.println(line);			
					}while(in.ready());
				} else if(option == 3) {
					out.println(option);
					do{
						line = in.readLine();
						System.out.println(line);			
					}while(in.ready());
				} else if(option == 5){
					out.println(option);
					System.out.println("Program kończy działanie");
				} else {
					System.out.println("Proszę wpisać 1,2,3,4 lub 5");
				}
            } catch (InputMismatchException e) {
                System.out.println("zły typ danych, proszę wpisać 1,2,3,4 lub 5");
                input.next();
            } catch(IOException ex){
				System.out.println("IOexception");
			}

		}while(option != 5);
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