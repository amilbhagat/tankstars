package com.screens;
import java.io.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.sun.tools.javac.Main;
import org.w3c.dom.Text;


public class SavedGamesScreen implements Screen, Serializable {
    MyGdxGame game;

    GameScreenBox2d obj;

    private SpriteBatch batch;
    private Sprite splash;

    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private TextButton savedGame1,savedGame2,savedGame3,savedGame4,savedGame5,savedGame6,savedGame7;
    private TextButton play,back;
    private Label heading;
    private Skin skin;
    private BitmapFont white, black;

//    private ScrollPane scrollPane;



    public SavedGamesScreen(MyGdxGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashTexture = new Texture("zyro-image (2).png");
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

        play = new TextButton("Play",textButtonStyle);
//        play.pad(20);

        back = new TextButton("Back",textButtonStyle);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenuScreen(game));
            }
        });


        String filename = "Games.ser";
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            obj = (GameScreenBox2d) in.readObject();

            in.close();
            file.close();
//            System.out.println("Object has been deserialized\n"
//                    + "Data after Deserialization.");
//            printdata(object);

            // System.out.println("z = " + object1.z);
        }

        catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }



        savedGame1 = new TextButton("Saved Game 1",textButtonStyle);
        savedGame1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreenBox2d(game, 1,1));
            }
        });
        savedGame2 = new TextButton("Saved Game 2",textButtonStyle);
        savedGame3 = new TextButton("Saved Game 3",textButtonStyle);
        savedGame4 = new TextButton("Saved Game 4",textButtonStyle);
        savedGame5 = new TextButton("Saved Game 5",textButtonStyle);
        savedGame6 = new TextButton("Saved Game 6",textButtonStyle);
        savedGame7 = new TextButton("Saved Game 7",textButtonStyle);
//        savedGame1.setTransform(true);
//        savedGame1.setScale(1.2f);
////        savedGame1;
//        savedGame2.setTransform(true);
//        savedGame2.setScale(1.2f);
//        savedGame3.setTransform(true);
//        savedGame3.setScale(1.2f);


        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.WHITE);

        heading = new Label("Saved Games",headingStyle);
        heading.setFontScale(2.75f);

        table.top().padTop(125);



        table.add(heading).space(100);
        table.row();
        table.add(savedGame1).space(20).width(450);
        table.row();
        table.add(savedGame2).space(20).width(450);
        table.row();
        table.add(savedGame3).space(20).width(450);
        table.row();
        table.add(savedGame4).space(20).width(450);
        table.row();
        table.add(savedGame5).space(20).width(450);
        table.row();
        table.add(back).space(100).width(300);
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

    }
}
