package com.mygdx.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public abstract class GameCharacter {
    GameScreen gameScreen;
    Texture texture;
    Texture textureHp = new Texture("hp.png");
    Vector2 position;
    Vector2 temp;
    Vector2 direction;
    float speed;
    float hp, maxHp;
    float damageEffectTimer;
    float attackTimer;
    Weapon weapon;
    float size_height = 80.0f;
    float size_width = 80.0f;


    public Vector2 getPosition() {
        return position;
    }

    public void render(SpriteBatch batch) {
        if (damageEffectTimer > 0) {
            batch.setColor(1, 1 - damageEffectTimer, 1 - damageEffectTimer, 1);
        }
        batch.draw(texture, position.x - size_width / 2, position.y - size_height / 2);
        batch.setColor(1, 1, 1, 1);
        batch.setColor(0, 0, 0, 1);
        batch.draw(textureHp, position.x - size_width / 2  - 2, position.y - size_height / 2 + size_height - 2, 84, 16);
        batch.setColor(1, 0, 0, 1);
        batch.draw(textureHp, position.x - size_width / 2, position.y - size_height / 2 + size_height, 0, 0, hp / maxHp * 80, 12, 1, 1, 0, 0, 0, 80, 12, false, false);
        batch.setColor(1, 1, 1, 1);
    }

    public abstract void update(float dt);

    public void checkScreenUp(float dt) {
        temp.set(position).mulAdd(direction, speed* dt);
        if(gameScreen.getBackGround().isCellPassable(temp)){
            position.set(temp);
        }

        if (position.x > Gdx.graphics.getWidth() - size_width / 2) {
            position.x = Gdx.graphics.getWidth() - size_width / 2;
        }
        if (position.x < 0.0f + size_width / 2) {
            position.x = 0.0f + size_width / 2;
        }
        if (position.y > Gdx.graphics.getHeight() - size_height / 2) {
            position.y = Gdx.graphics.getHeight() - size_height / 2;
        }
        if (position.y < 0.0f + size_height / 2) {
            position.y = 0.0f + size_height / 2;
        }
    }

    public void takeDamage(float amount) {
        hp -= amount;
        damageEffectTimer += 0.5f;
        if (damageEffectTimer > 1.0f) {
            damageEffectTimer = 1.0f;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
