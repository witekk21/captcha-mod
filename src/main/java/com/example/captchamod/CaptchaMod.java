package com.example.captchamod;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CaptchaMod.MODID)
public class CaptchaMod {
    public static final String MODID = "captchamod";

    public CaptchaMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::onClientSetup);
        NeoForge.EVENT_BUS.register(new CaptchaScreenHandler());
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
    }
}
