package space.harbour.java.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Searcher implements Runnable {
    public static Queue<String> toVisit = new LinkedList<>();
    public static  Set<String> visited  = new HashSet<>();
    public static final int MAXSIZE = 30;

    public static String patern =
            "(https?|ftp|file)\\:\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static void main(String[] args) throws InterruptedException {
        toVisit.add("https://vasart.github.io/supreme-potato/");
        toVisit.add("https://www.vogella.com/tutorials/JUnit/article.html");
        toVisit.add("https://github.com/alexMtzRivero/oop_homework_2");
        //toVisit.add("https://casier-judiciaire.justice.gouv.fr/pages/accueil.xhtml");
        toVisit.add("https://stackoverflow.com/questions/23871265/how-to-add-jsonobjects-to-javax-json-jsonarray-using-loop-dynamically");

        final int jobs = 5;

        ExecutorService executor = Executors.newFixedThreadPool(jobs);
        for (int i = 0; i < jobs; i++) {
            executor.execute(new Searcher(i));
        }
        executor.shutdown();
        // now wait for the jobs to finish
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        System.out.println(visited.size());
    }

    int id;

    public Searcher(int id) {
        this.id = id;
    }

    private static String getUrl(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            String url = null;
            if (matcher.find()) {
                url = matcher.group();
            }
            return url;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public void run() {
        String current = toVisit.poll();
        System.out.println(" Searcher: " + id + " in " + current);

        if (current != null && (!visited(current) && !inQueue(current))) {
            visit(current);
        }


    }

    public Boolean inQueue(String link) {
        return toVisit.contains(link);
    }

    public void visit(String link)  {
        ArrayList<String> urls = new ArrayList<>();

        try {
            urls = getUrlsOfWebPage(new URL(link));
        } catch (MalformedURLException e)  {
            e.printStackTrace();
        }
        addNewLinksOf(urls);
        visited.add(link);
        while (!toVisit.isEmpty() && visited.size() <   MAXSIZE) {
            run();
        }
    }

    public void addNewLinksOf(ArrayList<String> urls) {
        for (String url : urls) {
            if ((!visited(url) && !inQueue(url))) {
                toVisit.add(url);
                System.out.println(" Searcher: " + id + " adding : " + url);
            }
        }
    }

    public Boolean visited(String link) {
        return visited.contains(link);
    }

    public static ArrayList<String> getUrlsOfWebPage(URL url) {
        ArrayList<String> urls = new ArrayList<>();
        try (InputStream is = url.openConnection().getInputStream();
                InputStreamReader in = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(in); ) {
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                StringBuilder content = new StringBuilder();
                String text = content.append(inputLine).toString();
                String foundUrl = getUrl(text, patern);
                if (foundUrl != null) {
                    urls.add(foundUrl);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to retrieve content of " + url.toString());
            e.printStackTrace();
        }
        return urls;
    }
}
