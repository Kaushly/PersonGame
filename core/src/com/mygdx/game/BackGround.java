package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BackGround {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int FIELD_SIZE = 80;
    private byte[][] date;
    private Texture textureGrass;
    private ArrayList<Texture> wallTextures;

    public BackGround() {
        date = new byte[CELLS_X][CELLS_Y];
        create();

        textureGrass = new Texture("zone.jpg");
        wallTextures = new ArrayList<>();
        wallTextures.add(new Texture("wall.png"));
        wallTextures.add(new Texture("fon.png"));

    }

    private void create() {
        for (int i = 0; i < 3; i++) {
            date[MathUtils.random(0, CELLS_X-1)][MathUtils.random(0, CELLS_Y-1)] = 1;
        }
        for (int i = 0; i < 3; i++) {
            date[MathUtils.random(0, CELLS_X-1)][MathUtils.random(0, CELLS_Y-1)] = 2;
        }

    }

    public boolean isCellPassable(Vector2 position){
        return date[(int)position.x / 80][(int) position.y / 80] == 0;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(textureGrass, i * FIELD_SIZE, j * FIELD_SIZE);
                if (date[i][j] == 1) {
                    batch.draw(wallTextures.get(0), i * FIELD_SIZE, j * FIELD_SIZE);
                }
                if (date[i][j] == 2) {
                    batch.draw(wallTextures.get(1), i * FIELD_SIZE, j * FIELD_SIZE);
                }
            }
        }

    }

    public byte[][] getDate() {
        return date;
    }


}
