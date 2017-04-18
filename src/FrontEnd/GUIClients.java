package FrontEnd;

import java.util.HashMap;

/**
 * Created by Anthony Perez on 4/4/17.
 */
public class GUIClients {
    private static HashMap<Number, GUIClient> clientHash = new HashMap<Number, GUIClient>();
    private static GUIClient activeClient;

    public GUIClients(){
        clientHash = new HashMap<>();
    }

    public Boolean connectClient(GUIClient gui){
        clientHash.put(gui.returnID(), gui);
        System.out.println(clientHash);

        if(clientHash.containsKey(gui.clientGuiID)){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean disconnectClient(GUIClient gui){
        clientHash.remove(gui.returnID());
        System.out.println(clientHash);

        if(!clientHash.containsKey(gui.clientGuiID)){
            return false;
        }
        else{
            return true;
        }
    }
    public HashMap<Number, GUIClient> returnHash(){
        return clientHash;
    }
    public static void setActiveClient(GUIClient client){
        activeClient = client;
    }
    public static GUIClient getActiveClient(){
        return activeClient;
    }
    public static HashMap<Number, GUIClient> getClientHash(){
        return clientHash;
    }
}
