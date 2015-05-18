package de.htwg.se.catanishsettlers.view;

import java.util.*;

/**
 * Created by Stephan on 05.04.2015.
 */
public class Log {

    public static Message.Detail detailLevel;                             // how detailed should messages be?
    public static List<Message.Category> categories = new ArrayList<Message.Category>(); // which categories to display?

    public static void display(Message ...messages) {

        // set default levels if no level was set yet
        if (detailLevel == null) detailLevel = Message.Detail.HIGH;

        /*StringBuilder sb = new StringBuilder();
        for (Message.Category category : categories) {
            sb.append(category.name());
            sb.append(", ");
        }
        System.out.println("Verbose:    " + verboseLevel);
        System.out.println("Detail:     " + detailLevel);
        System.out.println("Categories: " + sb.toString());
        System.out.println("---------------------------");*/

        List<Message> candidates = new ArrayList<Message>();
        for (Message message : messages) {
            /*String text = message.verbose + ", " + message.detail + ", " + message.categories + ", " + message.text;
            System.out.println(text);*/

            // check if message has one of the selected categories. If not, skip message.
            if (categories == null) continue;
            boolean skip = true;
            for (Message.Category category : message.categories) {
                if (categories.contains(category)) {
                    skip = false;
                    break;
                }
            }
            //System.out.println("category check");
            if (skip) continue;
            // skip check end

            //System.out.println("detail check");
            if (message.detail.ordinal() > detailLevel.ordinal()) continue;
            //System.out.println("all checks passed");

            candidates.add(message);
        }

        int max = candidates.size();
        if (max > 0) {
            for (int i = 0; i < max - 1; i++) {
                System.out.print(candidates.get(i).text + " ");
            }
            System.out.println(candidates.get(max - 1).text);
        }
        System.out.println();
    }
}
