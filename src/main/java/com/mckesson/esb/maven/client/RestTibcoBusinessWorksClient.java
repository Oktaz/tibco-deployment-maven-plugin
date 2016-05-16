package com.mckesson.esb.maven.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Implementation of the Tibco REST API, ignorant of Maven
 */
public class RestTibcoBusinessWorksClient implements TibcoBusinessWorksClient {

    private String serverBaseUrl;
    private final String archivesUrl = "domains/%s/archives";
    private final String browseDomainsUrl = "browse/archives";
    private static final Logger logger = LoggerFactory.getLogger(RestTibcoBusinessWorksClient.class);

    public RestTibcoBusinessWorksClient(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }

    public String deployArchive(String domain, File archive) throws TibcoBusinessWorksClientException {

        StringBuffer result = new StringBuffer();

        String url = serverBaseUrl + String.format(archivesUrl, domain);
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            throw new TibcoBusinessWorksClientException(e);
        }
        uriBuilder.setParameter("replace", "true");

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = null;
        try {
            post = new HttpPost(uriBuilder.build());
        } catch (URISyntaxException e) {
            throw new TibcoBusinessWorksClientException(e);
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", archive, ContentType.APPLICATION_OCTET_STREAM, archive.getName());

        HttpEntity entity = builder.build();
        post.setEntity(entity);

        //request.addHeader("User-Agent", USER_AGENT);

        try {
            HttpResponse response = null;
            response = client.execute(post);

            logger.info("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (IOException e) {
            throw new TibcoBusinessWorksClientException(e);
        }
    }

    public String getArchives(String domain) throws TibcoBusinessWorksClientException {

        HttpClient client = HttpClientBuilder.create().build();

        try {
            String url = serverBaseUrl + browseDomainsUrl;

            URIBuilder uriBuilder = null;
            try {
                uriBuilder = new URIBuilder(url);
            } catch (URISyntaxException e) {
                throw new TibcoBusinessWorksClientException(e);
            }
            uriBuilder.setParameter("domain", domain);

            URI build = uriBuilder.build();
            logger.info("Get Archives URL :: " + build);
            HttpGet request = new HttpGet(build);


            // add request header
//            request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return "" + result;

        } catch(Exception e) {
            throw new TibcoBusinessWorksClientException(e);
        }
    }
}
