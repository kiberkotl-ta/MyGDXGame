package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.match3.MatchThree;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		setScreen(new MainMenu(this));
		//setScreen(new MatchThree(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}

	public class TableBug extends ApplicationAdapter {

		public Skin skin;
		Stage stage;
		Table container;


		@Override
		public void create() {
			skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

			container = new Table();
			container.setFillParent(true);


			stage = new Stage();
			stage.addActor(container);

		}

		@Override
		public void render() {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			stage.act();
			stage.draw();

			if (Gdx.input.justTouched()) {
				TextButton newButton = new TextButton("Button ", skin);
				container.row();
				container.add(newButton);
			}
		}
	}
}
