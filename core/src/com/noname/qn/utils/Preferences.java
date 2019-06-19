package com.noname.qn.utils;

public class Preferences {
    private Preferences(){}

    private static boolean enableEffects = true;
    private static boolean enableMusic = true;

    public static boolean isEnableEffects() {
        return enableEffects;
    }
    public static void setEnableEffects(boolean enableEffects) {
        Preferences.enableEffects = enableEffects;
    }
    public static boolean isEnableMusic() {
        return enableMusic;
    }
    public static void setEnableMusic(boolean enableMusic) {
        Preferences.enableMusic = enableMusic;
    }
}
