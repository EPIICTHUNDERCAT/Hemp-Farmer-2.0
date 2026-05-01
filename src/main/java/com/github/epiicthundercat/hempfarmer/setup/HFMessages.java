package com.github.epiicthundercat.hempfarmer.setup;

import net.minecraft.server.level.ServerPlayer;

// TODO (1.21.1): Forge 52 replaced NetworkRegistry.ChannelBuilder / SimpleChannel with
// RegisterPayloadsEvent + IPayloadRegistrar. Implement packets here when needed using:
//   @SubscribeEvent public static void register(RegisterPayloadsEvent event) { ... }
// For now this is a no-op stub since no packets are currently active.
public class HFMessages {

    public static void register() {
        // No packets registered yet
    }

    public static <MSG> void sendToServer(MSG message) {
        // stub — wire up when packets are implemented
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        // stub — wire up when packets are implemented
    }


}
