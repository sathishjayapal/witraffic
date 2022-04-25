package me.sathish.witrafficreader.core.models;

import javax.persistence.*;

@Entity
@Table
public class TrafficRoadData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    String roadwayName;
    String description;
    float distance;
    float normalTime;
    float delay;
    String regionl;

    public TrafficRoadData(String roadwayName, String description, float distance, float normalTime, float delay, String regional) {
        this.roadwayName = roadwayName;
        this.description = description;
        this.distance = distance;
        this.normalTime = normalTime;
        this.delay = delay;
        this.regionl = regional;
    }

    public TrafficRoadData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoadwayName() {
        return roadwayName;
    }

    public void setRoadwayName(String roadwayName) {
        this.roadwayName = roadwayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(float normalTime) {
        this.normalTime = normalTime;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public String getRegionl() {
        return regionl;
    }

    public void setRegionl(String regionl) {
        this.regionl = regionl;
    }
}
