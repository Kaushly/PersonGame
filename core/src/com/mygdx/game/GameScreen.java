package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Character.Enemy;
import com.mygdx.game.Character.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameScreen {
    private SpriteBatch batch;
    private Hero hero;
    private BackGround backGround;
    private List<Enemy> enemies;


    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    public void create() {
        hero = new Hero(this);
        enemies = new ArrayList<>();
        enemies.addAll(Arrays.asList(
                new Enemy(this),
                new Enemy(this),
                new Enemy(this),
                new Enemy(this)
        ));
        backGround = new BackGround();
    }

    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backGround.render(batch);

        enemies.forEach(p -> p.render(batch));

        hero.render(batch);
        batch.end();
    }

    public void update(float dt) {
        hero.update(dt);
        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy currentEnemy = enemies.get(i);
            if(!currentEnemy.isAlive()){
                enemies.remove(currentEnemy);
            }
        }
        if(!hero.isAlive()){
            hero.setHp(hero.getMaxHp());
            enemies.add(new Enemy(this));
        }
    }

    public Hero getHero() {
        return hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public BackGround getBackGround() {
        return backGround;
    }
}

