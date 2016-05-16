import com.mckesson.esb.maven.TibcoDeployArchiveMojo;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.WithoutMojo;
import org.junit.Ignore;
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
    public MojoRule rule = new MojoRule()
    {
        @Override
        protected void before() throws Throwable
        {
        }

        @Override
        protected void after()
        {
        }
    };

    /**
     * @throws Exception if any
     */
    @Test
    @Ignore
    public void testSomething()
            throws Exception {

        File pom = new File(getClass().getClassLoader().getResource("src/test/resources/pom.xml").toURI());

        assertNotNull( pom );
        assertTrue( pom.exists() );

        TibcoDeployArchiveMojo myMojo = (TibcoDeployArchiveMojo) rule.lookupMojo( "touch", pom );
        assertNotNull( myMojo );
        myMojo.execute();


    }

    /** Do not need the MojoRule. */
    @WithoutMojo
    @Test
    public void testSomethingWhichDoesNotNeedTheMojoAndProbablyShouldBeExtractedIntoANewClassOfItsOwn()
    {

    }
}
