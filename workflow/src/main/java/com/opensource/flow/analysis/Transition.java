package com.opensource.flow.analysis;


/**
 * Created by Aaron on 16/02/2017.
 */
public class Transition {
    private Object from;
    private Object to;
    public Transition(Object from,Object to){
        this.from=from;
        this.to=to;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }
}
