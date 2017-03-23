package com.doominoo;

import com.sun.jna.Memory;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

/**
 * Created by marcmarquez on 02/02/17.
 */

public class VideoPlayer
{
    private ScreensManager manager;
    private PixelWriter pixelWriter;
    private WritablePixelFormat<ByteBuffer> pixelFormat;
    private MediaPlayerComponent mediaPlayer;
    private AnimationTimer timer;
    private static final Logger Log = LoggerFactory.getLogger( VideoPlayer.class);
    private FloatProperty videoSourceRatioProperty;
    private float forwardSpeed = 1;
    private float rewindSpeed = 1;
    private static final int SKIP_TIME_MS = 10 * 1000;
    private static final float MAX_SPEED = 32;
    private final LongProperty length = new SimpleLongProperty(this,"length",0);
    private final LongProperty time = new SimpleLongProperty(this,"time",0);
    private final LongProperty readOnlyTime = new SimpleLongProperty(this,"time",0);

    public void increaseVolume() {
        mediaPlayer.getMediaPlayer().setVolume(mediaPlayer.getMediaPlayer().getVolume()+5);
        Log.info("Volume: "+mediaPlayer.getMediaPlayer().getVolume()+"%");
    }

    public void decreaseVolume() {
        mediaPlayer.getMediaPlayer().setVolume(mediaPlayer.getMediaPlayer().getVolume()-5);
        Log.info("Volume: "+mediaPlayer.getMediaPlayer().getVolume()+"%");
    }

    public enum PlayerStatus {
        PLAYING,
        PAUSED,
        REWINDING,
        FORWARDING,
        STOPPED
    }

    private PlayerStatus status = PlayerStatus.STOPPED;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    private StackPane playerPane;
    @FXML
    private BorderPane imagePane;
    @FXML
    private ImageView imageView;

    public static VideoPlayer getPlayer(StackPane rootPane) throws IOException {
        FXMLLoader loader = new FXMLLoader(VideoPlayer.class.getClassLoader().getResource("views/videoPlayer.fxml"));
        VideoPlayer videoPlayer = new VideoPlayer(rootPane);
        loader.setController(videoPlayer);
        loader.load();
        return loader.getController();
    }

    public void setScreensManager(ScreensManager screensManager) {
        manager = screensManager;
    }


    VideoPlayer(StackPane rootPane) {
        playerPane = rootPane;
    }

    public BorderPane getPane() {
        return imagePane;
    }

    @FXML
    void initialize() {
        assert imagePane != null : "fx:id=\"imagePane\" was not injected: check your FXML file 'videoPlayer.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'videoPlayer.fxml'.";
        mediaPlayer = new MediaPlayerComponent();
        ChangeListener<Number> timeListener = (observableValue, number, t1) -> mediaPlayer.getMediaPlayer().setTime( t1.longValue());
        time.addListener(timeListener);
        mediaPlayer.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {
            @Override
            public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t, String s) {

            }

            @Override
            public void opening(MediaPlayer mediaPlayer) {

            }

            @Override
            public void buffering(MediaPlayer mediaPlayer, float v) {

            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {

            }

            @Override
            public void paused(MediaPlayer mediaPlayer) {

            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {

            }

            @Override
            public void forward(MediaPlayer mediaPlayer) {

            }

            @Override
            public void backward(MediaPlayer mediaPlayer) {

            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {

            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long l) {
//                setTime(l);
                Platform.runLater(() -> {
                    time.removeListener(timeListener);
                    time.setValue(l);
                    time.addListener(timeListener);
                });
//                time.setValue(l);
            }

            @Override
            public void positionChanged(MediaPlayer mediaPlayer, float v) {

            }

            @Override
            public void seekableChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void pausableChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void titleChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void snapshotTaken(MediaPlayer mediaPlayer, String s) {

            }

            @Override
            public void lengthChanged(MediaPlayer mediaPlayer, long l) {
                setLength(l);

            }

            @Override
            public void videoOutput(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void scrambledChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void elementaryStreamAdded(MediaPlayer mediaPlayer, int i, int i1) {

            }

            @Override
            public void elementaryStreamDeleted(MediaPlayer mediaPlayer, int i, int i1) {

            }

            @Override
            public void elementaryStreamSelected(MediaPlayer mediaPlayer, int i, int i1) {

            }

            @Override
            public void corked(MediaPlayer mediaPlayer, boolean b) {

            }

            @Override
            public void muted(MediaPlayer mediaPlayer, boolean b) {

            }

            @Override
            public void volumeChanged(MediaPlayer mediaPlayer, float v) {
            }

            @Override
            public void audioDeviceChanged(MediaPlayer mediaPlayer, String s) {

            }

            @Override
            public void chapterChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void error(MediaPlayer mediaPlayer) {

            }

            @Override
            public void mediaMetaChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void mediaSubItemAdded(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t) {

            }

            @Override
            public void mediaDurationChanged(MediaPlayer mediaPlayer, long l) {

            }

            @Override
            public void mediaParsedChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void mediaFreed(MediaPlayer mediaPlayer) {

            }

            @Override
            public void mediaStateChanged(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void mediaSubItemTreeAdded(MediaPlayer mediaPlayer, libvlc_media_t libvlc_media_t) {

            }

            @Override
            public void newMedia(MediaPlayer mediaPlayer) {

            }

            @Override
            public void subItemPlayed(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void subItemFinished(MediaPlayer mediaPlayer, int i) {

            }

            @Override
            public void endOfSubItems(MediaPlayer mediaPlayer) {

            }
        });



        videoSourceRatioProperty = new SimpleFloatProperty(0.4f);
        setupImageView();

        imageView.setFitWidth(playerPane.widthProperty().doubleValue());
        imageView.setFitHeight(playerPane.widthProperty().doubleValue());
        imageView.fitWidthProperty().bind(playerPane.widthProperty());
        imageView.fitHeightProperty().bind(playerPane.heightProperty());
        timer = new AnimationTimer() {
            private long before;
            @Override
            public void handle(long now) {
                renderFrame();
                switch (status) {
                    case FORWARDING:
                        if ((now-before)>=0.5e9) {
                            if (forwardSpeed > 8) {
                                // The video looks very pixelated and choopy. It is better to simply skip.
                                mediaPlayer.getMediaPlayer().setRate(1);
                                mediaPlayer.getMediaPlayer().mute();
                                mediaPlayer.getMediaPlayer().skip(((long) forwardSpeed) * 1000);
                            } else {
                                mediaPlayer.getMediaPlayer().setRate(forwardSpeed);
                            }
                            before = now;
                        }
                        break;
                    case REWINDING:
                        if ((now-before)>=0.5e9) {
                            mediaPlayer.getMediaPlayer().mute();
                            mediaPlayer.getMediaPlayer().skip(-((long) rewindSpeed) * 1000);
                            before = now;
                        }
                        break;

                }
            }
        };
        timer.start();
        Log.info("mediaPlayer initiated");
    }


   class ModifiedBufferFormatCallback implements BufferFormatCallback {
       public BufferFormat getBufferFormat(final int sourceWidth, final int sourceHeight) {
           Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
           Platform.runLater(() -> videoSourceRatioProperty.set((float) sourceHeight / ((float) sourceWidth)));

           return new RV32BufferFormat(((int) visualBounds.getWidth()), ((int) visualBounds.getHeight()));
       }
   }

    private class MediaPlayerComponent extends DirectMediaPlayerComponent {

        public MediaPlayerComponent() {
            super(new ModifiedBufferFormatCallback());
        }
    }

    private void setupImageView() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        WritableImage writableImage = new WritableImage(((int) visualBounds.getWidth()), ((int) visualBounds.getHeight()));
        pixelWriter = writableImage.getPixelWriter();
        pixelFormat = PixelFormat.getByteBgraInstance();
        imageView.setImage(writableImage);
    }

    private void changeForwardSpeed() {
        forwardSpeed *= 2;
        if (forwardSpeed > MAX_SPEED) {
            forwardSpeed = 2;
        }
    }

    private void changeRewindSpeed() {
        rewindSpeed *= 2;
        if (rewindSpeed > MAX_SPEED) {
            rewindSpeed = 2;
        }
    }

    final void renderFrame() {
        Memory[] nativeBuffers = mediaPlayer.getMediaPlayer().lock();
        if (nativeBuffers != null) {
            Memory nativeBuffer = nativeBuffers[0];
            if (nativeBuffers != null) {
                ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0,nativeBuffer.size());
                BufferFormat bufferFormat = ((DefaultDirectMediaPlayer) mediaPlayer.getMediaPlayer()).getBufferFormat();
                if (bufferFormat.getWidth() > 0 && bufferFormat.getHeight()>0) {
                    pixelWriter.setPixels(0,0,bufferFormat.getWidth(),bufferFormat.getHeight(),pixelFormat,byteBuffer,bufferFormat.getPitches()[0]);
                    BufferedImage buffImage = SwingFXUtils.fromFXImage(imageView.getImage(),null);
//                    RewindFilter rewindFilter = new RewindFilter();
//                    rewindFilter.setWaveType(RippleFilter.NOISE);
////                    rewindFilter.setXAmplitude(120);
////                    rewindFilter.setXWavelength(170);
////                    rewindFilter.setYAmplitude(0);
//                    rewindFilter.setEdgeAction(TransformFilter.WRAP);
//                    rewindFilter.setInterpolation(TransformFilter.BILINEAR);
//                    MotionBlurFilter rewindFilter = new MotionBlurFilter();
//                    rewindFilter.setDistance(2);
//                    BufferedImage result = rewindFilter.filter(buffImage, null);
//                    WritableImage writableImage = SwingFXUtils.toFXImage(result,null);
//                    pixelWriter = writableImage.getPixelWriter();
//                    imageView.setImage(writableImage);
                }
            }
        }
        mediaPlayer.getMediaPlayer().unlock();
    }

    public void play(String media) {
        mediaPlayer.getMediaPlayer().playMedia(media);
        status = PlayerStatus.PLAYING;
    }

    public void play() {
        mediaPlayer.getMediaPlayer().play();
        status = PlayerStatus.PLAYING;
    }

    public PlayerStatus getStatus() {
       return status;
    }

    public boolean isPlaying() {
        return mediaPlayer.getMediaPlayer().isPlaying();
    }

    public void pause() {
        status = PlayerStatus.PAUSED;
        mediaPlayer.getMediaPlayer().pause();
    }

    public void stop() {
        status = PlayerStatus.STOPPED;
        mediaPlayer.getMediaPlayer().stop();
    }

    public void forward() {
//        if (status == PlayerStatus.FORWARDING) {
//            changeForwardSpeed();
//        }
        if (status != PlayerStatus.FORWARDING) {
            forwardSpeed = 1;
        }
        changeForwardSpeed();
        status = PlayerStatus.FORWARDING;
    }

    public void rewind() {
        if (status != PlayerStatus.REWINDING) {
            rewindSpeed = 1;
        }
        changeRewindSpeed();
        status = PlayerStatus.REWINDING;
    }

    public void stopForward() {
        status = PlayerStatus.PLAYING;
        forwardSpeed = 1;
        if (mediaPlayer.getMediaPlayer().isMute()) {
            mediaPlayer.getMediaPlayer().mute();
        }
        mediaPlayer.getMediaPlayer().setRate(1);
    }

    public void stopRewind() {
        status = PlayerStatus.PLAYING;
        if (mediaPlayer.getMediaPlayer().isMute()) {
            mediaPlayer.getMediaPlayer().mute();
        }
    }
    Pane getPlayerPane() {
        return playerPane;
    }

    double getTime() {
        return time.get();
    }

    void setTime(double v) {
        Log.info("Setting time");
        mediaPlayer.getMediaPlayer().setTime(((long) v));
        //time.set(v);
    }

    public LongProperty timeProperty() {
        return time;
    }

    double getLength() {
        return length.get();
    }

    void setLength(long v) {
        length.set(v);
    }

    public LongProperty lengthProperty() {
        return length;
    }
}
