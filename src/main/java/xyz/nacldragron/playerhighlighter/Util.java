package xyz.nacldragron.playerhighlighter;

public class Util {
    public static String getUsername(){
        // get username
        if(Properties.player != null)
        {
            return  Properties.player.getName().getString();
        } else {
            return "";
        }
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
        int x = (int) Properties.player.getX();
        int y = (int) Properties.player.getY();
        int z = (int) Properties.player.getZ();
        String dimension = Properties.player.getEntityWorld().getRegistryKey().getValue().toString();
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
