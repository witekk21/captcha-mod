package com.example.captchamod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class CaptchaGuiScreen extends Screen {
    
    private Button toggleButton;
    private Button backButton;
    private int buttonWidth = 150;
    private int buttonHeight = 40;
    
    public CaptchaGuiScreen() {
        super(Component.literal("Auto Captcha Settings"));
    }
    
    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        String toggleText = CaptchaConfig.isEnabled() ? "WYLACZ" : "WLACZ";
        
        this.toggleButton = Button.builder(
                Component.literal(toggleText),
                button -> toggleCaptcha()
        ).bounds(centerX - buttonWidth / 2, centerY - 40, buttonWidth, buttonHeight).build();
        
        this.backButton = Button.builder(
                Component.literal("Zamknij"),
                button -> this.onClose()
        ).bounds(centerX - buttonWidth / 2, centerY + 20, buttonWidth, buttonHeight).build();
        
        this.addRenderableWidget(this.toggleButton);
        this.addRenderableWidget(this.backButton);
    }
    
    private void toggleCaptcha() {
        CaptchaConfig.toggleEnabled();
        this.init();
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(guiGraphics, pMouseX, pMouseY, pPartialTick);
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        guiGraphics.drawCenteredString(this.font, Component.literal("Auto Captcha Solver"), 
                centerX, centerY - 100, 0xFFFFFF);
        
        String status = CaptchaConfig.isEnabled() ? "Status: WLACZONY" : "Status: WYLACZONY";
        int statusColor = CaptchaConfig.isEnabled() ? 0x00FF00 : 0xFF0000;
        guiGraphics.drawCenteredString(this.font, Component.literal(status), 
                centerX, centerY - 60, statusColor);
        
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }
    
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
