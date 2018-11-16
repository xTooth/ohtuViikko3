/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Toothy
 */
public class Statistics {

    private int submissions;
    private int exercises;
    private int hours;
    
    public Statistics(String url) throws IOException {

        String statsResponse = Request.Get(url).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsed = parser.parse(statsResponse).getAsJsonObject();

        for (int i = 1; i <= parsed.size(); i++) {
            JsonObject week = parsed.get("" + i).getAsJsonObject();
            submissions += week.get("students").getAsInt();
            exercises += week.get("exercise_total").getAsInt();
            hours += week.get("hour_total").getAsInt();
        }

    }

    public int getExercises() {
        return exercises;
    }

    public int getSubmissions() {
        return submissions;
    }

    public int getHours() {
        return hours;
    }
    
    
}
