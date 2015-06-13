package learning.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by edmundas on 10/02/2015.
 */
public class ProxyUtil {

    public static void proxy(String host, String port) {
        setProxyHttp(host, port);
    }

    public static void proxy(String host, String port, String user, String pass) {
        setProxyHttp(host, port);
        setAuthenticator(user, pass);
    }

    private static void setProxyHttp(String host, String port) {
        if (host != null && port != null) {
            System.setProperty("http.proxyHost", host);
            System.setProperty("http.proxyPort", port);
            System.setProperty("https.proxyHost", host);
            System.setProperty("https.proxyPort", port);
        }
    }

    private static void setProxySocks(String host, String port) {
        if (host != null && port != null) {
            System.setProperty("socksProxyHost", host);
            System.setProperty("socksProxyPort", port);
        }
    }

    private static void setAuthenticator(String user, String pass) {
        if (user != null && pass != null) {
            Authenticator.setDefault(new ProxyAuth(user, pass));
        }
    }

    static class ProxyAuth extends Authenticator {
        private PasswordAuthentication auth;

        private ProxyAuth(String user, String password) {
            auth = new PasswordAuthentication(user, password == null ? new char[]{} : password.toCharArray());
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return auth;
        }
    }
}
