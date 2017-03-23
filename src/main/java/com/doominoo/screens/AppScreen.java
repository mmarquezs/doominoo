package com.doominoo.screens;

import com.doominoo.ScreensManager;

/**
 * Created by marcmarquez on 06/02/17.
 */
public interface AppScreen {
    void setScreensManager(ScreensManager parent);
    default void setBackScreen(String screen) {
        return;
    }
}
