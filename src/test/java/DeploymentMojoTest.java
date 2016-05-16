import com.mckesson.esb.maven.TibcoDeployArchiveMojo;
import com.mckesson.esb.maven.TibcoDeploymentMojo;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.artifact.ProjectArtifact;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class DeploymentMojoTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testDeployArchive() throws Exception {

        File projectCopy = this.resources.getBasedir( "project--valid" );
        File pom = new File( projectCopy, "pom.xml");
        assertNotNull( pom );
        assertTrue( pom.exists());

        TibcoDeployArchiveMojo mojo = (TibcoDeployArchiveMojo) this.rule.lookupMojo( "deploy-archive", pom );
        assertNotNull( mojo );

        final MavenProject project = new MavenProject();
        project.setFile( pom );
        project.getBuild().setOutputDirectory("src/test/resources");
        File earFile = new File("src/test/resources/TestModule_1.0.0.ear");
        assertNotNull("EAR file was not found", earFile);

        ProjectArtifact artifact = new ProjectArtifact(project);
        artifact.setFile(earFile);
        project.setArtifact(artifact);

        this.rule.setVariableValueToObject( mojo, "project", project );
        assertNotNull( this.rule.getVariableValueFromObject( mojo, "project" ));

        mojo.execute();
    }


    @Test
    public void testGetArchives() throws Exception {

        File projectCopy = this.resources.getBasedir( "project--valid" );
        File pom = new File( projectCopy, "pom.xml");
        assertNotNull( pom );
        assertTrue( pom.exists());

        TibcoDeployArchiveMojo mojo = (TibcoDeployArchiveMojo) this.rule.lookupMojo( "deploy-archive", pom );
        assertNotNull( mojo );

        final MavenProject project = new MavenProject();
        project.setFile( pom );
        project.getBuild().setOutputDirectory("src/test/resources");
        File earFile = new File("src/test/resources/TestModule_1.0.0.ear");
        assertNotNull("EAR file was not found", earFile);

        ProjectArtifact artifact = new ProjectArtifact(project);
        artifact.setFile(earFile);
        project.setArtifact(artifact);

        this.rule.setVariableValueToObject( mojo, "project", project );
        assertNotNull( this.rule.getVariableValueFromObject( mojo, "project" ));

        mojo.execute();
    }
}
