package com.mckesson.esb.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 *
 */
@Mojo( name = "sayhi")
public class TibcoDeploymentMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {

        getLog().info("Hello, world");



    }
}
