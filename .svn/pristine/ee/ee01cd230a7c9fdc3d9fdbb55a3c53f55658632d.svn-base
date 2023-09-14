package com.cmu.base.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Named;

@Named(value = "trace")
@SessionScoped
public class EcouteurTrace extends BaseBean implements PhaseListener {

    private static final Character OK = '1';
    private static final Character NOACCES = '0';
    private static final Character NOTFOUND = '4';

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public static Character getOK() {
        return OK;
    }

    public static Character getNOACCES() {
        return NOACCES;
    }

    public static Character getNOTFOUND() {
        return NOTFOUND;
    }

}
