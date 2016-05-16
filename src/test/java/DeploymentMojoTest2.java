import com.mckesson.esb.maven.TibcoDeploymentMojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.Ignore;

import java.io.File;


/**
 *
 */
public class DeploymentMojoTest2 extends AbstractMojoTestCase {

    protected void setUp() throws Exception
    {
        // required for mojo lookups to work
        super.setUp();
    }

    @Ignore
    public void testSomething()
            throws Exception {

        File testPom = new File( getBasedir(),
                "src/test/resources/pom.xml" );

        TibcoDeploymentMojo mojo = (TibcoDeploymentMojo) lookupMojo( "sayhi", testPom );

        assertNotNull( mojo );


    }

}
