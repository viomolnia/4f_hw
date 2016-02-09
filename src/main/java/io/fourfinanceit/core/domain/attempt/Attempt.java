package io.fourfinanceit.core.domain.attempt;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Anna on 07.02.2016.
 */

//Entity for storing apply tries from ip addresses

@Entity
@Table(name="attempts")
public class Attempt {

    @Id
    @Column(name="attempt_id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attemptId; //id generated while adding new instance

    @Column(name="ip")
    private String ip; //string, which contains IP address, from which rest method call is made

    @Column(name="times", columnDefinition = "INTEGER")
    private int times=0; // times of calling rest method from single IP

    @Column(name="last_date")
    private Calendar lastDate; //date of last rest method call

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
