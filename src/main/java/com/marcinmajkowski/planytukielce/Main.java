package com.marcinmajkowski.planytukielce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.marcinmajkowski.planytukielce.domain.Link;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marcin on 19.11.2015.
 */
public class Main {
    private static final String TIMETABLE_DATE = "/20151119";

    public static void main(String[] args) throws JsonProcessingException {
        String text = new Scanner(Main.class.getResourceAsStream(TIMETABLE_DATE + "/list.html"), "windows-1250").useDelimiter("\\A").next();

        Matcher matcher = Pattern.compile("<A HREF=\"(resource/(.).*?)\" TARGET=\"grid\">(.*?)</A>").matcher(text);

        Map<String, List<Link>> links = new HashMap<>();
        while (matcher.find()) {
            String url = matcher.group(1);
            String type = matcher.group(2);
            String name = matcher.group(3);

            if (links.get(type) == null) {
                links.put(type, new ArrayList<>());
            }

            links.get(type).add(new Link(name, url));
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String linksJson = mapper.writeValueAsString(links);

        try (PrintWriter printWriter = new PrintWriter(new File(Main.class.getResource(TIMETABLE_DATE).getPath() + "/list.json"))) {
            printWriter.print(linksJson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
