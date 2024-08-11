package me.peiskos.onlybread;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Onlybread implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    Logger LOGGER = LoggerFactory.getLogger("OnlyBread");
    @Override
    public void onInitialize() {
        LOGGER.info("OnlyBread was Initialized! I actually do nothing here since I'm just mixins lol");
    }

    public static void punishPlayer(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity){
            MinecraftServer server = player.getServer();
            if (server != null) {
                String playerName = player.getName().getString();
                server.getPlayerManager().broadcast(Text.literal(playerName + " has eaten something NOT BREAD. SHUN THEM!!!").styled(
                        style -> style.withBold(true).withFormatting(Formatting.DARK_RED)
                ),false);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER,200,1000));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,1200,1000));

            }
        }
    }
}
