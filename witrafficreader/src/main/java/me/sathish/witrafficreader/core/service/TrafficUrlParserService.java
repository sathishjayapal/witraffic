package me.sathish.witrafficreader.core.service;

import me.sathish.witrafficreader.core.bean.TrafficInformation;
import me.sathish.witrafficreader.core.models.TrafficRoadData;
import me.sathish.witrafficreader.repo.TrafficRoadDataRepo;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrafficUrlParserService {
    static final Logger LOG = LoggerFactory.getLogger(TrafficUrlParserService.class);
    private final ModelMapper modelMapper;
    final
    TrafficRoadDataRepo trafficRoadDataRepo;

    public TrafficUrlParserService(ModelMapper modelMapper, TrafficRoadDataRepo trafficRoadDataRepo) {
        this.modelMapper = modelMapper;
        this.trafficRoadDataRepo = trafficRoadDataRepo;
    }

    private TrafficInformation convertToDto(TrafficRoadData trafficRoadData) {
        TrafficInformation trafficInformation = modelMapper.map(trafficRoadData, TrafficInformation.class);
        return trafficInformation;
    }

    public List<TrafficInformation> parseURL(String uri, String keyPart1) {
        final String key = "4d628fdca4cba8dac1d1";
        List<TrafficInformation> jsonResponse = new ArrayList<>();
        if (trafficRoadDataRepo.findAll().size() > 0) {
            List<TrafficRoadData> trafficRoadDataList = trafficRoadDataRepo.findAll();
            trafficRoadDataList.stream().forEach(trafficRoadData -> {
                TrafficInformation trafficInformation = convertToDto(trafficRoadData);
                jsonResponse.add(trafficInformation);
            });
        }
        if (jsonResponse.isEmpty()) {
            String fullURI = new StringBuilder(uri).append(keyPart1).append(key).toString();
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
                        TrafficRoadData trafficRoadData = new TrafficRoadData(roadwayName, description, distance, normalTime, delay, regional);
                        trafficRoadDataRepo.save(trafficRoadData);
                        TrafficInformation trafficInformation = convertToDto(trafficRoadData);
                        jsonResponse.add(trafficInformation);
                    }

                }
            } catch (IOException e) {
                LOG.error("Exception raised ");
            } finally {
                if (jsonResponse.isEmpty()) {
                    TrafficInformation trafficInformation = new TrafficInformation("n/a", "n/a", 0.0F, 0.0F, 0.0F, 0.0F, "n/a");
                    jsonResponse.add(trafficInformation);
                }
            }
        }
        return jsonResponse;
    }
}
