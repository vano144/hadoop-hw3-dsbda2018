package twitterIntegration;

// main settings of application

public class Settings {
    public static String LOGSTASH_URL = "127.0.0.1";
    public static Integer LOGSTASH_PORT = 5044;

    public static String TWITTER_CONSUMER_KEY = System.getenv("TWITTER_CONSUMER_KEY");
    public static String TWITTER_CONSUMER_SECRET = System.getenv("TWITTER_CONSUMER_SECRET");
    public static String TWITTER_ACCESS_TOKEN = System.getenv("TWITTER_ACCESS_TOKEN");
    public static String TWITTER_ACCESS_TOKEN_SECRET = System.getenv("TWITTER_ACCESS_TOKEN_SECRET");

    public static boolean isFilledTwitterKeys() {
        return TWITTER_CONSUMER_KEY != null &&
               TWITTER_CONSUMER_SECRET != null &&
               TWITTER_ACCESS_TOKEN != null &&
                TWITTER_ACCESS_TOKEN_SECRET != null;
    }
}
