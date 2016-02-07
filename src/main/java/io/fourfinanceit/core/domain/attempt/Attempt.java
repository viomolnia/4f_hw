package io.fourfinanceit.core.domain.attempt;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Anna on 07.02.2016.
 */
@Entity
@Table(name="attempts")
public class Attempt {

    @Id
    @Column(name="attempt_id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attemptId;

    @Column(name="ip")
    private String ip;

    @Column(name="times", columnDefinition = "INTEGER")
    private int times;

    @Column(name="last_date")
    private Calendar lastDate;

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Calendar getLastDate() {
        return lastDate;
    }

    public void setLastDate(Calendar lastDate) {
        this.lastDate = lastDate;
    }
}
