package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Minicraft extends ApplicationAdapter {
    FitViewport viewport;

    final int WIDTH = 100;
    final int HEIGHT = 100;

    SpriteBatch batch;
    TextureRegion right, left, down, up;

    float x = 0;
    float y = 0;
    float xv = 0;
    float yv = 0;

    final float MAX_VELOCITY = 300;


    @Override
    public void resize (int width, int height){
        viewport.update(width, height);
    }

    @Override
    public void create() {
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][2];
        left = grid[6][3];
        left.flip(true, false);
    }

    @Override
    public void render() {
        move();

        TextureRegion img;

        if (Math.round(yv) > 0) {
            img = up;
        } else if (Math.round(yv) < 0) {
            img = down;
        } else if (Math.round(xv) > 0) {
            img = right;
        }
        else{
            img = left;
        }

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img,x, y, WIDTH, HEIGHT);
        batch.end();
    }

    void move() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = MAX_VELOCITY * -1;


        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = MAX_VELOCITY * -1;
        }

        x += xv * Gdx.graphics.getDeltaTime();
        y += yv * Gdx.graphics.getDeltaTime();

        if ( x < 0 ){
            x = 0;
        }
        if (y < 0){
            y =0;
        }
        if (x > Gdx.graphics.getWidth() - WIDTH) {
            x = Gdx.graphics.getWidth() - WIDTH;
        }
        if (y >Gdx.graphics.getHeight() - HEIGHT){
            y = Gdx.graphics.getHeight() - HEIGHT;
        }

        xv *= 0.9;
        yv *= 0.9;
    }
}
