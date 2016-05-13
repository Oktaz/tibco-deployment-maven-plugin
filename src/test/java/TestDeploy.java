import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class TestDeploy {

    public void deploy() throws Exception {
        String url = "http://localhost:8079/bw/v1/domains/MikeDomain/archives";
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.setParameter("replace", "true");

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uriBuilder.build());
        StringBuffer result = new StringBuffer();

        File file = new File("/Users/Zatko/Desktop/TestModule_1.0.0.ear");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "TestModule_1.0.0.ear");

        HttpEntity entity = builder.build();
        post.setEntity(entity);

        try {
            //request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(post);

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println("Body Output: " + result);
        } catch(Exception e) {

        }

    }

    public static void main(String[] args) throws Exception {
        TestDeploy td = new TestDeploy();
        td.deploy();
    }
}
