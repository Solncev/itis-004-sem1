package com.solncev.chat.view;

import com.solncev.chat.ChatApplication;
import javafx.scene.Parent;

public abstract class BaseView {

    private static ChatApplication application;

    public abstract Parent getView();

    public static void setApplication(ChatApplication application) {
        BaseView.application = application;
    }

    public static ChatApplication getApplication() throws Exception {
        if (application!= null) {
            return application;
        }
        throw new Exception("No Application in BaseView");
    }
}
