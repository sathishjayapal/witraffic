package me.sathish.witrafficalerts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sathish.witrafficalerts.corealert.bean.TrafficAlert;
import me.sathish.witrafficalerts.corealert.config.TrafficAlertUrlConfiguration;
import me.sathish.witrafficalerts.corealert.exceptions.WITrafficAlertException;
import me.sathish.witrafficalerts.corealert.service.TrafficAlertService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TrafficAlertServiceTest {
    Logger logger = LoggerFactory.getLogger(TrafficAlertServiceTest.class);
    @Autowired
    TrafficAlertService trafficAlertService;
    @Value("${importfile}")
    private String importFile;
    @Value("${mydaatakey}")
    private String mydaatakey;
    @Autowired
    TrafficAlertUrlConfiguration trafficAlertUrlConfiguration;
    List<TraffiAlertFromFile> trafficAlerts = null;

    @BeforeEach
    void setUp() {
        try {
            trafficAlerts = TraffiAlertFromFile.read(importFile);
            trafficAlerts.stream().forEach(data -> {
                TrafficAlert trafficAlert = new TrafficAlert(Long.valueOf(data.getId()), data.getMessage(),
                        data.getNotes(), data.getStartTime(), data.getEndTime(), null,
                        data.getHighImportance(), data.getSendNotification());
                String[] regions = {"Northwest", "SouthEast"};
                HashMap jsonDataMap = new HashMap();
                jsonDataMap.put("Id", data.getId());
                jsonDataMap.put("Message", data.getMessage());
                jsonDataMap.put("Notes", data.getNotes());
                jsonDataMap.put("StartTime", data.getStartTime());
                jsonDataMap.put("EndTime", data.getEndTime());
                jsonDataMap.put("Regions", regions);
                jsonDataMap.put("HighImportance", data.getHighImportance());
                jsonDataMap.put("SendNotification", data.getSendNotification());
                JSONObject jsonObject = new JSONObject(jsonDataMap);
            });
        } catch (IOException e) {
            logger.error(e.toString(), e.fillInStackTrace());
            assertTrue(Boolean.FALSE);
        }
    }

    @Test
    @DisplayName("BAD URL throws Exception")
    void parseBadURL() {
        assertThrows(WITrafficAlertException.class, () -> trafficAlertService.parseURL(null));
    }

    @Test
    @DisplayName(("Check URL is getting pulled from config server"))
    void checkValidURL() {
        assertTrue(trafficAlertUrlConfiguration.getAlertsURL().startsWith("http"));
    }

    @Test
    @DisplayName("No alerts raised in WI- Mostly")
    void checkNoALerts() {
        try {
            assertTrue(trafficAlertService.parseURL(trafficAlertUrlConfiguration.getAlertsURL()).size() == 0);
        } catch (WITrafficAlertException e) {

        }
    }

    private static class TraffiAlertFromFile {
        //fields
        private String Id, Message, Notes, StartTime, EndTime, Regions[],
                HighImportance, SendNotification;

        //reader
        static List<TraffiAlertFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TraffiAlertFromFile>>() {
                    });
        }

        protected TraffiAlertFromFile() {
        }

        String getId() {
            return Id;
        }

        String getMessage() {
            return Message;
        }

        String getNotes() {
            return Notes;
        }

        String getStartTime() {
            return StartTime;
        }

        String getEndTime() {
            return EndTime;
        }

        String[] getRegions() {
            return Regions;
        }

        String getHighImportance() {
            return HighImportance;
        }

        String getSendNotification() {
            return SendNotification;
        }
    }
}
