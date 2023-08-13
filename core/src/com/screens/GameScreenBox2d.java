package com.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.physics.box2d.Body;
import sun.awt.windows.WPrinterJob;

import java.io.Serializable;
import java.util.ArrayList;

public class GameScreenBox2d implements Screen, Serializable {
//    public GameScreenBox2d obj = this;
    MyGdxGame game;

    transient private SpriteBatch batch;

    transient private Sprite boxSprite1;
    transient private Sprite boxSprite2;
    transient private Sprite splash;

    private Stage stage;
    private Table table;

    private TextureAtlas atlas;
    private TextButton startGameButton, savedGamesButton, exitButton;
    private Label heading;
    private Skin skin;
    private BitmapFont white, black;


    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private Body box;
    private Body box2;
    private final float TIMESTEP = 1/60f;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private float speed = 0.8f;
    private Vector2 movement = new Vector2();
    private Vector2 forceApply = new Vector2();
    private Vector2 movement2 = new Vector2();
    private Vector2 forceApply2 = new Vector2();
    private int tank1;
    private int tank2;
    private ShapeRenderer shape;

    private Body proj;
    private FixtureDef projfix;
    private BodyDef projbod;
    private Array<Body> tmpBodies = new Array<Body>();

    public GameScreenBox2d(MyGdxGame game, int tank1, int tank2) {
        this.game = game;
        this.tank1 = tank1;
        this.tank2 = tank2;
    }
    public int createProj(Body b){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(box.getPosition());
        Shape shape = new CircleShape();
        shape.setRadius(0.10f);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.001f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

//        world.createBody(bodyDef).createFixture(fixtureDef);
        proj = world.createBody(bodyDef);
        proj.createFixture(fixtureDef);
        bodyDef.position.set(box.getPosition().x, box.getPosition().y);
        proj.applyLinearImpulse(0.01f, 0.01f, proj.getPosition().x, proj.getPosition().y, true);
        return 1;
    }

//    public double angleline1(float deltaTime,int x1,int y1) {
//
//        shape.begin(ShapeRenderer.ShapeType.Line);
//        shape.setColor(Color.YELLOW);
//        int x2 = x1+500-a;
//        int y2 = y1-a;
//        shape.line(x1, y1, x2,y2);
//        if(!Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//                a += 20;
//            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                a -= 20;
//            }
//        }
//        shape.end();
//        double sin = ((y2-y1)/pow((pow(y1-y2,2)+pow(x1-x2,2)),0.5));
//        return sin;
//
//
//    }
//    public double angleline2(float deltaTime,int x1,int y1) {
//
//        shape.begin(ShapeType.Line);
//        shape.setColor(Color.YELLOW);
//        int x2 = x1-500+a;
//        int y2 = y1+a;
//        shape.line(x1, y1, x2,y2);
//        if(!Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//                a -= 20;
//            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                a += 20;
//            }
//        }
//        shape.end();
//        double sin = ((y2-y1)/pow((pow(y1-y2,2)+pow(x1-x2,2)),0.5));
//        return sin;
//
//
//    }
    @Override
    public void show() {

        batch = new SpriteBatch();

        // background
        Texture splashTexture = new Texture("hello.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        splash.setPosition(-splash.getWidth()/2,-splash.getHeight()/2);

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/default/craftacular/skin/craftacular-ui.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0 ,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        white = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/font-bold-export.fnt"), false);


        // terrain
        Texture texture = new Texture(Gdx.files.internal("terrainfinalfinal.png"));
        Image terrain = new Image(texture);
        terrain.setSize(1.2f*1827,1.2f*911);
        terrain.setPosition(0,-150);
        stage.addActor(terrain);

        // Pause button
//        Texture pauseButtonTexture = new Texture("button/pause.png");
//        TextureRegion textureRegionPause = new TextureRegion(pauseButtonTexture);
//        TextureRegionDrawable pauseTextureRegionDrawable = new TextureRegionDrawable(textureRegionPause);
//        ImageButton pause = new ImageButton(pauseTextureRegionDrawable);
//        pause.setTransform(true);
//        pause.setSize(80,80);
//        pause.setPosition(pause.getX(), pause.getY());

//        InputProcessor processor = new In

//        Gdx.input.setInputProcessor(new InputController() {
//            public boolean keyDown(int keycode) {
//                if (keycode == Input.Keys.ESCAPE)
//                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseMenuScreen(game, GameScreenBox2d));
//                return true;
//            }
//        });

//        stage.addActor(pause);

        // BOX 2D shit
        world = new World(new Vector2(0, -50), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/100, Gdx.graphics.getHeight()/100);

//        Gdx.input.setInputProcessor(new InputCon {
//
//        }
//      BALL body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 1);

        Shape shape = new CircleShape();
        shape.setRadius(0.25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;



        world.createBody(bodyDef).createFixture(fixtureDef);

        shape.dispose();

//        GROUND BODY DEFINITION
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);

        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[] {new Vector2(-9f,100f), new Vector2(-9.5f,0.39f),new Vector2(-8.75f, 0.39f), new Vector2(-8.6f, 0.35f),new Vector2(-8.5f, 0.3f), new Vector2(-7, -1.2f), new Vector2(-6.4f, -1.2f),new Vector2(-5.1f, -1.2f),new Vector2(-4.05f, -0.3f),new Vector2(-2.8f, -0.3f),new Vector2(-1.23f, -1.75f),new Vector2(4f, -1.75f),new Vector2(5.5f, -0.3f),new Vector2(6.8f, -0.3f),new Vector2(7.8f, -1.2f),new Vector2(9.5f, -1.2f),new Vector2(9.5f, 100f)});
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0;
        world.createBody(bodyDef).createFixture(fixtureDef);
        groundShape.dispose();


//        Tank Box body definition
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(-6.2f,0);

//        box shape
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.6f,0.3f);
//        tank shape 2
//        PolygonShape boxShape2 = new PolygonShape();
//        boxShape2.setAsBox(0.6f,0.3f);


//        fixture
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = boxShape;
        fixtureDef1.friction = 0f;
        fixtureDef1.restitution = 0;
        fixtureDef1.density = 10;

//        projectile
//        shape = new CircleShape();
//        shape.setRadius(0.15f);
//
//        fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 2.5f;
//        fixtureDef.friction = 0.5f;
//        fixtureDef.restitution = 0;

//        world.createBody(bodyDef).createFixture(fixtureDef);
//        shape.dispose();





        Gdx.input.setInputProcessor(new InputController(){
            @Override
            public boolean keyDown(int keycode) {
                switch(keycode){
                    case Input.Keys.A:
                        if(box.getAngle()>0.02){
                            movement.y = -speed;
                            movement.x = 0;
                        }
                        else {
                            movement.x = -speed;
                            movement.y = 0;
                        }
                        if(box.getAngle()<-0.02){
                            movement.x = -speed*2;
                            movement.y = 0;
                        }
                        forceApply.x = 0;
                        forceApply.y = 0;

//                        movement.y = -1;
                        break;

                    case Input.Keys.D:
                        if(box.getAngle()<-0.02){
                            movement.y = -speed;
                            movement.x = 0;
                        }
                        else {
                            movement.x = speed;
                            movement.y = 0;
                        }
                        if(box.getAngle()>0.02){
                            movement.y = 0;
                            movement.x = speed*2;
                        }

//                        movement.y = -1;
                        forceApply.x = 0;
                        forceApply.y = 0;

                        break;
                    case Input.Keys.LEFT:
                        if(box2.getAngle()>0.02){
                            movement2.y = -speed;
                            movement2.x = 0;
                        }
                        else {
                            movement2.x = -speed;
                            movement2.y = 0;
                        }
                        if(box2.getAngle()<-0.02){
                            movement2.x = -speed*2;
                            movement2.y = 0;
                        }
                        forceApply2.x = 0;
                        forceApply2.y = 0;

//                        movement.y = -1;
                        break;

                    case Input.Keys.RIGHT:
                        if(box2.getAngle()<-0.02){
                            movement2.y = -speed;
                            movement2.x = 0;
                        }
                        else {
                            movement2.x = speed;
                            movement2.y = 0;
                        }
                        if(box2.getAngle()>0.02){
                            movement2.y = 0;
                            movement2.x = speed*2;
                        }

//                        movement.y = -1;
                        forceApply2.x = 0;
                        forceApply2.y = 0;

                        break;

                    case Input.Keys.SPACE:
//
//                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseMenuScreen(game, obj));
                        createProj(proj);
                        break;


//                        projfix = fixtureDef;

                    case Input.Keys.P:
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseMenuScreen(game, GameScreenBox2d.this));





                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch(keycode){
                    case Input.Keys.A:
                        movement.x = 0;
                        if(box.getAngle()>0.02){
                            forceApply.x = 0;
                            forceApply.y = 355;
                        }
                        else if(box.getAngle()<-0.02){
                            forceApply.x = 0;
                            forceApply.y = 355;
                        }

                        break;

                    case Input.Keys.D:
                        movement.x = 0;
                        System.out.println(box.getAngle());
                        if(box.getAngle()>0.02){
//                            box.applyForce();
                            forceApply.x = 0;
                            forceApply.y = 355;


                        }
                        else if(box.getAngle()<-0.02){
//                            box.applyForce();
                            forceApply.x = 0;
                            forceApply.y = 355;


                        }


//                        movement.y = 0;

                        break;
                    case Input.Keys.LEFT:
                        movement2.x = 0;
                        if(box2.getAngle()>0.02){
                            forceApply2.x = 0;
                            forceApply2.y = 355;
                        }
                        else if(box2.getAngle()<-0.02){
                            forceApply2.x = 0;
                            forceApply2.y = 355;
                        }

                        break;

                    case Input.Keys.RIGHT:
                        movement2.x = 0;
                        System.out.println(box2.getAngle());
                        if(box2.getAngle()>0.02){
//                            box.applyForce();
                            forceApply2.x = 0;
                            forceApply2.y = 355;


                        }
                        else if(box2.getAngle()<-0.02){
//                            box.applyForce();
                            forceApply2.x = 0;
                            forceApply2.y = 355;


                        }


//                        movement.y = 0;

                        break;

                }
                return true;
            }
        });
        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef1);
        bodyDef.position.set(6.2f, 4f);

        box2 = world.createBody(bodyDef);
        box2.createFixture(fixtureDef1);

        if(tank1 == 1){
            boxSprite1 = new Sprite(new Texture("Tanks/tank1.png"));
            boxSprite1.setSize(2,1);
        }else if(tank1 == 2){
            boxSprite1 = new Sprite(new Texture("Tanks/tank2.png"));
            boxSprite1.setSize(2,1.65f);
        }else if(tank1 == 3){
            boxSprite1 = new Sprite(new Texture("Tanks/tank3.png"));
            boxSprite1.setSize(2,1.65f);
        }
        if(tank2 == 1){
            boxSprite2 = new Sprite(new Texture("Tanks/tank1.png"));
            boxSprite2.setSize(2,1);
            boxSprite2.flip(true, false);
        }else if(tank2 == 2){
            boxSprite2 = new Sprite(new Texture("Tanks/tank2.png"));
            boxSprite2.flip(true, false);
            boxSprite2.setSize(2,1.65f);
        }else if(tank2 == 3){
            boxSprite2 = new Sprite(new Texture("Tanks/tank3.png"));
            boxSprite2.setSize(2,1.65f);
            boxSprite2.flip(true, false);
        }
//        boxSprite1 = new Sprite(new Texture("Tanks/tank1.png"));

        boxSprite1.setOrigin(boxSprite1.getWidth()/2, boxSprite1.getHeight()/2);
        box.setUserData(boxSprite1);
//        boxSprite2.setSize(2,1);
        boxSprite2.setOrigin(boxSprite2.getWidth()/2, boxSprite2.getHeight()/2);
        box2.setUserData(boxSprite2);


        boxShape.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.position.set(box.getPosition().x, box.getPosition().y, 0);
//        camera.update();

        batch.setProjectionMatrix(camera.combined);
        stage.draw();
        stage.act(delta);
        batch.begin();
//        splash.draw(batch);

        world.getBodies(tmpBodies);
        for(Body body : tmpBodies) {
            if(body.getUserData()!=null && body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
//                sprite.set
                sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y-sprite.getHeight()/2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }
        }

        batch.end();


        debugRenderer.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(forceApply, false);
        box.setLinearVelocity(movement);
        box2.applyForceToCenter(forceApply2, false);
        box2.setLinearVelocity(movement2);

    }
//    public void update(float deltaTime){
//        world.step(TIMESTEP, (int) VELOCITY, (int) POSITION);
//    }

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
        dispose();

    }

    @Override
    public void dispose() {


        world.dispose();
        debugRenderer.dispose();
        boxSprite1.getTexture().dispose();
        boxSprite2.getTexture().dispose();

    }
}
