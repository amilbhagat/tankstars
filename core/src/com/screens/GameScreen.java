package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MyGdxGame;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class GameScreen implements Screen {
    static MyGdxGame game;

    private static Map<String, GameScreen> instances
            = new HashMap<String, GameScreen>();
    private GameScreen (){}
    public static GameScreen getInstance(String  key) {
        if (!instances.containsKey(key)) {
            instances.put(key, new GameScreen(game));
        }
        return instances.get(key);
    }
    private SpriteBatch batch;
    private Sprite splash;

    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private TextButton startGameButton, savedGamesButton, exitButton;
    private Label heading;
    private Skin skin;
    private BitmapFont white, black;

    public GameScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashTexture = new Texture("hello.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/default/craftacular/skin/craftacular-ui.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getInstance("k");

        white = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);

        Texture texture = new Texture(Gdx.files.internal("terrainfinalfinal.png"));
        Image terrain = new Image(texture);
        terrain.setSize(1.2f*1827,1.2f*911);
        terrain.setPosition(0,-150);

        Texture textureTank1 = new Texture(Gdx.files.internal("Tanks/tank2.png"));
        Image tank1 = new Image((textureTank1));
        tank1.setScale(0.25f);
        tank1.setPosition(300,320);

        Texture textureTank2 = new Texture(Gdx.files.internal("Tanks/tank1.1.png"));
        Image tank2 = new Image((textureTank2));
        tank2.setScale(0.5f);
        tank2.setPosition(Gdx.graphics.getWidth()-400, 450);

        Texture textureHealthBarLeft = new Texture(Gdx.files.internal("greenLeft.png"));
        Image HealthBarLeft = new Image((textureHealthBarLeft));
        HealthBarLeft.setScale(0.4f);
        HealthBarLeft.setPosition(300-200, Gdx.graphics.getHeight()-150);

        Texture textureHealthBarRight = new Texture(Gdx.files.internal("greenRight.png"));
        Image HealthBarRight = new Image((textureHealthBarRight));
        HealthBarRight.setScale(0.4f);
        HealthBarRight.setPosition(Gdx.graphics.getWidth()-300-50, Gdx.graphics.getHeight()-150);

        Texture pauseButtonTexture = new Texture("button/pause.png");
        TextureRegion textureRegionPause = new TextureRegion(pauseButtonTexture);
        TextureRegionDrawable pauseTextureRegionDrawable = new TextureRegionDrawable(textureRegionPause);
        ImageButton pause = new ImageButton(pauseTextureRegionDrawable);
        pause.setTransform(true);
        pause.setSize(80,80);
        pause.setPosition(50,50);

        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
//                game.setScreen(new PauseMenuScreen(game));
            }
        });


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button");
        textButtonStyle.down = skin.getDrawable("button-hover");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;
        stage.addActor(terrain);
        stage.addActor(tank1);
        stage.addActor(tank2);
        stage.addActor(pause);
        stage.addActor(HealthBarLeft);
        stage.addActor(HealthBarRight);



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

    }
}
