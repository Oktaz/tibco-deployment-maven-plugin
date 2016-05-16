import com.mckesson.esb.maven.TibcoDeployArchiveMojo;
import com.mckesson.esb.maven.TibcoDeploymentMojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
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
    public void testInvalidProject() throws Exception {

        File projectCopy = this.resources.getBasedir( "project--valid" );
        File pom = new File( projectCopy, "pom.xml");
        assertNotNull( pom );
        assertTrue( pom.exists());


        TibcoDeployArchiveMojo mojo = (TibcoDeployArchiveMojo) this.rule.lookupMojo( "deploy-archive", pom );
        assertNotNull( mojo );
        mojo.execute();
    }

}
