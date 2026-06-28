package com.example.captchamod;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(modid = "captchamod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CaptchaScreenHandler {

    private static final Pattern CAPTCHA_PATTERN = Pattern.compile("(?i)wpisz.*?/captcha\\s+([a-zA-Z0-9]+)");
    
    private static String lastCaptchaCode = "";
    private static long lastCaptchaTime = 0;
    private static final long COOLDOWN_MS = 1000;

    public static void processCaptchaCode(String captchaCode) {
        if (!CaptchaConfig.isEnabled()) {
            return;
        }
        
        long currentTime = System.currentTimeMillis();
        
        if (captchaCode.equals(lastCaptchaCode) && 
            currentTime - lastCaptchaTime < COOLDOWN_MS) {
            return;
        }
        
        lastCaptchaCode = captchaCode;
        lastCaptchaTime = currentTime;
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            String command = "/captcha " + captchaCode;
            mc.player.chat(command);
        }
    }
}
