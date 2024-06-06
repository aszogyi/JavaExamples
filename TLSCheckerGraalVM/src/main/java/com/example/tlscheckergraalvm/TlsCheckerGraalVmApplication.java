package com.example.tlscheckergraalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class TlsCheckerGraalVmApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        SpringApplication.run(TlsCheckerGraalVmApplication.class, args);

        String[] protocols = SSLContext.getDefault().createSSLEngine().getEnabledProtocols();
        System.out.println("Enabled SSL/TLS Protocols:");
        for (String protocol : protocols) {
            System.out.println(protocol);
        }

        SSLContext context = SSLContext.getDefault();
        System.out.println("Default SSL Protocol: " + context.getProtocol());
    }

}
