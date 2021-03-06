package com.gakes.util;

import java.io.File;


public class PrivateConstant {


    // 文件信息
    public static class FileInfo {


        /**
         * 文件根路径
         */
        public static String BASE_FILE_PATH = "dev" + File.separator;

        // 文件类型
        /**
         * 应用类型:app
         */
        public static final int TYPE_APP = 0;
        /**
         * 文件类型:file
         */
        public static final int TYPE_MORE = 1;

        /**
         * 录音
         */
        public static final int TYPE_AUDIO_RECORD = 2;

        /**
         * 图片类型：Photo
         */
        public static final int TYPE_PHOTO = 3;

        /**
         * 视频
         */
        public static final int TYPE_VIDEO = 4;

        /**
         * 应用文件保存路径
         */
        public static final String SAVE_APP_PATH = BASE_FILE_PATH + "app" + File.separator;
        /**
         * 录音位置
         */
        public static final String SAVE_AUDIO_RECORD_PATH = BASE_FILE_PATH + "audio_record" + File.separator;

        /**
         * 图片保存路径
         */
        public static final String SAVE_PHOTO_PATH = BASE_FILE_PATH + "photo" + File.separator;

        /**
         * 视频位置
         */
        public static final String SAVE_VIDEO_PATH = BASE_FILE_PATH + "video" + File.separator;


    }




}
