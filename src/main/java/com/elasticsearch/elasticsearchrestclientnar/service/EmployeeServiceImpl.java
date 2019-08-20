package com.elasticsearch.elasticsearchrestclientnar.service;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public String getAllEmployees() {
        RestClient restClient = null;
        String responseBody = "";
        try{
            Request request = new Request("GET", "http://localhost:9200/employee_data/hr_details/_search");
            restClient = getRestClient();

            Response response = restClient.performRequest(request);

            RequestLine requestLine = response.getRequestLine();
            HttpHost host = response.getHost();
            int statusCode = response.getStatusLine().getStatusCode();
            Header[] headers = response.getHeaders();
            responseBody = EntityUtils.toString(response.getEntity());
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            closeRestClient(restClient);
        }

        return responseBody;
    }

    private RestClient getRestClient(){
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );

        Header[] defaultHeaders = new Header[]{new BasicHeader("Content-Type", "application/json")};
        restClientBuilder.setDefaultHeaders(defaultHeaders);
        return restClientBuilder.build();
    }

    private void closeRestClient(RestClient restClient){
        try{
            if(restClient != null){
                restClient.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
