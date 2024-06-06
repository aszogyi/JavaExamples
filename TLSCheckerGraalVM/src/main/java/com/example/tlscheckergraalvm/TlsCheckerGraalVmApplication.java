package com.example.tlscheckergraalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class TlsCheckerGraalVmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlsCheckerGraalVmApplication.class, args);

        try {
            // List the supported SSL/TLS protocols
            String[] protocols = SSLContext.getDefault().createSSLEngine().getEnabledProtocols();
            System.out.println("Enabled SSL/TLS Protocols:");
            for (String protocol : protocols) {
                System.out.println(protocol);
            }

            // Print the default SSL/TLS protocol
            SSLContext context = SSLContext.getDefault();
            System.out.println("Default SSL Protocol: " + context.getProtocol());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
