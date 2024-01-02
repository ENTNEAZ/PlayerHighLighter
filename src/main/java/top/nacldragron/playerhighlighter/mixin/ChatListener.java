package top.nacldragron.playerhighlighter.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import top.nacldragron.playerhighlighter.Properties;
import top.nacldragron.playerhighlighter.Util;

@Mixin(ChatHud.class)
public class ChatListener {
    @Inject(method = "logChatMessage", at = @At(value = "HEAD"))
    public void checkChat(Text message, MessageIndicator indicator, CallbackInfo ci) {
        //check if the chat is asking my position
        String messageStr = message.getString().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n");
        if (Properties.isRespondingPosition() && Util.getPlayer() != null && Util.checkValidAskingPosition(messageStr)) {
            String s = Util.getPositionString();
            Util.getPlayer().networkHandler.sendChatMessage(s);
        }

        //check if someone is @ me
        if (Properties.isRespondingAtMe() && Util.getPlayer() != null && Util.checkValidAtMe(messageStr)) {
            // show a small title in client
            Util.getPlayer().sendMessage(Text.of("§e§lSomeone is @ you!"), true);
        }
    }
}
