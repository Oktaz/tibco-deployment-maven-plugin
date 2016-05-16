package com.mckesson.esb.maven;

import com.mckesson.esb.maven.client.RestTibcoBusinessWorksClient;
import com.mckesson.esb.maven.client.TibcoBusinessWorksClient;
import com.mckesson.esb.maven.client.TibcoBusinessWorksClientException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Mojo( name = "get-archives")
public class TibcoGetArchivesMojo extends AbstractMojo {

    @Parameter( required = true, readonly = true )
    private String url;

    @Parameter( required = true, readonly = true)
    private String domain;

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("Get Archives");

        getLog().info("Server Base URL:: " + url);
        getLog().info("Domain:: " + domain);

        TibcoBusinessWorksClient client = new RestTibcoBusinessWorksClient(url);
        try {
            String archives = client.getArchives(domain);
            getLog().info(archives);
        } catch (TibcoBusinessWorksClientException e) {
            getLog().error(e);
            throw new MojoExecutionException(e.getMessage(), e);
        }


    }
}
