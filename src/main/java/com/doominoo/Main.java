package com.doominoo;
/**
 * Created by marcmarquez on 02/02/17.
 */

import com.doominoo.screens.MainScreen;
import com.doominoo.screens.VideoPlayerScreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {
    private static final Logger Log = LoggerFactory.getLogger( Main.class);
    public static void main(String[] args) {
        launch(args);
    }
    private static Stage window;
    private static ScreensManager screensManager = new ScreensManager();
    //public DirectMediaPlayer mediaPlayer;
    public StackPane rootPane;
    //public VideoPlayer videoPlayer;
    public BorderPane videoPlayerPane;
    @Override
    public void start(Stage primaryStage)  throws Exception {
        /**
         * Add windows to the WindowManager. Sets one as the main one and shows it at the stage.
         */
        window = primaryStage;
        window.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        BackgroundService service = new BackgroundService();
        service.setPeriod(Duration.seconds(60));
        service.start();
        service.setOnFailed(workerStateEvent -> {
//            Log.error(service.getException().getMessage());
            service.getException().printStackTrace();
        });


        //Scene scene = new Scene(new Group(screensManager));
        screensManager.setWindow(window);
        screensManager.addScreen("VideoPlayerScreen",new VideoPlayerScreen(screensManager));
        screensManager.addScreen("MainScreen", new MainScreen(service, screensManager));
//        screensManager.addScreen("MainScreen",new MainScreen( screensManager));
//        screensManager.setActiveScreenTo("VideoPlayerScreen");
        screensManager.setActiveScreenTo("MainScreen");
        window.setScene(new Scene(screensManager));

//        VideoPlayer videoPlayer = ((VideoPlayerScreen) screensManager
//                .getScreen("VideoPlayerScreen"))
//                .getVideoPlayer();
//        videoPlayer.play("file:///home/marcmarquez/tmp/Todos_estan_bien.mkv");

        window.show();

//        rootPane = new StackPane();
//        videoPlayer = VideoPlayer.getPlayer(rootPane);
//        rootPane.getChildren().addAll(videoPlayer.getPane());



//        BorderPane borderPane = new BorderPane(videoPlayer.getPlayerPane());
        //BorderPane borderPane = new BorderPane();

//        borderPane.setCenter(videoPlayer.getCanvas());

        //borderPane.setBackground(new Background(new BackgroundFill()));
        //borderPane.setCenter(videoPlayer.getPlayerPane());
//        videoPlayerOverlay = new VideoPlayerOverlay2();
//        final Popup popup = new Popup(); popup.setX(300); popup.setY(200);
//        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
//        videoPlayer.play("https://www.youtube.com/watch?v=ppUl_CBuyz8&user=UCx72VxFRGsDMM7ASsRBdg5g");
//        videoPlayer.play("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");
//        videoPlayer.play("https://video-edge-c2b0a0.fra02.hls.ttvnw.net/v0/CrgBSiA7Noi-Dt4Q37j3PTQcMtpMSzsT1IDqCZb1qVRio_KYnsIi4AhDhioo6QqVFG6xfvqPu38mPm9QDR8yk8xzT355LIVP8rYSkdSmU7PxHJ8mJlCfUxCO7-eD9WI91AD5Nl96C3r0ox0m-nakmjFBFnaiV7jL0eLcR-PeTSKfQvV_GfL5SSyiA33HRyVdfY3mHbktowJmruBKTM4bie6joEM3aFbBM4TSImQFNDCC6WMBJrBoI4Gu8hIQ5DkkL7c7NCSmABJvCJfCwhoMog2HzUxRBw0GCLxM/index-live.m3u8");

//        window.show();
//        videoPlayerOverlay.show(window);
    }

    @Override
    public void stop() throws Exception {
        //videoPlayer.stop();
    }
}
