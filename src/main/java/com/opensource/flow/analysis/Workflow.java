package com.opensource.flow.analysis;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Aaron on 16/02/2017.
 */
public class Workflow {
    private List<Transition> transitions= Lists.newArrayList();
    public Workflow addTranstion(Object from,Object to){
        transitions.add(new Transition(from,to));
        return this;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
