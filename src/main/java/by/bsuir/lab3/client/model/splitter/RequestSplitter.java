package by.bsuir.lab3.client.model.splitter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestSplitter {
    private static final Pattern requestPart = Pattern.compile("(?:([^\\s\"]+)|(?:\"([^\"]+)\"))");

    private RequestSplitter() {
    }

    public static String[] split(String request) {
        ArrayList<String> parts = new ArrayList<>();
        Matcher requestMatcher;
        int groupNumber;
        String group;

        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }

        requestMatcher = requestPart.matcher(request);
        while (requestMatcher.find()) {
            for (groupNumber = 1; groupNumber <= requestMatcher.groupCount(); ++groupNumber) {
                group = requestMatcher.group(groupNumber);
                if (group != null) {
                    parts.add(group);
                }
            }
        }

        return parts.toArray(new String[0]);
    }

}
