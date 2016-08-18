/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidtuner;

import java.io.*;
import java.net.*;

/**
 *
 * @author Rollie
 */
public class TCPClient {
    Socket client;
    BufferedReader reader;
    DataOutputStream writer;
    
    TCPClient(String ip, int port) throws IOException{
            client = new Socket(ip, port);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new DataOutputStream(client.getOutputStream());
    }
    
    public void writeToClient(String output) throws IOException{
        writer.writeBytes(output);
    }
    
    public String readFromClient() throws IOException{
        return reader.readLine();
    }
}
