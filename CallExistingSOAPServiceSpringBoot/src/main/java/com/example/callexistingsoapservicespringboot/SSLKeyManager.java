package com.example.callexistingsoapservicespringboot;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * X509ExtendedKeyManager which choose a dedicated key/certificate of the key store
 * remark: necessary due TFC server don't provide a list of trusted issuers
 * @author A1176016
 *
 */
class SSLKeyManager extends X509ExtendedKeyManager {

    /**
     * wrapped key manager
     */
    protected final X509ExtendedKeyManager km;

    /**
     * alias to use
     */
    protected final String keyAlias;

    /**
     * constructor of the key manager which choose the first alias
     * @param keyStore - InputStream to read the JKS key store
     * @param keyStorePassword - password of the key store
     * @throws GeneralSecurityException
     * @throws IOException
     */
    SSLKeyManager(InputStream keyStore, String keyStorePassword) throws GeneralSecurityException, IOException {
        this(keyStore, keyStorePassword, null);
    }

    /**
     * constructor of the key manager
     * @param keyStore- InputStream to read the JKS key store
     * @param keyStorePassword - password of the key store
     * @param keyAlias - alias of the key/certificate for client authentication (null -> first key shall be used)
     * @throws GeneralSecurityException
     * @throws IOException
     */
    SSLKeyManager(InputStream keyStore, String keyStorePassword, String keyAlias) throws GeneralSecurityException, IOException {

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(keyStore, keyStorePassword.toCharArray());

        if (keyAlias == null || keyAlias.isEmpty()) {
            Enumeration<String> aliases = ks.aliases();
            if (aliases.hasMoreElements()) {
                this.keyAlias = ks.aliases().nextElement();
            } else {
                throw new GeneralSecurityException("keystore is empty");
            }
        } else {
            if (ks.containsAlias(keyAlias)) {
                this.keyAlias = keyAlias;
            } else {
                throw new GeneralSecurityException("keystore does not contains key '"+keyAlias+"'");
            }
        }

        kmf.init(ks, keyStorePassword.toCharArray());
        km = (X509ExtendedKeyManager) kmf.getKeyManagers()[0];
    }

    @Override
    public String chooseClientAlias(String[] arg0, Principal[] arg1,
                                    Socket arg2) {
        return keyAlias;
    }

    @Override
    public String chooseServerAlias(String arg0, Principal[] arg1,
                                    Socket arg2) {
        return keyAlias;
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return km.getCertificateChain(alias);
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return km.getClientAliases(keyType, issuers);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return km.getPrivateKey(alias);
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return km.getClientAliases(keyType, issuers);
    }
}

