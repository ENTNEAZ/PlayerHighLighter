package top.nacldragon.playerhighlighter.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import top.nacldragon.playerhighlighter.Properties;
import top.nacldragon.playerhighlighter.Util;

@Mixin(ChatHud.class)
public class ChatListener {
    @Inject(method = "logChatMessage", at = @At(value = "HEAD"))
    public void checkChat(ChatHudLine indicator, CallbackInfo ci) {
        if(!Properties.isRespondingPosition() || Util.getPlayer() == null){
            return;
        }

        //check if the chat is asking my position
        String messageStr = indicator.content().getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
        if(Util.checkValidAskingPosition(messageStr)){
            String s = Util.getPositionString();
            Util.getPlayer().networkHandler.sendChatMessage(s);
        }

        //check if someone is @ me
        if(Util.checkValidAtMe(messageStr)){
            // show a small title in client
            Util.getPlayer().sendMessage(Text.of(Util.getUsername() + " §e§lSomeone is @ you!"), true);
        }

    }
}
