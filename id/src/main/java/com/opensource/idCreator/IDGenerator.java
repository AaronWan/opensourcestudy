package com.opensource.idCreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The core id generator algorithm
 *
 * @sequenceBits the bit length for sequence number
 * @idcBits the bit length idc number
 * @idcId the idc id
 * @ticks the time accuracy as milliseconds
 * @epochTime the timestamp of epoch time
 *
 * Created by liyiguang on 15/12/16.
 */
public class IDGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IDGenerator.class);

    private static final int TOTAL_BITS = 64;

    /** self increase sequence number bits*/
    private int sequenceBits = 12;

    /** IDC number bits */
    private int idcBits = 10;

    /** IDC id */
    private int idcId = 1;

    /** Time slice millisecond */
    private int ticks = 1;

    /** epoch time */
    private long epochTime = 0;

    /** global lock */
    private Lock lock = new ReentrantLock();

    /** timestamp */
    private long lastTime = -1L;

    /** current sequence number */
    private long sequence = 0L;

    /** sequence mask */
    private final long sequenceMask;
    /** idc mask */
    private final long idcMask;
    /** time mask */
    private final long timeMask;
    /** time left shift bits */
    private final long timeLeftShiftBits;
    /** idc number left shift bits */
    private final long idcLeftShiftBit;


    public IDGenerator(int sequenceBits, int idcBits, int idcId, int ticks, long epochTime) {
        this.sequenceBits = sequenceBits;
        this.idcBits = idcBits;
        this.idcId = idcId;
        this.ticks = ticks;
        this.epochTime = epochTime;

        this.sequenceMask = -1L ^ (-1L << sequenceBits);
        this.idcMask = -1L ^ (-1L << (idcBits + sequenceBits)) ^ sequenceMask;
        this.timeMask =  -1L << (idcBits + sequenceBits);
        this.timeLeftShiftBits = sequenceBits + idcBits;
        this.idcLeftShiftBit = sequenceBits;

    }

    public long nextId() {

        try {
            lock.lock();

            long time = nextTime();

            if(time < lastTime) {
                logger.error("Clock is moving backward,Rejecting requests util:"+lastTime);
                throw new RuntimeException("Clock moved backward,time="+time + " lastTime="+lastTime);
            }

            if (time == lastTime) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    time = tillNextTime(lastTime);
                }
            } else {
                sequence = 0;
            }

            lastTime = time;

            return (time << timeLeftShiftBits) | (this.idcId << idcLeftShiftBit) | sequence;

        } finally {
            lock.unlock();
        }
    }

    private long tillNextTime(long lastTime) {
        long time = nextTime();
        while(time <= lastTime){
            time = nextTime();
        }
        return time;
    }

    public long getMaxIdcNumber(){
        long number = -1L ^ (-1L << idcBits);
        return number;
    }

    public long getMaxSequenceNumber(){
        long number = -1L ^ (-1L << sequenceBits);
        return number;
    }

    public int getLastYear(){

        return Epoch.yearNow() + getLifeTime();

    }

    public int getLifeTime(){

        int timeBits = TOTAL_BITS - idcBits - sequenceBits -1;
        long total = -1L ^ (-1L << timeBits);
        long totalMillis = total * ticks;
        long wasted = now() - epochTime;

        long ret = (totalMillis - wasted) / 1000 / 60 / 60 / 24 / 365;

        return (int)ret;
    }

    private long nextTime(){
        return nextMilliSecond() / ticks;
    }

    private long nextMilliSecond(){
        return now() - epochTime;
    }

    private long now(){
        return System.currentTimeMillis();
    }

    public String getIDGeneratorInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("MaxLiftTime:"+getLifeTime() + " Years\n");
        sb.append("Till:" + getLastYear() + "\n");
        sb.append("MaxSequenceNumber:"+getMaxSequenceNumber() + "\n");
        sb.append("MaxIDCNumber:"+getMaxIdcNumber() + "\n");
        sb.append("TimeAccuracy:"+this.ticks +" milliseconds\n");

        return sb.toString();
    }

}
