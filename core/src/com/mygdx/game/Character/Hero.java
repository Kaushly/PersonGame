package com.mygdx.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.BackGround;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public class Hero extends GameCharacter {
    GameScreen gameScreen;


    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = new Texture("Hero.png");
        this.position = new Vector2(100, 200);
        this.direction = new Vector2(0,0);
        this.temp = new Vector2(0,0);
        this.maxHp = 100.0f;
        this.hp = this.maxHp;
        this.speed = 250.0f;
        this.weapon = new Weapon("Sword", 130.0f, 0.5f, 5.0f);

    }

    @Override
    public void update(float dt) {
//        float dst = gameScreen.getEnemy().getPosition().dst(this.position);
        for (Enemy enemy : gameScreen.getEnemies()) {
            float dst = enemy.getPosition().dst(this.position);

            if (dst < weapon.getAttackRadius()) {
                attackTimer += dt;
                if (attackTimer >= weapon.getAttackPeriod()) {
                    attackTimer = 0.0f;
                    enemy.takeDamage(weapon.getDamage());
                }
            }
        }
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f){
            damageEffectTimer = 0.0f;
        }
        direction.set(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction.x = 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction.x = -1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction.y = 1.0f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction.y = -1.0f;
        }
        temp.set(position).mulAdd(direction, speed* dt);
        if(gameScreen.getBackGround().isCellPassable(temp)){
            position.mulAdd(direction, speed * dt);
        }

        checkScreenUp();
    }

    public Vector2 getPosition() {
        return position;
    }


}
