package org.nd4j.linalg.lossfunctions.impl;

import lombok.EqualsAndHashCode;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Created by susaneraly on 8/15/16.
 */
@EqualsAndHashCode
public class LossMAE extends LossL1norm {

    @Override
    public double computeScore(INDArray labels, INDArray preOutput, String activationFn, INDArray mask, boolean average) {

        double score = super.computeScore(labels,preOutput,activationFn,mask,average);
        score /= (labels.size(1));
        return score;
    }

    @Override
    public INDArray computeScoreArray(INDArray labels, INDArray preOutput, String activationFn, INDArray mask) {
        INDArray scoreArr = super.computeScoreArray(labels,preOutput,activationFn,mask);
        scoreArr.muli(1/scoreArr.size(1));
        return scoreArr;
    }

    @Override
    public INDArray computeGradient(INDArray labels, INDArray preOutput, String activationFn, INDArray mask) {
        INDArray gradients = super.computeGradient(labels,preOutput,activationFn,mask);
        gradients.muli(1.0/labels.size(1));
        return gradients;
    }

    @Override
    public String toString() {
        return "LossMAE()";
    }
}
