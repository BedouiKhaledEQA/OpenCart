package com.BetaFrameWork.steps;

import com.BetaFrameWork.utils.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hooks extends Base {
    @Before
    public void runsBeforeAnyScenario() {


    }

    @After
    public void runsAfterAnyScenario(Scenario scenario) throws IOException {
        // Prendre un screenshot de la totalité de la page
        BufferedImage image = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver)
                .getImage();

        // Convertir le screenshot en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] screenshot = baos.toByteArray();

        // Attacher le screenshot au scenario
        scenario.attach(screenshot, "image/png", "image");
        // Quit the driver
        driver.quit();
    }


    }


