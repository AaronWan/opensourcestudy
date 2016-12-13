package com.opensource.distribution.custom;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Aaron on 16/8/14.
 */
public enum LockRecorder {
    RECORDER;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    LockRecorder() {
    }

    public void setProperties(String process_name, long timeStamp) {
        if (getProcess_name() == null || process_name.equals(getProcess_name()) || (timeStamp - getTimeStamp()) >= 1000 * 10) {
            if (getProcess_name() == null || !process_name.equals(getProcess_name())) {
                this.setProcess_name(process_name);
            }
            this.setTimeStamp(timeStamp);
        }
    }

    public long getTimeStamp() {
        String timestamp = readLine("timestamp");
        if (timestamp == null) {
            return 0;
        }
        return Long.valueOf(timestamp);
    }

    private void setTimeStamp(long timeStamp) {
        setLine("timestamp", timeStamp);
    }

    public String getProcess_name() {
        return readLine("process_name");
    }

    public void setLine(String fileName, Object value) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(value.toString().getBytes());
        } catch (Exception e) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception ee) {

                }
            }
        }
    }

    public String readLine(String filename) {
        String process_name = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            process_name = br.readLine();
        } catch (Exception e) {

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {

                }
            }
        }
        return process_name;
    }

    private void setProcess_name(String process_name) {
        setLine("process_name", process_name);
    }
}
