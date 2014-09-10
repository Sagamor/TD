package com.sagamor.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import game.Config;
import screens.CalibrateScreen;
import screens.GameScreen;

public class TowerDefence extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
        Config.skin = new Skin(Gdx.files.internal("skin/skin.json"));
        setScreen(new CalibrateScreen(this));
	}
}
