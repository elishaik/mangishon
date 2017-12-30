package com.example.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class example extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    private Texture background;
    private AnimatedSprite spaceshipAnimated;
    private ShotManager shotManager;
    private Music gameMusic;
    private Enemy enemy;
    private CollisionManager collisionManager;
    private boolean isGameOver = false;

    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batch = new SpriteBatch();

        background = new Texture(Gdx.files.internal("data/space-background.png"));
        Texture spaceshipTexture = new Texture(Gdx.files.internal("data/spaceship-spritesheet.png"));
        Sprite spaceshipSprite = new Sprite(spaceshipTexture);

        spaceshipAnimated = new AnimatedSprite(spaceshipSprite);
        spaceshipAnimated.setPosition(SCREEN_WIDTH / 2, 0);

        Texture shotTexture = new Texture(Gdx.files.internal("data/shot-spritesheet.png"));
        Texture enemyshotTexture = new Texture(Gdx.files.internal("data/enemy-shot-spritesheet.png"));
        shotManager = new ShotManager(shotTexture, enemyshotTexture);

        Texture enemyTexture = new Texture(Gdx.files.internal("data/enemy-spritesheet.png"));
        enemy = new Enemy(enemyTexture, shotManager);

        collisionManager = new CollisionManager(spaceshipAnimated, enemy, shotManager);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("data/game-music.mp3"));
        gameMusic.setVolume(.25f);
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);

        if(isGameOver) {
            BitmapFont font = new BitmapFont();
            font.getData().setScale(5);
            font.draw(batch, "GAME OVER", 200, 200);

        }

        spaceshipAnimated.draw(batch);
        shotManager.draw(batch);
        enemy.draw(batch);
        batch.end();

        handleInput();

        if(!isGameOver) {
            spaceshipAnimated.move();
            enemy.update();
            shotManager.update();

            collisionManager.handleCollisions();
        }

        if (spaceshipAnimated.isDead()){
            isGameOver = true;
        }
    }

    private void handleInput() {

        if (Gdx.input.isTouched()) {
            if (isGameOver){
                spaceshipAnimated.setDead(false);
                isGameOver = false;
            }

            Vector3 touchPostion = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPostion);

            if (touchPostion.x > spaceshipAnimated.getX()) {
                spaceshipAnimated.moveRight();
            } else {
                spaceshipAnimated.moveLeft();
            }

            shotManager.firePlayerShot(spaceshipAnimated.getX());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
