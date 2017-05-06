package org.nd4j.linalg.learning.config;

import lombok.Builder;
import lombok.Data;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.learning.AdamUpdater;
import org.nd4j.linalg.learning.GradientUpdater;

/**
 * Created by Alex on 06/05/2017.
 */
@Data
@Builder
public class Adam implements IUpdater {

    public static final double DEFAULT_LEARNING_RATE = 1e-3;
    public static final double DEFAULT_ADAM_EPSILON = 1e-8;
    public static final double DEFAULT_ADAM_BETA1_MEAN_DECAY = 0.9;
    public static final double DEFAULT_ADAM_BETA2_VAR_DECAY = 0.999;

    private double learningRate = 1e-3; // learning rate
    private double beta1 = DEFAULT_ADAM_BETA1_MEAN_DECAY; // gradient moving avg decay rate
    private double beta2 = DEFAULT_ADAM_BETA2_VAR_DECAY; // gradient sqrd decay rate
    private double epsilon = DEFAULT_ADAM_EPSILON;

    public Adam(){
        this(DEFAULT_LEARNING_RATE, DEFAULT_ADAM_BETA1_MEAN_DECAY, DEFAULT_ADAM_BETA2_VAR_DECAY, DEFAULT_ADAM_EPSILON);
    }

    public Adam(double learningRate, double beta1, double beta2, double epsilon){
        this.learningRate = learningRate;
        this.beta1 = beta1;
        this.beta2 = beta2;
        this.epsilon = epsilon;
    }

    @Override
    public long stateSize(long numParams) {
        return 2*numParams;
    }

    @Override
    public void applySchedules(int iteration, double newLearningRate) {
        this.learningRate = newLearningRate;
    }

    @Override
    public GradientUpdater instantiate(INDArray viewArray, boolean initializeViewArray) {
        AdamUpdater u =  new AdamUpdater(this);
        u.setStateViewArray(viewArray, viewArray.shape(), viewArray.ordering(), initializeViewArray);
        return u;
    }
}
