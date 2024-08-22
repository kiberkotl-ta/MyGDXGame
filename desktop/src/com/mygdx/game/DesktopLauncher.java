package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowIcon(Files.FileType.Internal, "red.png");
		config.setTitle("Match Three");
		config.setWindowedMode(960, 720);
		new Lwjgl3Application(new MyGdxGame(), config);

		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");


		config.setForegroundFPS(60);
	}
}