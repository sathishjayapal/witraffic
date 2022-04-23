package me.sathish.witrafficreader.core.bean;

public class TrafficInformation {
    String roadwayName;
    String Description;
    float distance;
    float normalTime;
    float currentTime;
    float delay;
    String regionl;

    public TrafficInformation() {
    }

    public TrafficInformation(String roadwayName, String description, float distance, float normalTime, float currentTime, float delay, String regionl) {
        this.roadwayName = roadwayName;
        Description = description;
        this.distance = distance;
        this.normalTime = normalTime;
        this.currentTime = currentTime;
        this.delay = delay;
        this.regionl = regionl;
    }

    public String getRoadwayName() {
        return roadwayName;
    }

    public void setRoadwayName(String roadwayName) {
        this.roadwayName = roadwayName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
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
