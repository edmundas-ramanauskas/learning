package learning;

import learning.util.ProxyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by edmundas on 04/02/2015.
 */
public class Test {

    private static String PROXY_HOST = "primary-proxy";
    private static String PROXY_PORT = "8080";
    private static String PROXY_USER = "";
    private static String MESSAGE = "Enter your password for proxy: ";

    public static final void main(String[] args) throws IOException {
        System.out.print(MESSAGE);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password = null;
        try {
            password = br.readLine().trim();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }

        ProxyUtil.proxy(PROXY_HOST, PROXY_PORT, PROXY_USER, password);

        URL url = new URL("http://www.google.com/");
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
        uc.connect();
        System.out.println(uc.getContentType());
        uc.disconnect();
    }
}
