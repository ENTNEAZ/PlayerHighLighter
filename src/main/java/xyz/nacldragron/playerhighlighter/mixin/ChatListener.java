package xyz.nacldragron.playerhighlighter.mixin;


import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xyz.nacldragron.playerhighlighter.Util;
import xyz.nacldragron.playerhighlighter.Properties;

@Mixin(ChatHud.class)
public class ChatListener {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;I)V", at = @At(value = "HEAD"), cancellable = false)
    public void checkChat(Text message, int messageId, CallbackInfo ci) {
        //check if the chat is asking my position
        if (Properties.player != null && Util.checkValidAskingPosition(message.getString())) {
            Properties.player.sendChatMessage(Util.getPositionString());
        }

        //check if someone is @ me
        if (Properties.player != null && Util.checkValidAtMe(message.getString())) {
            // show a small title in client
            Properties.player.sendMessage(Text.of("§e§lSomeone is @ you!"), true);
        }
    }
}
