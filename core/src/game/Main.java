package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import screens.CalibrateScreen;

/**
 * Created by Sagamor on 11/03/2014.
 */

public class Main extends Game {

    @Override
    public void create() {
        Config.skin = new Skin(Gdx.files.internal("skin/skin.json"));

//        for(FileHandle file : Gdx.files.internal("effects").list()) {
//            if(file.name().startsWith("."))
//                continue;
//            ParticleEffect effect = new ParticleEffect();
//            effect.load(file, Config.skin.getAtlas());
//            ParticleEffectPool pool = new ParticleEffectPool(effect, 10, 100);
//            Config.effects.put(file.nameWithoutExtension(), pool);
//        }

        setScreen(new CalibrateScreen(this));
    }
}
