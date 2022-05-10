package game.client;

import game.models.PassValue;

import java.io.*;
import java.net.Socket;
import java.time.Instant;

public class WriterThread extends Thread{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public WriterThread(Socket socket){
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = new BufferedReader(new InputStreamReader(System.in));
//        reader = new
    }

    @Override
    public void run() {
        try {
                writer.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void validateName(String name){
        if(name == null || name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
