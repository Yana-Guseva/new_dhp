/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.eltech.ddm.classification.naivebayes;
import org.eltech.ddm.classification.naivebayes.steps.FindProbabilityOfAttributeValue;
import org.eltech.ddm.classification.naivebayes.steps.FindProbabilityOfTargetValue;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.algorithms.*;
import org.eltech.ddm.miningcore.MiningException;
//import org.eltech.ddm.classification.naivebayes.steps.FindProbabilityOfAttributeValue;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;
//import org.eltech.ddm.classification.naivebayes.steps.FindProbabilityOfTargetValue;
import org.eltech.ddm.classification.steps.DecisionCurrentAttributIsNotTarget;

/**
 *
 * @author Ivan Kholod
 */
public class NaiveBayesAlgorithmHorParallel extends NaiveBayesAlgorithm  {

	public NaiveBayesAlgorithmHorParallel(
			EMiningFunctionSettings miningSettings) throws MiningException {
		super(miningSettings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EMiningModel createModel(MiningInputStream inputStream) throws MiningException {
		EMiningModel resultModel = new NaiveBayesModel((EMiningFunctionSettings) miningSettings);
		
//		(resultModel).setVectorCount(inputStream.getVectorsNumber());

		return resultModel;
	}

	/**
	 * Initialization of Naive Bayes algorithm's block.
	 * 
	 */
	@Override
	protected void initBlocks() throws MiningException {
		blocks = new MiningSequence(miningSettings,
				new MiningParallel( miningSettings, MemoryType.shared,
						new MiningLoopVectors(miningSettings,
								new FindProbabilityOfTargetValue(miningSettings),
								new MiningParallel( miningSettings, MemoryType.shared,
										new MiningLoopElement(miningSettings, EMiningModel.INDEX_ATTRIBUTE_SET,
											new FindProbabilityOfAttributeValue(miningSettings))))));

		blocks.addListenerExecute(new BlockExecuteTimingListner());

	}
}
