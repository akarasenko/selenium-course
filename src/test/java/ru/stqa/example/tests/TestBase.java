package ru.stqa.example.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.example.application.Application;

public class TestBase {
    public Application app;

    @Before
    public void start() {
        app = new Application();
    }

    @After
    public void stop() {
        app.quit();
    }
}
