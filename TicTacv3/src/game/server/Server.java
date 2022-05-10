package game.server;

import game.Program;
import game.client.ReaderThread;
import game.client.WriterThread;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	private final int port;


	public Server(int port)
	{
		this.port = port;
	}

	public void start()
	{

		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			System.out.println("Listening for clients on port " + port);

			//if user count get more than 2 handle that failcase scenario
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
