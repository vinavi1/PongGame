/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pack;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageTransmitter extends Thread {

    String message;
    String[] hostnames;
    int[] ports;

    
    public MessageTransmitter() {
    }

    public MessageTransmitter(String message, String[] hostnames, int[] ports) {
        this.message = message;
        this.hostnames = hostnames;
        this.ports = ports;
    }

    @Override
    public void run() {
        try { 
        		for (int i = 0 ; i< hostnames.length ;i++)  { 
        			Socket s = new Socket(InetAddress.getByName(hostnames[i]),ports[i]);
        			s.getOutputStream().write(message.getBytes());
        			s.close();
        		}       
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
