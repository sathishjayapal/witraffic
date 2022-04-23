package me.sathish.witrafficalerts.corealert.service;

import me.sathish.witrafficalerts.corealert.bean.TrafficAlert;
import me.sathish.witrafficalerts.corealert.exceptions.WITrafficAlertException;
import me.sathish.witrafficalerts.corealert.repo.TrafficAlertRepo;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class TrafficAlertService {
    static final Logger LOG = LoggerFactory.getLogger(TrafficAlertService.class);
    private TrafficAlertRepo trafficAlertRepo;
    @Value("${mydaatakey}")
    private String mydataKey;

    public TrafficAlertService(TrafficAlertRepo trafficAlertRepo) {
        this.trafficAlertRepo = trafficAlertRepo;
    }

    public CloseableHttpResponse checkURLConnection(String url) throws WITrafficAlertException {
        String fullURI = url + mydataKey;
        System.out.println("Going to URL " + fullURI);
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
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                return response;
            }
        } catch (IOException e) {
            LOG.error("Exception when parsing URL for Alerts" + e.getMessage(), e.fillInStackTrace());
            throw new WITrafficAlertException("Exception when parsing for Alerts : URL" + url + "\t Key" + mydataKey.substring(0, 5));
        }
        throw new WITrafficAlertException("Bad URL or Key passed for getting alerts \t" + fullURI);
    }

    public List<TrafficAlert> parseURL(String uri) throws WITrafficAlertException {
        List<TrafficAlert> jsonResponse = Collections.emptyList();
        CloseableHttpResponse response = checkURLConnection(uri);
        JSONArray trafficInformationArray = null;
        try {
            trafficInformationArray = new JSONArray(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            LOG.error("Invalid JSON returned for parsing WI Alerts" + e.getMessage(), e.fillInStackTrace());
            throw new WITrafficAlertException("Invalid JSON returned for parsing WI Alerts");
        }
        if (trafficInformationArray.length() > 0) {
            for (int i = 0; i < trafficInformationArray.length(); i++) {
                jsonResponse.add(getTrafficInformation(trafficInformationArray.getJSONObject(i)));
            }
        }
        return jsonResponse;
    }

    private TrafficAlert getTrafficInformation(JSONObject jsonObject) {
        Long id = jsonObject.getLong("Id");
        String message = jsonObject.getString("Message");
        String notes = jsonObject.getString("Notes");
        String startTime = jsonObject.getString("StartTime");
        String endTime = jsonObject.getString("EndTime");
        JSONArray regions = jsonObject.getJSONArray("Regions");
        StringBuilder regionStringBuilder = new StringBuilder();
        for (int j = 0; j < regions.length(); j++) {
            regionStringBuilder.append(regions.get(j)).append(":");
        }
        String highImportance = jsonObject.getString("HighImportance");
        String sendNotification = jsonObject.getString("SendNotification");
        TrafficAlert trafficInformation = new TrafficAlert(id, message, notes, startTime,
                endTime, regionStringBuilder.toString(), highImportance, sendNotification);
        trafficAlertRepo.save(trafficInformation);
        return trafficInformation;
    }

    public Iterable<TrafficAlert> lookup() {
        return trafficAlertRepo.findAll();
    }

    public long count() {
        return trafficAlertRepo.count();
    }
}
