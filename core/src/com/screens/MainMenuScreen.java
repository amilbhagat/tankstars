package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen {
    MyGdxGame game;
    private SpriteBatch batch;
    private Sprite splash;

    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private TextButton startGameButton, savedGamesButton, exitButton;
    private Label heading;
    private Skin skin;
    private BitmapFont white, black;

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashTexture = new Texture("zyro-image.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/default/craftacular/skin/craftacular-ui.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button");
        textButtonStyle.down = skin.getDrawable("button-hover");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        exitButton = new TextButton("EXIT", textButtonStyle);
        startGameButton = new TextButton("START GAME", textButtonStyle);
        savedGamesButton = new TextButton("SAVED GAMES", textButtonStyle);
        exitButton.pad(20);
        startGameButton.pad(20);
        savedGamesButton.pad(20);

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });


        startGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Tank1_1(game));
            }
        });

        savedGamesButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new SavedGamesScreen(game));
            }
        });


        table.add(startGameButton).fillY().space(20);
        table.row();
        table.add(savedGamesButton).fillY().space(20);
        table.row();
        table.add(exitButton).fillY().space(20);
        table.padTop(150);
//        table.align(Align.top);
        table.debug();
        stage.addActor(table);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        splash.draw(batch);
        batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        splash.getTexture().dispose();

    }
}
