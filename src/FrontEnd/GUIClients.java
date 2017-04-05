package FrontEnd;

import java.util.HashMap;

/**
 * Created by Jp on 4/4/17.
 */
public class GUIClients {
    HashMap<Integer,ClientGUI> clientHash = new HashMap<Integer,ClientGUI>();

    public GUIClients(){
        clientHash = new HashMap<>();
    }

    public Boolean connectClient(ClientGUI gui){
        clientHash.put(gui.returnID(), gui);
        System.out.println(clientHash);

        if(clientHash.containsKey(gui.clientGuiID)){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean disconnectClient(ClientGUI gui){
        clientHash.remove(gui.returnID());
        System.out.println(clientHash);

        if(!clientHash.containsKey(gui.clientGuiID)){
            return false;
        }
        else{
            return true;
        }
    }
}
