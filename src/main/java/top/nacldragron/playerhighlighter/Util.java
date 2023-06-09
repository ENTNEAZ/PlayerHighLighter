package top.nacldragron.playerhighlighter;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class Util {
    public static String getUsername(){
        // get username
        if(Util.getPlayer() != null)
        {
            return Util.getPlayer().getName().getString();
        } else {
            return "";
        }
    }

    public static ClientPlayerEntity getPlayer(){
        // get player
        return MinecraftClient.getInstance().player;
    }

    public static boolean checkValidAskingPosition(String s){
        //to see if a chat is calling me?
        //e.g. !!Player or !! Player
        //the latter one is for tab complete
        return s.contains("!! " + Util.getUsername())
                || s.contains("!!" + Util.getUsername())
                || s.contains("!! all")
                || s.contains("!!all");
    }

    public static String getPositionString(){
        //return the position
        //e.g. [0,0,0] @ minecraft:overworld
        int x = (int) Util.getPlayer().getX();
        int y = (int) Util.getPlayer().getY();
        int z = (int) Util.getPlayer().getZ();
        String dimension = Util.getPlayer().getEntityWorld().getRegistryKey().getValue().toString();
        return "[" + x + "," + y + "," + z + "] @ " + dimension;
    }

    public static boolean checkValidAtMe(String s){
        //to see if a chat is @ me?
        //e.g. @Player or @ Player
        //the latter one is convenient for tab complete
        return s.contains("@ " + Util.getUsername())
                || s.contains("@" + Util.getUsername())
                || s.contains("@all")
                || s.contains("@ all");
    }
}
