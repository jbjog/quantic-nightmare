package com.noname.qn.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.noname.qn.QNGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Quantic NightMare";
		config.width = 721;
		config.height = 386;
		new LwjglApplication(new QNGame(), config);
	}
}
