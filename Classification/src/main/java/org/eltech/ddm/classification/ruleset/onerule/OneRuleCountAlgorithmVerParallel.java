package org.eltech.ddm.classification.ruleset.onerule;

import org.eltech.ddm.classification.ClassificationFunctionSettings;
import org.eltech.ddm.classification.ruleset.onerule.steps.*;
import org.eltech.ddm.classification.steps.DecisionCurrentAttributIsNotTarget;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.*;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

public class OneRuleCountAlgorithmVerParallel extends MiningAlgorithm {

	public OneRuleCountAlgorithmVerParallel(EMiningFunctionSettings miningSettings)
			throws MiningException {
		super(miningSettings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EMiningModel createModel(MiningInputStream inputStream) throws MiningException {
		EMiningModel resultModel = new OneRuleCountMiningModel(miningSettings);

		return resultModel;
	}

    /**
     * Initialization of 1R algorithm's block.
     */
	@Override
	protected void initBlocks() throws MiningException {

		int indexTargetAttr = miningSettings.getLogicalData().
				getAttributeIndex(((ClassificationFunctionSettings)miningSettings).getTarget());
		int[] indexTargetValues =  {EMiningModel.ATTRIBUTE_SET, indexTargetAttr};

		blocks = new MiningSequence(miningSettings,
				new MiningParallel( miningSettings, MemoryType.distributed,
					new MiningLoopElement(miningSettings, EMiningModel.INDEX_ATTRIBUTE_SET,
						//new MiningDecisionElement(miningSettings, ruleNoTarget,	EMiningModel.INDEX_CURRENT_ATTRIBUTE,
						new DecisionCurrentAttributIsNotTarget(miningSettings,
								new MiningParallel( miningSettings, MemoryType.shared,
									new MiningLoopVectors(miningSettings,
										new IncrementCorrectVectorsCount(miningSettings)))))),
				new MiningLoopElement(miningSettings, EMiningModel.INDEX_ATTRIBUTE_SET,
						new DecisionCurrentAttributIsNotTarget(miningSettings,
								new MiningParallel( miningSettings, MemoryType.shared,
										new MiningLoopElement(miningSettings, EMiningModel.INDEX_CURRENT_ATTRIBUTE,
												new MiningLoopElement(miningSettings, indexTargetValues,
														new SelectMaxCorrectVectorsCount(miningSettings)),
												new MiningParallel( miningSettings, MemoryType.shared,
														new AddBestOneRule(miningSettings),
														new SummCorrectVectorsCount(miningSettings)))),
								new MiningParallel( miningSettings, MemoryType.distributed,
										new SelectBestRuleSet(miningSettings),
										new RemoveAttributeRuleSet(miningSettings))
						)
				)
		);



		blocks.addListenerExecute(new BlockExecuteTimingListner());
	}


}
