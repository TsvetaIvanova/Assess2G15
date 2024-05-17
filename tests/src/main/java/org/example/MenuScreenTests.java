package org.example;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skloch.game.*;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/** Forgot that there is no way to get the new dimensions of the screen (private access)
@RunWith(GdxTestRunner.class)
public class MenuScreenTests {
    @Test
    public void testResize() {
        int width = 500;
        int height = 500;
        MenuScreen testScreen = new MenuScreen(new HustleGame(width, height));
        testScreen.resize(1000, 1000);
    }
}
 */
