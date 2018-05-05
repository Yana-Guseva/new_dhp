package org.eltech.ddm.associationrules.apriori.steps;

import org.eltech.ddm.associationrules.*;
import org.eltech.ddm.associationrules.apriori.AprioriMiningModel;
import org.eltech.ddm.associationrules.dhp.DHPModel;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningBlock;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.List;

import static org.eltech.ddm.miningcore.miningmodel.EMiningModel.index;

public class CreateLarge1ItemSetStep extends MiningBlock {

	final protected double minSupport;

	public CreateLarge1ItemSetStep(EMiningFunctionSettings settings) throws MiningException {
		super(settings);
		minSupport = ((AssociationRulesFunctionSettings)settings).getMinSupport();
	}

	@Override
	protected EMiningModel execute(MiningInputStream inputData, EMiningModel model) throws MiningException {
		DHPModel modelA = (DHPModel) model;
		HashMapMiningModelElement hashTable = modelA.getHashTable(modelA.getCurrentHashTableIndex() + 1);
		if (hashTable == null) {
			hashTable = new HashTable(String.valueOf(modelA.getCurrentHashTableIndex() + 1));
            model.addElement(index(DHPModel.HASH_TABLE_SET), hashTable);
		}
//		if (hashTable.size() == 0) {
//            modelA.addElement(index(DHPModel.HASH_TABLE_SET, 0), new ItemSets("1"));
//        }
		Item item = modelA.getItem(modelA.getCurrentTransactionIndex(), modelA.getCurrentItemIndex());
		double supp = calcSupport(item, modelA);
		if(supp >= minSupport){
            ItemSet itemSet = (ItemSet) hashTable.getElement(item.getID());
            if (itemSet == null) {
                itemSet = new ItemSet(item);
                hashTable.put(item.getID(), itemSet);
            }
            itemSet.setSupportCount(item.getSupportCount());
		}

//        MiningModelElement largeItemSetsSet = modelA.getElement(index(AprioriMiningModel.LARGE_ITEM_SETS_SET));
//        if (largeItemSetsSet.size() == 0) {
//            modelA.addElement(index(AprioriMiningModel.LARGE_ITEM_SETS_SET), new ItemSets("0"));
//        }
//        ItemSets oneItemsets = (ItemSets) modelA.getElement(index(AprioriMiningModel.LARGE_ITEM_SETS_SET, 0));
//        Item item = modelA.getItem(modelA.getCurrentTransactionIndex(), modelA.getCurrentItemIndex());
//		double supp = calcSupport(item, modelA);
//        if(supp >= minSupport){
//			ItemSet oneItemSet = new ItemSet(item);
//			oneItemSet.setSupportCount(item.getSupportCount());
//			if(oneItemsets.contains(oneItemSet))
//				oneItemsets.remove(oneItemSet);
//			modelA.addElement(index(AprioriMiningModel.LARGE_ITEM_SETS_SET, 0), oneItemSet);
//		}

 		return modelA;
	}


	protected double calcSupport(Item item, AprioriMiningModel modelA) throws MiningException {
		// TODO Auto-generated method stub
		return ((double)item.getSupportCount()) /
                ((double)modelA.getElement(index(AssociationRulesMiningModel.TRANSACTION_LIST_SET)).size());
	}

}