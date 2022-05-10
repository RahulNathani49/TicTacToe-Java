package game.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String host;
    private int port;

    public Client(String host, int port){
        validateHost(host);
        this.host = host;
        this.port = port;

    }

    private void validateHost(String host){
        if(host == null){
            throw new IllegalArgumentException("Host can not be Null");
        }
    }

    public void start(int index){
        try {

            Socket socket = new Socket(host,port);

            WriterThread writerThread = new WriterThread(socket);
            writerThread.start();
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(index);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
