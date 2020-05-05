package com.mygdx.game;

public class Weapon {
    private String name;
    private float attackPeriod;
    private float attackRadius;
    private float damage;

    public Weapon(String name, float attackRadius, float attackPeriod, float damage) {
        this.name = name;
        this.attackPeriod = attackPeriod;
        this.attackRadius = attackRadius;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public float getAttackPeriod() {
        return attackPeriod;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    public float getDamage() {
        return damage;
    }
}
