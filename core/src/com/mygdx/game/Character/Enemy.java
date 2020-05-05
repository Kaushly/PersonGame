package com.mygdx.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public class Enemy extends GameCharacter {
    private float moveTimer;
    private float activityRadius;

    public Enemy(GameScreen gameScreen) {
        this.texture = new Texture("Enemy.png");
        this.position = new Vector2(MathUtils.random(Gdx.graphics.getWidth()), MathUtils.random(Gdx.graphics.getHeight()));
        this.direction = new Vector2(0, 0);
        this.temp = new Vector2(0,0);
        this.speed = 70.0f;
        this.maxHp = 50.0f;
        this.hp = this.maxHp;
        this.gameScreen = gameScreen;
        this.activityRadius = 400.0f;
        this.weapon = new Weapon("Prosha", 50.0f, 0.4f,5.0f);

    }

    @Override
    public void update(float dt) {
        float dst = gameScreen.getHero().getPosition().dst(this.position);
        damageEffectTimer -= dt;
        if (damageEffectTimer < 0.0f){
            damageEffectTimer = 0.0f;
        }
        if(dst < weapon.getAttackRadius()){
            attackTimer +=dt;
            if(attackTimer >= weapon.getAttackPeriod()){
                attackTimer = 0.0f;
                gameScreen.getHero().takeDamage(weapon.getDamage());
            }
        }

        if(dst < activityRadius){
            temp.set(gameScreen.getHero().getPosition());
            temp.sub(this.position);
            temp.nor();
            position.mulAdd(temp, speed * dt);
        }else{
            position.mulAdd(direction, speed * dt);
            moveTimer -= dt;
            if (moveTimer < 0.0f) {
                moveTimer = MathUtils.random(1.0f, 3.0f);
                direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f));
                direction.nor();
            }
        }
        checkScreenUp();
    }

    public Vector2 getPosition() {
        return position;
    }
}
