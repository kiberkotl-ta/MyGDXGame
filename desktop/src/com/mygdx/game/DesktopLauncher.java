package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.match3.GameWindow;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(960, 720);
//		new Lwjgl3Application(new MyGdxGame(), config);
//		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");

		new Lwjgl3Application(new GameWindow(), config);
		config.setForegroundFPS(60);
	}
}