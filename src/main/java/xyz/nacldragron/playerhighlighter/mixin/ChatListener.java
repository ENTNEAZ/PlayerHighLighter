package xyz.nacldragron.playerhighlighter.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xyz.nacldragron.playerhighlighter.Properties;
import xyz.nacldragron.playerhighlighter.Util;

@Mixin(ChatHud.class)
public class ChatListener {
    @Inject(method = "logChatMessage", at = @At(value = "HEAD"))
    public void checkChat(Text message, MessageIndicator indicator, CallbackInfo ci) {
        //check if the chat is asking my position
        String messageStr = message.getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
        if (Properties.isResponsingPosition && Util.getPlayer() != null && Util.checkValidAskingPosition(messageStr)) {
            String s = Util.getPositionString();
            Util.getPlayer().sendChatMessage(s, Text.of(s));
        }

        //check if someone is @ me
        if (Properties.isResponsingAtMe && Util.getPlayer() != null && Util.checkValidAtMe(messageStr)) {
            // show a small title in client
            Util.getPlayer().sendMessage(Text.of("§e§lSomeone is @ you!"), true);
        }
    }
}
