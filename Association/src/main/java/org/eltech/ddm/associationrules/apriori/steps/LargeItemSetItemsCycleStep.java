package org.eltech.ddm.associationrules.apriori.steps;


import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associationrules.dhp.DHPModel;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningBlock;
import org.eltech.ddm.miningcore.algorithms.MiningLoop;
import org.eltech.ddm.miningcore.algorithms.MiningSequence;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

import static org.eltech.ddm.miningcore.miningmodel.EMiningModel.index;


public class LargeItemSetItemsCycleStep extends MiningLoop {

    private final int[] indexSet;

    private final int startPosition;

    private final int countElement;

    public LargeItemSetItemsCycleStep(EMiningFunctionSettings settings, int[] indexSet, MiningBlock... blocks) throws MiningException {
        super(settings);
        this.indexSet = indexSet;
        startPosition = 0;
        countElement = -1;
        iteration = new MiningSequence(settings, blocks);
    }

    @Override
    protected EMiningModel initLoop(EMiningModel model) throws MiningException {
        model.setCurrentElement(indexSet, startPosition);
        return model;
    }

    @Override
    protected boolean conditionLoop(EMiningModel model) throws MiningException {
        DHPModel modelA = (DHPModel) model;
        return modelA.getCurrentItemLargeItemsetIndex() <
                ((ItemSet) modelA.getElement(index(DHPModel.HASH_TABLE_SET, modelA.getCurrentHashTableIndex(), modelA.getCurrentLargeItemsetIndex())))
                        .getItemIDList().size();
    }

    @Override
    protected EMiningModel beforeIteration(EMiningModel model) throws MiningException {
        return model;
    }

    @Override
    protected EMiningModel afterIteration(EMiningModel model) throws MiningException {
        model.nextCurrElement(indexSet);
        return model;
    }

//    @Override
//	protected EMiningModel initLoop(MiningInputStream inputData, EMiningModel model) throws MiningException {
//
//		//setStateParameter(model, AprioriMiningModel.NAME_CURRENT_ITEM, 0);
//		((AprioriMiningModel) model).setCurrentItem(0);
//
//		return model;
//	}
//
//	@Override
//	protected boolean conditionLoop(MiningInputStream inputData, EMiningModel model) throws MiningException {
////		return (((Integer)getStateParameter(model, AprioriMiningModel.NAME_CURRENT_ITEM)) <
////				((AprioriMiningModel) model).getLargeItemSetsList().get(
////						(Integer)getStateParameter(model, AprioriMiningModel.NAME_CURRENT_LARGE_ITEM_SETS)).get(
////								(Integer)getStateParameter(model, AprioriMiningModel.NAME_CURRENT_ITEM_SET)).getItemIDList().size());
//
//		return ((((AprioriMiningModel) model).getCurrentItem()) <
//				((AprioriMiningModel) model).getLargeItemSetsList().get(
//						((AprioriMiningModel) model).getCurrentLargeItemSets()).get(
//						((AprioriMiningModel) model).getCurrentItemSet()).getItemIDList().size());
//
//	}
//
//	@Override
//	protected EMiningModel beforeIteration(MiningInputStream inputData, EMiningModel model) throws MiningException {
//
//		return model;
//	}
//
//	@Override
//	protected EMiningModel afterIteration(MiningInputStream inputData, EMiningModel model) throws MiningException {
//		((AprioriMiningModel) model).setCurrentItem(((AprioriMiningModel) model).getCurrentItem() + 1);
////		setStateParameter(model, AprioriMiningModel.NAME_CURRENT_ITEM,
////				(Integer)getStateParameter(model, AprioriMiningModel.NAME_CURRENT_ITEM) + 1);
//
//		return model;
//	}

}
