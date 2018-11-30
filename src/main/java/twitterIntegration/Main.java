package twitterIntegration;


import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (Settings.isFilledTwitterKeys()) {
            TwitterConsumer twitterConsumer = new TwitterConsumer();
            twitterConsumer.setRawListener();
            twitterConsumer.startListen();
        } else {
            logger.warning("Empty environment twitter keys");
        }

    }
}
