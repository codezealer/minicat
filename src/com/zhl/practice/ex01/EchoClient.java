package com.zhl.practice.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	private final String host = "127.0.0.1";
	private final int port = 8888;
	private Socket socket;
	
	public EchoClient() throws IOException {
		socket = new Socket(host, port);
	}
	
	public static void main(String[] args) throws IOException {
		new EchoClient().talk();
	}

	private void talk() {
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			pw = getWriter(socket);
			br = getReader(socket);
			String msg = "hello,server.";
			pw.println(msg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) pw.close();
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
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
