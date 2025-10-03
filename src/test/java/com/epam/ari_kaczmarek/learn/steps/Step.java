package com.epam.ari_kaczmarek.learn.steps;

public abstract class Step {
    protected Step nextStep;
    
    protected abstract void performStep();
    
    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }

    public void run() {
        performStep();
        if (nextStep != null) {
            nextStep.run();
        }
    }
}
