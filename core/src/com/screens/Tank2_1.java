package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

public class Tank2_1 implements Screen {

    MyGdxGame game;

    private SpriteBatch batch;
    private Sprite splash;

    private Stage stage;
    private Table table;

    private Texture myTextureRight,myTextureLeft;
    private TextureRegion myTextureRegionRight,myTextureRegionLeft;
    private TextureRegionDrawable myTexRegionDrawableRight,myTextureRegionDrawableLeft;
    private ImageButton buttonRight,buttonLeft;
    private TextureAtlas atlas;
    private TextButton savedGame1,savedGame2,savedGame3,savedGame4,savedGame5,savedGame6,savedGame7;
    private TextButton next,back;



    private Label heading;
    private Skin skin;
    private BitmapFont white, black;
    //    int counternew = 0;
//    int counterold = 2;
//    ImageButton tank;
    int tank1 ;
    public Tank2_1(MyGdxGame game, int tank1) {
        this.game = game;
        this.tank1 = tank1;
    }
//    private Image tank1(){
//        Texture texture = new Texture(Gdx.files.internal("tanks/tank1.png"));
//        Image tank1 = new Image(texture);
//        tank1.setScale(3);
//        tank1.setPosition(Gdx.graphics.getWidth()/2-texture.getWidth()*3/2, Gdx.graphics.getHeight()/2-texture.getHeight()*3/2-52);
//
//        return tank1;
//    }
//    private Image tank2(){
//        Texture texture = new Texture(Gdx.files.internal("tanks/tank2.png"));
//        Image tank1 = new Image(texture);
//        tank1.setScale(3);
//        tank1.setPosition(Gdx.graphics.getWidth()/2-texture.getWidth()*3/2, Gdx.graphics.getHeight()/2-texture.getHeight()*3/2-52);
//        stage.addActor(tank1);
//        return tank1;
//    }
//    private Image tank3(){
//        Texture texture = new Texture(Gdx.files.internal("tanks/tank3.png"));
//        Image tank1 = new Image(texture);
//        tank1.setScale(3);
//        tank1.setPosition(Gdx.graphics.getWidth()/2-texture.getWidth()*3/2, Gdx.graphics.getHeight()/2-texture.getHeight()*3/2-52);
//        stage.addActor(tank1);
//        return tank1;
//    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        Texture splashTexture = new Texture("tankbg2.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/default/craftacular/skin/craftacular-ui.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);

        Texture texture = new Texture(Gdx.files.internal("Tanks/tank1.png"));
        Image tank1 = new Image(texture);
        tank1.setSize(3*300,3*168);
        tank1.setPosition(Gdx.graphics.getWidth()/2-texture.getWidth()*3/2, Gdx.graphics.getHeight()/2-texture.getHeight()*3/2-52);



//        tank1.setSize(600, 168*2);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button");
        textButtonStyle.down = skin.getDrawable("button-hover");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        next = new TextButton("Next",textButtonStyle);
        next.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreenBox2d(game, Tank2_1.this.tank1, 1));
            }
        });
        back = new TextButton("Back",textButtonStyle);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Tank1_1(game));
            }
        });

        myTextureRight = new Texture("button/right.png");
        myTextureRegionRight = new TextureRegion(myTextureRight);
        myTexRegionDrawableRight = new TextureRegionDrawable(myTextureRegionRight);
        buttonRight = new ImageButton(myTexRegionDrawableRight);
        buttonRight.setTransform(true);
        buttonRight.setSize(20,20);

        buttonRight.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Tank2_2(game, 1));
            }
        });

//        buttonRight.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y){
//                counter++;
//                counter%=3;
//
//            }
//        });
////        buttonLeft.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y){
//                if(counter==0){
//                    tank = tank3();
//                    counter = 2;
//                }
//                else if(counter ==1){
//                    tank = tank1();
//                    counter = 0;
//                }
//
//                else if(counter ==2){
//                    tank = tank2();
//                    counter = 1;
//                }
//
//            }
//        });

//        back.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y){
//                game.setScreen(new MainMenuScreen(game));
//            }
//        });

//        buttonRight.setBounds(0,0,20,20);

        myTextureLeft = new Texture("button/left.png");
        myTextureRegionLeft = new TextureRegion(myTextureLeft);
        myTextureRegionDrawableLeft = new TextureRegionDrawable(myTextureRegionLeft);
        buttonLeft = new ImageButton(myTextureRegionDrawableLeft);
        buttonLeft.setTransform(true);
        buttonLeft.setSize(20,20);

        buttonLeft.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Tank2_3(game, 1));
            }
        });
//        buttonLeft.setScale(0.20f);

        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.WHITE);

        heading = new Label("      Player 2 : Choose Tank      ",headingStyle);
        heading.setFontScale(2.25f);
//        table.setBounds(0,0,Gdx.graphics.getWidth());
//        table.left();
//        table.setWidth(Gdx.graphics.getWidth());
//        table.top().padTop(100);

        table.add(heading).fillX().colspan(2).space(100);
        table.row().space(250);
        table.add(buttonLeft).width(120).height(120).left();
        table.add(buttonRight).width(120).height(120).right();
        table.row().padTop(50);

        table.add(back).right().padRight(20);
        table.add(next).left().padLeft(20);
//        table.debug();
        stage.addActor(tank1);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        splash.draw(batch);
//        Texture tank1 = new Texture(Gdx.files.internal("tanks/tank1.png"));
//        ImageButton imageButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(tank1)));
//        imageButton.scaleBy(3);
//        imageButton.setPosition(Gdx.graphics.getWidth()/2-tank1.getWidth()*3/2, Gdx.graphics.getHeight()/2-tank1.getHeight()*3/2-52);
////        Image tank1 = new Image(texture);
////        tank1.setScale(3);
////        tank1.setPosition(Gdx.graphics.getWidth()/2-texture.getWidth()*3/2, Gdx.graphics.getHeight()/2-texture.getHeight()*3/2-52);
//        Texture tank2 = new Texture(Gdx.files.internal("tanks/tank1.png"));
//        ImageButton imageButton2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(tank1)));
//        imageButton2.scaleBy(3);
//        imageButton2.setPosition(Gdx.graphics.getWidth()/2-tank1.getWidth()*3/2, Gdx.graphics.getHeight()/2-tank1.getHeight()*3/2-52);
//
//        Texture tank3 = new Texture(Gdx.files.internal("tanks/tank1.png"));
//        ImageButton imageButton3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(tank1)));
//        imageButton3.scaleBy(3);
//        imageButton3.setPosition(Gdx.graphics.getWidth()/2-tank1.getWidth()*3/2, Gdx.graphics.getHeight()/2-tank1.getHeight()*3/2-52);

        batch.end();



//        batch.end();
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

