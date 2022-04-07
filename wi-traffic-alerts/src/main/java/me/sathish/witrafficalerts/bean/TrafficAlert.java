package me.sathish.witrafficalerts.bean;

public class TrafficAlert {
    private String id;
    private String startTime;
    private String endTime;
    private String[] regions;
    private String highImportance;
    private String sendNotification;
    private String port;

    public TrafficAlert() {
    }

    public TrafficAlert(String id, String startTime, String endTime, String[] regions, String highImportance, String sendNotification, String port) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.regions = regions;
        this.highImportance = highImportance;
        this.sendNotification = sendNotification;
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getRegions() {
        return regions;
    }

    public void setRegions(String[] regions) {
        this.regions = regions;
    }

    public String getHighImportance() {
        return highImportance;
    }

    public void setHighImportance(String highImportance) {
        this.highImportance = highImportance;
    }

    public String getSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(String sendNotification) {
        this.sendNotification = sendNotification;
    }
}
