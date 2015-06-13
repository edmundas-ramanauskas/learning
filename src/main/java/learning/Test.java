package learning;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by edmundas on 04/02/2015.
 */
public class Test {

    //compile pattern once because it's expensive
    private static final Pattern STARTS_WITH_UPPER_CASE_LETTER = Pattern.compile("^[A-Z].*$");

    public static boolean startWithUpperCaseLetter(String text) {
        return STARTS_WITH_UPPER_CASE_LETTER.matcher(text).matches();
    }

    public static final void main(String[] args) throws IOException, XMLStreamException {
        System.out.println(startWithUpperCaseLetter("teSt"));
    }
}
