package com.mr.mymap.shopping.common;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.*;
import java.net.InetAddress;
import java.util.Set;

/**
 * Class that contains all the authentication logic, including choosing between service account
 * credentials and OAuth2 client credentials.
 */
public class Authenticator {

    private Set<String> scopes;
    private Config config;
    private HttpTransport httpTransport;
    private JsonFactory jsonFactory;
    private DataStoreFactory dataStoreFactory;

    private InputStream inputStream;


    public Authenticator(
            HttpTransport httpTransport, JsonFactory jsonFactory, Set<String> scopes, Config config, InputStream inputStream)
            throws IOException {
        this.scopes = scopes;
        this.httpTransport = httpTransport;
        this.jsonFactory = jsonFactory;
        this.config = config;
        this.dataStoreFactory = new ConfigDataStoreFactory(config);
        this.inputStream = inputStream;
    }

    public Credential authenticate() throws IOException {
        String kk = "{\"web\":{\"client_id\":\"1004647281374-ocjvtgmnccdd9rib2581tn4mcki5b05h.apps.googleusercontent.com\",\"project_id\":\"merchant-center-1650186117263\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"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\",\"redirect_uris\":[\"http://localhost/\",\"http://localhost:9999/Callback\",\"https://merchant-center-1650186117263.et.r.appspot.com/Callback\",\"https://merchant-center-1650186117263.et.r.appspot.com:9999/Callback\"]}}";
        File clientSecretsFile = new File(config.getPath(), "client-secrets.json");
        if (true) {
            System.out.println("Loading OAuth2 client credentials.");
            try (InputStream inputStream = new ByteArrayInputStream(kk.getBytes())) {
                GoogleClientSecrets clientSecrets =
                        GoogleClientSecrets.load(jsonFactory, new InputStreamReader(inputStream));
                // set up authorization code flow
                GoogleAuthorizationCodeFlow flow =
                        new GoogleAuthorizationCodeFlow.Builder(
                                httpTransport, jsonFactory, clientSecrets, scopes)
                                .setDataStoreFactory(dataStoreFactory)
                                .build();
                // authorize
                String userID = ConfigDataStoreFactory.UNUSED_ID;
                Credential storedCredential = flow.loadCredential(userID);
//                if (storedCredential != null) {
//                    System.out.printf("Retrieved stored credential for %s from cache.%n", userID);
//                    return storedCredential;
//                }
                LocalServerReceiver receiver =
                        new LocalServerReceiver.Builder().setHost(InetAddress.getLocalHost().getHostName()).setPort(9999).build();
                Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize(userID);
                System.out.printf("Retrieved credential for %s from web.%n", userID);
                return credential;
            } catch (IOException e) {
                throw new IOException(
                        "Could not retrieve OAuth2 client credentials from the file "
                                + clientSecretsFile.getCanonicalPath());
            }
        }
        throw new IOException(
                "No authentication credentials found. Checked the Google Application"
                        + "Default Credentials and the paths "
//                        + serviceAccountFile.getCanonicalPath()
                        + " and "
                        + clientSecretsFile.getCanonicalPath()
                        + ". Please read the accompanying README.");
    }
}
