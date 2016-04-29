/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sya
 */
public class MessageListener extends Thread{
    ServerSocket server;
    int port = 8877;
    PongGame gui;
    //int count = 0;
    //HashMap hm_address_player = new HashMap<String,Integer>();
    
    public MessageListener(PongGame gui,int port) throws IOException { 
        this.port = port ;
        this.gui = gui;
        server = new ServerSocket(port);
    }
    
    public MessageListener() throws IOException { 
        server = new ServerSocket(port);
    }

    @Override
    public void run() {
    	System.out.println("tum");
        Socket clientsocket;
        String address;
        //String port;
        try {
            while(true) {
                clientsocket = server.accept();
                address = clientsocket.getInetAddress().getHostAddress();
                // if (!(hm_address_player.containsKey(address))) { 
                //     count = count + 1;
                //     hm_address_player.put(address, count);
                // }
                InputStream is = clientsocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                if(line != null) {
                	System.out.println(line);
                    gui.modify(line);
                }           
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
