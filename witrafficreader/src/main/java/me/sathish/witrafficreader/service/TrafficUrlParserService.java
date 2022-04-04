package me.sathish.witrafficreader.service;

import me.sathish.witrafficreader.bean.TrafficInformation;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficUrlParserService {
    public List<TrafficInformation> parseURL(String uri) {
        final String key = "DONOTPUSH";
        String fullURI = uri + key;
        List<TrafficInformation> jsonResponse = new ArrayList<>();
        int timeout = 3;
        RequestConfig config = RequestConfig.custom().
                setConnectTimeout(timeout * 1000).
                setConnectionRequestTimeout(timeout * 1000).
                setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config).build();
        HttpGet httpGet = new HttpGet(fullURI);
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            JSONArray trafficInformationArray = new JSONArray(EntityUtils.toString(response.getEntity()));
            for (int i = 0; i < trafficInformationArray.length(); i++) {
                JSONObject jsonObject = trafficInformationArray.getJSONObject(i);
                String roadwayName = jsonObject.getString("RoadwayName");
                String description = jsonObject.getString("Description");
                float distance = jsonObject.getFloat("Distance");
                float normalTime = jsonObject.getFloat("NormalTime");
                float currentTimeurrentTime = jsonObject.getFloat("CurrentTime");
                float delay = jsonObject.getFloat("Delay");
                String regional = jsonObject.getString("Region");
                TrafficInformation trafficInformation = new TrafficInformation(roadwayName, description, distance, normalTime, currentTimeurrentTime, delay, regional);
                jsonResponse.add(trafficInformation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
