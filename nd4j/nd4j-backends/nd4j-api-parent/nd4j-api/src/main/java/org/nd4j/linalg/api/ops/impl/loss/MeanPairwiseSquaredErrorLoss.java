/*******************************************************************************
 * Copyright (c) 2015-2019 Skymind, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

package org.nd4j.linalg.api.ops.impl.loss;

import org.nd4j.autodiff.loss.LossReduce;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Arrays;
import java.util.List;

/**
 * Mean Pairwise Squared Error Loss
 *
 * @author Paul Dubs
 */
public class MeanPairwiseSquaredErrorLoss extends BaseLoss {
    public MeanPairwiseSquaredErrorLoss(SameDiff sameDiff, LossReduce lossReduce, SDVariable predictions, SDVariable weights, SDVariable labels){
        super(sameDiff, lossReduce, predictions, weights, labels);
    }

    public MeanPairwiseSquaredErrorLoss(INDArray labels, INDArray predictions, INDArray weights, LossReduce lossReduce){
        super(lossReduce, predictions, weights, labels);
    }

    public MeanPairwiseSquaredErrorLoss(){ }

    @Override
    public String opName() {
        return "mean_pairwssqerr_loss";
    }

    @Override
    public List<SDVariable> doDiff(List<SDVariable> grad){
        //No external gradient
        //Args are: predictions, weights, label
        SDVariable[] grads = f().lossMeanPairwiseSquaredErrorBp(arg(2), arg(0), arg(1), lossReduce);
        return Arrays.asList(grads);
    }
}
