package xyz.nacldragron.playerhighlighter.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xyz.nacldragron.playerhighlighter.Util;
import xyz.nacldragron.playerhighlighter.Properties;

@Mixin(ChatHud.class)
public class ChatListener {
    @Inject(method = "logChatMessage", at = @At(value = "HEAD"), cancellable = false)
    public void checkChat(Text message, MessageIndicator indicator, CallbackInfo ci) {
        //check if the chat is asking my position
        String messageStr = message.getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
        if (Properties.player != null && Util.checkValidAskingPosition(messageStr)) {
            String s = Util.getPositionString();
            Properties.player.sendChatMessage(s, Text.of(s));
        }

        //check if someone is @ me
        if (Properties.player != null && Util.checkValidAtMe(messageStr)) {
            // show a small title in client
            Properties.player.sendMessage(Text.of("§e§lSomeone is @ you!"), true);
        }
    }
}
