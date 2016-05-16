package com.mckesson.esb.maven;

import java.io.File;

/**
 * The interface for communicating with the Tibco API
 */
public interface TibcoBusinessWorksClient {

    String deployArchive(String domain, File archive) throws TibcoBusinessWorksClientException;

    String getArchives(String domain) throws TibcoBusinessWorksClientException;
}
