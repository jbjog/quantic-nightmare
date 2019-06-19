package com.noname.qn.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.noname.qn.QNGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Quantic NightMare";
		config.width = 721;
		config.height = 386;
		config.addIcon("QN32.png", Files.FileType.Internal);
		new LwjglApplication(new QNGame(), config);
	}
}
