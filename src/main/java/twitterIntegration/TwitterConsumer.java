package twitterIntegration;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;

public class TwitterConsumer {

    private Socket socket;
    private Format formatter;
    private StreamListener listener = null;


    public TwitterConsumer() {
        // initialize twitter consumer class

        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public Format getFormatter() {
        // getter for formatter

        return formatter;
    }

    private void close() {
        // helper method to close socket

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJsonFromStatus(Status status) {
        // create from twitter4j Status object json with fields:
        // country: str, created_at: str

        String createdAt = formatter.format(status.getCreatedAt());
        JSONObject jsonString = new JSONObject()
                .put("country", status.getPlace().getCountry())
                .put("created_at", createdAt);
        return jsonString.toString();
    }


    public void startListen() {
        // start method for listen twitter

        // set configuration for auth
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(Settings.TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(Settings.TWITTER_CONSUMER_SECRET)
                .setOAuthAccessToken(Settings.TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Settings.TWITTER_ACCESS_TOKEN_SECRET);


        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        FilterQuery filtro = new FilterQuery();

        // add filter to get only messages with coordinates
        double[][] bb = {{-180, -90}, {180, 90}};
        filtro.locations(bb);

        // add listener and start listen twitter stream api
        twitterStream.addListener(listener);

        // apply filter and start
        twitterStream.filter(filtro);
    }

    public void setRawListener() {
        // init raw listener

        listener = new RawStreamListener() {
            @Override
            public void onMessage(String s) {
                //initialize socket


                try {
                    socket = new Socket(Settings.LOGSTASH_URL, Settings.LOGSTASH_PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }

                try {

                    // convert str from Api to twitter4j status object
                    Status status = TwitterObjectFactory.createStatus(s);
                    DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                    // convert data to str and send to socket
                    os.writeBytes(getJsonFromStatus(status));
                    os.flush();
                } catch (IOException | TwitterException e) {
                    e.printStackTrace();
                }
                close();

            }

            @Override
            public void onException(Exception e) {
                // callback for exceptions
                e.printStackTrace();
            }
        };

        //        StatusListener listener = new StatusListener() {
//
//            @Override
//            public void onException(Exception e) {
//                e.printStackTrace();
//            }
//            @Override
//            public void onDeletionNotice(StatusDeletionNotice arg) {
//                System.out.println("onDeletionNotice");
//            }
//            @Override
//            public void onScrubGeo(long userId, long upToStatusId) {
//                System.out.println("onScrubGeo");
//            }
//            @Override
//            public void onStallWarning(StallWarning warning) {
//                System.out.println("onStallWarning");
//            }
//            @Override
//            public void onStatus(Status status) {
//                System.out.println("onStatus");
//            }
//            @Override
//            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
//                System.out.println("onTrackLimitationNotice");
//            }
//        };
    }

}
