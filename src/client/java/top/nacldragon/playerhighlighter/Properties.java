package top.nacldragon.playerhighlighter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

public class Properties {
    private static boolean Lighting = true;
    private static boolean RespondingPosition = true;
    private static boolean RespondingAtMe = true;

    public static boolean isLighting() {
        return Lighting;
    }

    public static boolean isRespondingPosition() {
        return RespondingPosition;
    }



    public static boolean isRespondingAtMe() {
        return RespondingAtMe;
    }

    public static void setLighting(boolean lighting) {
        Lighting = lighting;
        writeConfig("isLighting",lighting);
    }

    public static void setRespondingPosition(boolean respondingPosition) {
        RespondingPosition = respondingPosition;
        writeConfig("isRespondingPosition",respondingPosition);
    }

    public static void setRespondingAtMe(boolean respondingAtMe) {
        RespondingAtMe = respondingAtMe;
        writeConfig("isRespondingAtMe",respondingAtMe);
    }

    private static void writeConfig(String key,Boolean value){
        Path dotMinecraftFolder = MinecraftClient.getInstance().runDirectory.toPath();
        Path configFolder = dotMinecraftFolder.resolve("config");
        Path configPath = configFolder.resolve("playerhighlighter.json");
        try {
            JsonReader reader = new JsonReader(new java.io.FileReader(configPath.toString()));
            JsonParser parser = new JsonParser();
            JsonObject config = parser.parse(reader).getAsJsonObject();
            config.addProperty(key,value);
            FileWriter writer = new FileWriter(configPath.toString());
            writer.write(config.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void init(){
        Path dotMinecraftFolder = MinecraftClient.getInstance().runDirectory.toPath();
        Path configFolder = dotMinecraftFolder.resolve("config");
        Path configPath = configFolder.resolve("PlayerHighLighter.json");

        if (configPath.toFile().exists()) {
            // load config
            loadConfig(configPath);
        } else {
            // create config
            createConfig(configPath);
        }
    }

    private static void loadConfig(Path configPath){
        try {
            // read config
            JsonReader reader = new JsonReader(new java.io.FileReader(configPath.toString()));
            JsonParser parser = new JsonParser();
            JsonObject config = parser.parse(reader).getAsJsonObject();

            // load config
            Lighting = config.get("isLighting").getAsBoolean();
            RespondingPosition = config.get("isRespondingPosition").getAsBoolean();
            RespondingAtMe = config.get("isRespondingAtMe").getAsBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            // re-create config
            createConfig(configPath);
        }
    }

    private static void createConfig(Path configPath){
        try {
            // create file
            File configFile = new File(configPath.toString());
            FileWriter writer = new FileWriter(configFile);
            writer.write("{\"isLighting\": true,\"isRespondingPosition\": true,\"isRespondingAtMe\": true}");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
