package game.server;

public class Program
{
    public static void main(String[] args)
    {
        Server server = new Server(7777);
        server.start();
    }
}
