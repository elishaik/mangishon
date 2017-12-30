package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;

import java.util.Random;

/**
 * Created by aviad on 14 דצמבר 2017.
 */

class Enemy {

    public static final float ENEMY_SPEED = 250;
    private final Texture enemyTexture;
    private AnimatedSprite animatedSprtie;
    private final ShotManager shotManager;
    private float spawnTimeOut = 0f;

    public Enemy(Texture enemyTexture, ShotManager shotManager) {
        this.enemyTexture = enemyTexture;
        this.shotManager = shotManager;

        spawn();
    }

    private void spawn() {
        Sprite enemySprite = new Sprite(enemyTexture);
        animatedSprtie = new AnimatedSprite(enemySprite);
        int xPosition = createRandomPosition();
        animatedSprtie.setPosition(xPosition, example.SCREEN_HEIGHT - animatedSprtie.getHeight());
        animatedSprtie.setVelocity(new Vector2(ENEMY_SPEED, 0));
        animatedSprtie.setDead(false);
    }

    private int createRandomPosition() {
        Random random = new Random();
        int randomNumber = random.nextInt(example.SCREEN_WIDTH - animatedSprtie.getWidth() + 1);
        return randomNumber + animatedSprtie.getWidth() / 2;
    }

    public void draw(SpriteBatch batch) {
        if (!animatedSprtie.isDead())
            animatedSprtie.draw(batch);
    }

    public void update() {
        if (animatedSprtie.isDead()) {
            spawnTimeOut -= Gdx.graphics.getDeltaTime();
            if (spawnTimeOut <= 0) {
                spawn();
            }
        } else {

            if (shouldChangeDirection()) {
                animatedSprtie.changeDirection();
            }

            if (shouldFire()) {
                shotManager.fireEnemyShot(animatedSprtie.getX());
            }
            animatedSprtie.move();
        }
    }

    private boolean shouldFire() {
        Random random = new Random();
        return random.nextInt(51) == 0;
    }

    private boolean shouldChangeDirection() {
        Random random = new Random();
        return random.nextInt(21) == 0;
    }

    public Rectangle getBoundingBox() {
        return animatedSprtie.getBoundingBox();
    }

    public void hit() {
        animatedSprtie.setDead(true);
        spawnTimeOut = 2f;
    }
}
