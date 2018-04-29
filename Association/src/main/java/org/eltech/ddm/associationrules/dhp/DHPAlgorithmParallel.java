package org.eltech.ddm.associationrules.dhp;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.AssociationRulesMiningModel;
import org.eltech.ddm.associationrules.apriori.steps.*;
import org.eltech.ddm.associationrules.dhp.steps.*;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.*;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

import static org.eltech.ddm.miningcore.miningmodel.EMiningModel.index;

public class DHPAlgorithmParallel extends DHPAlgorithm {
    public DHPAlgorithmParallel(EMiningFunctionSettings miningSettings) throws MiningException {
        super(miningSettings);
    }

    @Override
    public EMiningModel createModel(MiningInputStream inputStream) throws MiningException {
        EMiningModel resultModel = new DHPModel((AssociationRulesFunctionSettings) miningSettings);

        return resultModel;
    }

    @Override
    protected void initBlocks() throws MiningException {
        blocks = new MiningSequence(miningSettings,
                new MiningLoopVectors(miningSettings,
                        new BuildTransaction(miningSettings), new SetTransactionCountStep(miningSettings)),
                new MiningLoopElement(miningSettings, index(AssociationRulesMiningModel.INDEX_TRANSACTION_LIST_SET),
                        new MiningLoopElement(miningSettings, index(AssociationRulesMiningModel.INDEX_CURRENT_TRANSACTION_ITEM),
                                new Calculate1ItemSetSupportStep(miningSettings),
                                new CreateLarge1ItemSetStep(miningSettings))),
                new MiningLoopElement(miningSettings, index(AssociationRulesMiningModel.INDEX_TRANSACTION_LIST_SET),
                        new CreateHashTable(miningSettings)),
                new DHPMiningParallel(miningSettings, MemoryType.distributed,
                        new LargeItemSetListsCycleStep(miningSettings,
                                new PruningStep(miningSettings),
                                new MiningLoopElement(miningSettings, index(AssociationRulesMiningModel.INDEX_TRANSACTION_LIST_SET),
                                        new PruneTransactionListStep(miningSettings),
                                        new IsThereCurrentTransaction(miningSettings, new CreateHashTable(miningSettings))))),
                new LargeItemSetListsCycleStep(miningSettings, 1,
                        new KLargeItemSetsCycleStep(miningSettings, index(DHPModel.INDEX_CURRENT_LARGE_ITEM_SET),
                                new LargeItemSetItemsCycleStep(miningSettings, index(DHPModel.INDEX_CURRENT_ITEM_LARGE_ITEM_SET),
                                        new GenerateAssosiationRuleStep(miningSettings)))), new PrintStep(miningSettings));

        blocks.addListenerExecute(new BlockExecuteTimingListner());
    }
}
