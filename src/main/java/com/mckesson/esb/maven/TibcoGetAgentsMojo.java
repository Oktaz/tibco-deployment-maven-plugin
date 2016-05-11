package com.mckesson.esb.maven;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */
@Mojo( name = "getagents")
public class TibcoGetAgentsMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("Get Agents");

        String url = "http://mhsihubp-012.oc.mckesson.com:8079/bw/v1/agents/info";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        StringBuffer result = new StringBuffer();

        try {
            // add request header
            //request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

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
