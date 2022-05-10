package game.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReaderThread extends Thread{
    BufferedReader reader;

    public ReaderThread(Socket socket){
        try {
            reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverMessage;
                serverMessage = reader.readLine();
                if (serverMessage == null) {
                    break;
                }
                System.out .println(serverMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("this is reader");
    }
}
