package com.example.captchamod;

public class CaptchaConfig {
    public static boolean isEnabled = true;
    
    public static void toggleEnabled() {
        isEnabled = !isEnabled;
    }
    
    public static boolean isEnabled() {
        return isEnabled;
    }
}
