package me.sathish.witrafficalerts.corealert.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TrafficAlert {
    @Id
    private long Id;
    @Column
    String message;
    @Column
    String notes;
    @Column
    private String startTime;
    @Column
    private String endTime;
    @Column
    private String regions;
    @Column
    private String highImportance;
    @Column
    private String sendNotification;

    public TrafficAlert() {
    }

    public TrafficAlert(long id, String message, String notes, String startTime, String endTime, String regions,
                        String highImportance, String sendNotification) {
        this.Id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.regions = regions;
        this.message = message;
        this.notes = notes;
        this.highImportance = highImportance;
        this.sendNotification = sendNotification;
    }

}
