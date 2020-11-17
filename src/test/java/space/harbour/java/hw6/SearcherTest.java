package space.harbour.java.hw6;

import java.net.MalformedURLException;
import java.net.URL;
import junit.framework.TestCase;
import org.junit.Assert;

public class SearcherTest extends TestCase {

    public void testVisit() {
        Searcher s = new Searcher(1);
        s.visit("https://vasart.github.io/supreme-potato/");
        Assert.assertTrue(Searcher.visited.contains("https://vasart.github.io/supreme-potato/"));
    }

    public void testAddNewLinksOf() throws MalformedURLException {

        Searcher s = new Searcher(1);
        s.addNewLinksOf(Searcher.getUrlsOfWebPage(new URL("https://vasart.github.io/supreme-potato/")));

        Assert.assertEquals(3, Searcher.toVisit.size());
    }

    public void testGetUrlsOfWebPage() throws MalformedURLException {

        Searcher s = new Searcher(1);

        Assert.assertNotNull(Searcher.getUrlsOfWebPage(new URL("https://vasart.github.io/supreme-potato/")));
    }
}