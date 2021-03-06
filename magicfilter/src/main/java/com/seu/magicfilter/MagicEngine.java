package com.seu.magicfilter;

import com.seu.magicfilter.camera.CameraEngine;
import com.seu.magicfilter.filter.helper.MagicFilterType;
import com.seu.magicfilter.utils.MagicParams;
import com.seu.magicfilter.widget.MagicCameraView;
import com.seu.magicfilter.widget.base.MagicBaseView;

/**
 * Created by why8222 on 2016/2/25.
 */
public class MagicEngine {
    private static MagicEngine magicEngine;

    public static MagicEngine getInstance() {
        if (magicEngine == null)
            throw new NullPointerException("MagicEngine must be built first");
        else
            return magicEngine;
    }

    private MagicEngine(Builder builder) {

    }

    public void setFilter(MagicFilterType type) {
        MagicParams.magicBaseView.setFilter(type);
    }

    public void startRecord() {
        if (MagicParams.magicBaseView instanceof MagicCameraView)
            ((MagicCameraView) MagicParams.magicBaseView).changeRecordingState(true);
    }

    public void stopRecord() {
        if (MagicParams.magicBaseView instanceof MagicCameraView)
            ((MagicCameraView) MagicParams.magicBaseView).changeRecordingState(false);
    }

    public void setBeautyLevel(int level) {
        if (MagicParams.magicBaseView instanceof MagicCameraView && MagicParams.beautyLevel != level) {
            MagicParams.beautyLevel = level;
            ((MagicCameraView) MagicParams.magicBaseView).onBeautyLevelChanged();
        }
    }

    public void switchCamera() {
        CameraEngine.switchCamera();
    }

    public void switchFlashlight() { CameraEngine.switchFlashlight(); }

    public void focusOnTouch() { CameraEngine.focusOnTouch(); }

    public static class Builder {

        public MagicEngine build(MagicBaseView magicBaseView) {
            MagicParams.context = magicBaseView.getContext();
            MagicParams.magicBaseView = magicBaseView;
            return new MagicEngine(this);
        }

        public Builder setVideoPath(String path) {
            MagicParams.videoPath = path;
            return this;
        }

        public Builder setVideoName(String name) {
            MagicParams.videoName = name;
            return this;
        }

        public Builder setVideoHeight(int height) {
            MagicParams.HEIGHT = height;
            return this;
        }

        public Builder setVideoWidth(int width) {
            MagicParams.WIDTH = width;
            return this;
        }

    }
}
