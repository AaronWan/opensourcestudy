package com.opensource.distribution.custom;

/**
 * Created by Aaron on 16/8/14.
 */
public class FetchTokenManager {
    private LockRecorder lockRecorder = LockRecorder.RECORDER;
    private String processName;
    private boolean hasHeartbeat = false;

    public FetchTokenManager(String processName) {
        this.processName = processName;
        startHeartbeat();
    }

    public boolean canProcess() {
        if (processName.equals(lockRecorder.getProcess_name())) {
            return true;
        }
        return false;
    }

    private void startHeartbeat() {
        if (!hasHeartbeat) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        lockRecorder.setProperties(FetchTokenManager.this.processName, System.currentTimeMillis());
                    }
                }
            }.start();

            this.hasHeartbeat = true;
        } else {
            throw new RuntimeException("cant startheartbeat again");
        }
    }

}
