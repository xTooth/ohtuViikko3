package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);
        System.out.println("\n" + bodyText2);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        Gson mapper2 = new Gson();
        Course[] courses = mapper2.fromJson(bodyText2, Course[].class);

        System.out.println("opisleijanumero: " + studentNr);

        for (Course course : courses) {

            int totalh = 0;
            int totale = 0;

            System.out.println(course.getFullName() + " " + course.getTerm() + " " + course.getYear());

            for (Submission submission : subs) {
                if (submission.getCourse().equals(course.getName())) {

                    int points = course.getExercises()[submission.getWeek()];
                    System.out.println("viikko " + submission.getWeek() + ":\n  tehtyjä tehtäviä:" + submission.getExercises().size() + "/" + points + " aikaa kului " + submission.getHours() + " trhdyt tehtävät: " + submission.getExercises().toString());
                }
            }

            for (Submission sub : subs) {
                if (sub.getCourse().equals(course.getName())) {
                    totalh += sub.getHours();
                    totale += sub.getExercises().size();
                }
            }

            int total = 0;
            for (Integer i : course.getExercises()) {
                total += i;
            }

            System.out.println("yhteensä: " + totale + "/" + total + " tehtävää " + totalh + " tuntia");
            Statistics stats = new Statistics("https://studies.cs.helsinki.fi/courses/" + course.getName() + "/stats");
            System.out.println("kurssilla yhteensä " + stats.getSubmissions() + " palautusta, palautettuja tehtäviä " + stats.getExercises() + " kpl, aikaa käytetty yhteensä " + stats.getHours() + " tuntia");
        }
    }
}
