package com.sagamor.td.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.sagamor.td.TowerDefence;

public class DesktopLauncher {
    public static void main(String[] args) {
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        settings.maxHeight = 2048;
//        settings.maxWidth = 2048;
//        settings.combineSubdirectories = true;
//        TexturePacker.process(settings, "images", "skin", "skin");
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        //configuration.useGL20 = true;
        configuration.title = "Tower Defence";
        configuration.width = 1920;
        configuration.height = 1200;
        //configuration.fullscreen = true;
        new LwjglApplication(new TowerDefence(), configuration);
    }
}
