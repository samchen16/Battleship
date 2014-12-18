package com.mygdx.battleship.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.battleship.Battleship;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Battleship.WIDTH;
		config.height = Battleship.HEIGHT;
		new LwjglApplication(new Battleship(), config);
	}
}
