package com.mckesson.esb.maven;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 */
@Mojo( name = "deploy-archive")
public class TibcoDeployArchiveMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("Deploy Archive");
        StringBuffer result = new StringBuffer();

        try {
            String url = "http://localhost:8079/bw/v1/domains/MikeDomain/archives";
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.setParameter("replace", "true");

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(uriBuilder.build());

            File file = new File("/Users/Zatko/Desktop/TestModule_1.0.0.ear");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "TestModule_1.0.0.ear");

            HttpEntity entity = builder.build();
            post.setEntity(entity);

            //request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(post);

            getLog().info("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch(Exception e) {

        }

        getLog().info(result);
    }
}
