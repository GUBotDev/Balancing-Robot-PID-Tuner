/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidtuner;

import java.io.IOException;

/**
 *
 * @author Rollie
 */
public class Controller {
    TCPClient client;
    
    Controller(String ip, int port) throws IOException{
        System.out.println("Waiting for pi connection...");
        client = new TCPClient(ip, port);
        System.out.println("Connected to " + ip + ":" + port);
    }
    
    public void writePID(String p, String i, String pS, String iS, String angleOffset) throws IOException{
        if(p != "" && i != "" && pS != "" && iS != ""){
            client.writeToClient(p + "," + i + "," + pS + "," + iS + "," + angleOffset + "\n");
        }
        else{
            System.out.println("Bad settings, did not send.");
        }
    }
}
