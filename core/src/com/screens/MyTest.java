package com.screens;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Test;


import static com.screens.GameScreenBox2d.*;
import static org.junit.Assert.assertEquals;

public class MyTest {
    World world = new World(new Vector2(0, -50), true);
    GameScreenBox2d obj;
    BodyDef b = new BodyDef();
    @Test
    public void testDamage(){
        assertEquals(1,(int)obj.createProj(world.createBody(b)));
    }
}

