package com.vova_cons.hundread_games.tds.utils;

/**
 * Created by anbu on 17.05.20.
 **/
public class LogUtils {
    public static void log(String tag, String msg) {
        System.out.println("[" + tag + "]: " + msg);
    }

    public static void error(String tag, String msg) {
        System.err.println("[" + tag + "]: " + msg);
    }

    public static Tag tag(String tag) {
        return new Tag(tag);
    }

    public static Tag tag(Class<?> type) {
        return new Tag(type.getSimpleName());
    }

    public static class Tag {
        private String tag;

        public Tag(String tag) {
            this.tag = tag;
        }

        public void log(String msg) {
            LogUtils.log(tag, msg);
        }

        public void error(String msg) {
            LogUtils.error(tag, msg);
        }
    }
}
