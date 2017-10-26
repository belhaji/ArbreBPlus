import org.junit.Test;
import tp.FileUtils;
import tp.Pair;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by adilbelhaji on 10/21/17.
 */
public class FileutilsTestCase {

    @Test
    public void testGetPairListForKeyAge() {
        List<Pair<Integer, String>> pairList = FileUtils.getPairListForKeyAge("all.txt");
        assertNotNull(pairList);
        assertTrue(pairList.size() > 0);
        pairList.forEach(System.out::println);

    }
    @Test
    public void testGetPairListForKeyName() {
        List<Pair<String, Integer>> pairList = FileUtils.getPairListForKeyName("all.txt");
        assertNotNull(pairList);
        assertTrue(pairList.size() > 0);
        pairList.forEach(System.out::println);

    }
}
