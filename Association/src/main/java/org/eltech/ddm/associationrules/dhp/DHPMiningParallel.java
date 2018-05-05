package org.eltech.ddm.associationrules.dhp;

import org.eltech.ddm.associationrules.apriori.steps.LargeItemSetListsCycleStep;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningErrorCode;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.*;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.List;

public class DHPMiningParallel extends MiningParallel {
    public DHPMiningParallel(EMiningFunctionSettings settings, MemoryType memory, MiningBlock block) throws MiningException {
        super(settings, memory, block);
    }

    @Override
    protected EMiningModel execute(MiningInputStream dataSet, EMiningModel model) throws MiningException {

        if(isDataParallelize){
            if(!(blocks[0] instanceof MiningLoop))
                throw new MiningException(MiningErrorCode.PARALLEL_EXECUTION_ERROR);

            MiningLoop block = (MiningLoop)blocks[0];

            int startPos = 0;
            {
                int[] index = ((MiningLoopElement) block).getIndexSet();
                MiningModelElement elem = model.getElement(index);
                int countElement = elem.size() / handlersNumber;
                int mod = elem.size() % handlersNumber;
                blocks[0] = new LargeItemSetListsCycleStep(functionSettings, index, startPos, countElement + mod, block.getIteration());
                startPos += countElement + mod;
                for (int i = 1; i < handlersNumber; i++) {
                    blocks[i] = new LargeItemSetListsCycleStep(functionSettings, index, startPos, countElement, block.getIteration());
                    startPos += countElement;
                }

            }
        }

        List<EMiningModel> models = fork(model);

        if(memoryType == MemoryType.distributed) {
            model.join(models);
            return model;
        } else {
            return models.get(0);
        }
    }
}
