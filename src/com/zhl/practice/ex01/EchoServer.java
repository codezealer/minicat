package com.zhl.practice.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private final int port = 8888;
	private ServerSocket serverSocket;
	
	public EchoServer() throws IOException {
		serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
	}
	
	public static void main(String[] args) throws IOException {
		new EchoServer().service();
	}

	private void service() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("new connection accepted.");
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);
			String msg = null;
			while ( (msg=br.readLine()) != null) {
				System.out.println(msg);
				if ("bye".equals(msg)) {
					break;
				}
			}
			
		}
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		return new PrintWriter(socket.getOutputStream());
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	
}
