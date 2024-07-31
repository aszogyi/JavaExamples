package com.example.callexistingsoapservicespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class CallExistingSoapServiceSpringBootApplication {


    static SSLContext createSSLContext(InputStream keyStore, String keyStorePassword, String keyAlias,
                                       InputStream trustStore, String trustStorePassword) throws GeneralSecurityException, IOException {
        KeyManager[] keyManagers = new KeyManager[] {new SSLKeyManager(keyStore, keyStorePassword, keyAlias)};

        TrustManager[] trustManagers;
        if (trustStore == null) {
            trustManagers = new TrustManager[] {new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }};
        } else {
            KeyStore trustStoreInstance = KeyStore.getInstance("JKS");
            trustStoreInstance.load(trustStore, trustStorePassword.toCharArray());

            trustStore.close();

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStoreInstance);
            trustManagers = tmf.getTrustManagers();
        }


        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagers, trustManagers, new SecureRandom());
        return sslContext;
    }

        /*
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CallExistingSoapServiceSpringBootApplication.class, args);


        String urlString = "https://skygate-cetus.preprod.tardis.telekom.de/soabp/rpc/ReleaseTest/de.telekom.magentaview.architecture.MagentaView/MagentaView/getDeliveryTerms6/up";
        String keystorePath = "c:/_MobilFunk/keyStore/AWS_S3_keystore/soabp.keystore";
        String keystorePassword = "mN+:Bo;VFBwyYyTA!cQs";
        String keyAlias = "csva";
        String requestXmlPath = "c:/_MobilFunk/keyStore/AWS_S3_keystore/request.xml";


        String urlString = "https://skygate-cetus.preprod.tardis.telekom.de:443/soabp/rpc/ReleaseTest/tmo.rbp.wholesaleconnectivity.architecture.WholesaleConnectivity:CommonBSI/DE/getContractMCComplete18/up";
        String keystorePath = "c:/_MobilFunk/keyStore/getContract_Roland/SOAKeyStore";
        String keystorePassword = "QsGbmA36sZnLZNJBq0sV";
        String keyAlias = "cbsi-app-ta1";
        String requestXmlPath = "c:/_MobilFunk/keyStore/getContract_Roland/getContractMCComplete18-tst1.xml";


        // Load the keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());

        // Initialize KeyManagerFactory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        // Initialize TrustManagerFactory
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        // Initialize SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        // Create the connection
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setDoOutput(true);

        // Read the input XML file
        String xmlRequest = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(requestXmlPath)));

        // Write the XML to the connection
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = xmlRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        String finalvalue = "";
        if (responseCode == 200) {
            Document responseDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(responseDoc), new StreamResult(System.out));
        } else {
            System.out.println("Error: " + connection.getResponseMessage());

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            finalvalue = response.toString();
            System.out.println("final value: " +finalvalue);
        }


    }
    */


    public static void main(String[] args) throws Exception {

        SpringApplication.run(CallExistingSoapServiceSpringBootApplication.class, args);


        /*
        String urlString = "https://example.com/soap-endpoint";
        String keystorePath = "path/to/keystore.jks";
        String keystorePassword = "yourKeystorePassword";
        String keyAlias = "yourKeyAlias";
        String requestXmlPath = "path/to/request.xml";

         */

        String urlString = "https://skygate-cetus.preprod.tardis.telekom.de:443/soabp/rpc/ReleaseTest/tmo.rbp.wholesaleconnectivity.architecture.WholesaleConnectivity:CommonBSI/DE/getContractMCComplete18/up";
        String keystorePath = "c:/_MobilFunk/keyStore/getContract_Roland/SOAKeyStore";
        String keystorePassword = "QsGbmA36sZnLZNJBq0sV";
        String keyAlias = "cbsi-app-ta1";
        String requestXmlPath = "c:/_MobilFunk/keyStore/getContract_Roland/getContractMCComplete18-tst1.xml";

        /*
        String urlString = "https://skygate-cetus.preprod.tardis.telekom.de/soabp/rpc/ReleaseTest/net.tmobile.rbp.toolbox.architecture.RMCToolbox:RMC/DE/getUserRoles2/up";
        String keystorePath = "c:/_MobilFunk/keyStore/BOSS_Certifications/TST1/soabp.keystore";
        String keystorePassword = "QwertzuiopAsdfghjkl!";
        String keyAlias = "csva";
        String requestXmlPath = "c:/_MobilFunk/keyStore/userrole/userRoleRequest.xml";

         */


        // Load the keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());

        // Initialize KeyManagerFactory with keyAlias
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        // Wrap the KeyManagers to use specific alias
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        for (int i = 0; i < keyManagers.length; i++) {
            if (keyManagers[i] instanceof X509KeyManager) {
                keyManagers[i] = new AliasSpecificX509KeyManager((X509KeyManager) keyManagers[i], keyAlias);
            }
        }

        // Initialize SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        //SSLContext sslContext = SSLContextFactory.createSSLContext(keystore, keypass, keyAlias);
        sslContext.init(keyManagers, null, null);

        // Create the connection
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setDoOutput(true);

        // Read the input XML file
        String xmlRequest = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(requestXmlPath)));

        // Write the XML to the connection
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = xmlRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == 200) {
            Document responseDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(responseDoc), new StreamResult(System.out));
        } else {
            System.out.println("Error: " + connection.getResponseMessage());
        }
    }

    // Custom KeyManager to use specific key alias
    private static class AliasSpecificX509KeyManager implements X509KeyManager {
        private final X509KeyManager originalKeyManager;
        private final String alias;

        public AliasSpecificX509KeyManager(X509KeyManager keyManager, String alias) {
            this.originalKeyManager = keyManager;
            this.alias = alias;
        }

        @Override
        public String chooseClientAlias(String[] keyType, java.security.Principal[] issuers, java.net.Socket socket) {
            return alias;
        }

        @Override
        public String chooseServerAlias(String keyType, java.security.Principal[] issuers, java.net.Socket socket) {
            return originalKeyManager.chooseServerAlias(keyType, issuers, socket);
        }

        @Override
        public java.security.cert.X509Certificate[] getCertificateChain(String alias) {
            return originalKeyManager.getCertificateChain(this.alias);
        }

        @Override
        public String[] getClientAliases(String keyType, java.security.Principal[] issuers) {
            return originalKeyManager.getClientAliases(keyType, issuers);
        }

        @Override
        public java.security.PrivateKey getPrivateKey(String alias) {
            return originalKeyManager.getPrivateKey(this.alias);
        }

        @Override
        public String[] getServerAliases(String keyType, java.security.Principal[] issuers) {
            return originalKeyManager.getServerAliases(keyType, issuers);
        }
    }

}
