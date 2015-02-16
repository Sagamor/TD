package game.controllers;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import game.Board;
import game.Config;
import game.GameSettings;
import game.Monster;
import game.descriptions.WaveDescription;
import game.descriptions.monsters.MonsterDescription;
import game.descriptions.waves.WaveParams;

/**
 * Created by Sagamor on 11/09/2014.
 */
public class WaveController {
    private final Board board;
    private final Array<WaveDescription> waves;
    private WaveDescription wave;

    public WaveController(Board board, GameSettings settings) {
        this.board = board;
        this.waves = new Array<WaveDescription>(settings.waveDescriptions);
        nextWave();
    }

    private void nextWave() {
        if (waves.size == 0) return;
        wave = waves.removeIndex(0);

        float cooldown = wave.cooldown + calcMaxWaveTime(wave.monsters.values());
        board.addAction(Actions.delay(cooldown, Actions.run(new Runnable() {
            @Override
            public void run() {
                nextWave();
            }
        })));
        for (ObjectMap.Entry<String, WaveParams> e : wave.monsters.entries()) {
            spawnMonsters(e.key, e.value);
        }
    }

    private void spawnMonsters(String monsterName, WaveParams params) {
        final MonsterDescription desc = Config.monsterDescriptions.get(monsterName);
        float period = params.frequency == 0 ? 0 : 1 / params.frequency;
        for (int i = 0; i < params.count; i++) {
            float delay = period * i;
            board.addAction(Actions.delay(delay, Actions.run(new Runnable() {
                @Override
                public void run() {
                    spawnMonster(desc);
                }
            })));
        }
    }

    private void spawnMonster(MonsterDescription desc) {
        Monster monster = new Monster(desc, board);
        board.addMonster(monster);
    }

    private float calcMaxWaveTime(ObjectMap.Values<WaveParams> values) {
        float res = 0;
        for (WaveParams params : values) {
            res = Math.max(res, params.count / params.frequency);
        }
        return res;
    }
}
