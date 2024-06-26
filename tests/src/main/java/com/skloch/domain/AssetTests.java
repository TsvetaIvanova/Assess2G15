package com.skloch.domain;
import com.skloch.game.*;

import com.badlogic.gdx.Gdx;
import org.example.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(org.example.GdxTestRunner.class)
public class AssetTests {
    @Test
    public void testGameScreenAssetsExist() {
        assertTrue("The asset for the energy bar exists",
                Gdx.files.internal(GameScreen.IMAGE_ENERGY_BAR).exists());
        assertTrue("The asset for the energy bar outline exists",
                Gdx.files.internal(GameScreen.IMAGE_ENERGY_BAR_OUTLINE).exists());
    }
}