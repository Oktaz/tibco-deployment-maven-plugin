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
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 */
@Mojo( name = "deploy-archive")
public class TibcoDeployArchiveMojo extends AbstractMojo {

    @Parameter( defaultValue = "${project}", readonly = true )
    private MavenProject project;


    @Parameter( required = true, readonly = true )
    private String url;

    @Parameter( required = true, readonly = true)
    private String domain;

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("Deploy Archive");

        getLog().info("Server Base URL:: " + url);
        getLog().info("Domain:: " + domain);

        TibcoBusinessWorksClient client = new RestTibcoBusinessWorksClient(url);

        try {
//            String filePath = project.getBuild().getOutputDirectory() + ;
            File file  = project.getArtifact().getFile();
            String deployResponse = client.deployArchive(domain, file);
            getLog().info("Archives::\n" + deployResponse);

        } catch (TibcoBusinessWorksClientException e) {
            getLog().error(e);
        }

    }
}
