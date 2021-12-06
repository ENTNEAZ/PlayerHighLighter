package xyz.nacldragron.playerhighlighter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class PlayerHighLighter implements ModInitializer {
	@Override
	public void onInitialize() {
		KeyBinding Lighter =  KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Lighter", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_COMMA, // The keycode of the key
				"PlayerHighLighter" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (Lighter.wasPressed()) {
				if(Properties.isLighting){
					client.player.sendMessage(new LiteralText("PlayerHighLighter:OFF"), true);
					Properties.isLighting = false;
				} else {
					client.player.sendMessage(new LiteralText("PlayerHighLighter:ON"), true);
					Properties.isLighting = true;
				}

			}
		});
	}
}
