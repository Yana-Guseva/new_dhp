package org.eltech.ddm.associationrules;

import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.Arrays;
import java.util.List;

public class AssociationRulesMiningModel extends EMiningModel {

	private static final long serialVersionUID = 1L;

	public final static int TRANSACTION_LIST_SET = 1;
	public final static int ITEM_SET = 2;
	public final static int ASSOCIATION_RULE_SET = 3;

    public final static int CURRENT_ITEM = -1;
    public static final int[] INDEX_TRANSACTION_LIST_SET = {TRANSACTION_LIST_SET};
    public static final int[] INDEX_CURRENT_TRANSACTION_ITEM = {TRANSACTION_LIST_SET, CURRENT_ITEM};


//
//	protected ArrayList<AssociationRule> associationRuleSet = new ArrayList<AssociationRule>();
//
//	protected TransactionList transactionList = new TransactionList();
//
//	private int transactionCount = 0;
//
//	protected Map<String, Item> itemList = new HashMap<String, Item>();

	public AssociationRulesMiningModel(AssociationRulesFunctionSettings settings) throws MiningException {
		super(settings);
        sets.add(TRANSACTION_LIST_SET, new HashMapMiningModelElement("transactionListSet") {
            @Override
            protected String propertiesToString() {
                return "";
            }

            @Override
            public void merge(List<MiningModelElement> elements) throws MiningException {

            }
        });
        sets.add(ITEM_SET, new HashMapMiningModelElement("itemSet") {
            @Override
            protected String propertiesToString() {
                return "";
            }

            @Override
            public void merge(List<MiningModelElement> elements) throws MiningException {

            }
        });
        sets.add(ASSOCIATION_RULE_SET, new AssociationRuleSet("associationRuleSet") {
            @Override
            protected String propertiesToString() {
                return "";
            }

            @Override
            public void merge(List<MiningModelElement> elements) throws MiningException {

            }
        });
	}

//	public TransactionList getTransactionList() {
//		return transactionList;
//	}
//
//	public void setTransactionList(TransactionList transactionList) {
//		this.transactionList = transactionList;
//	}
//
//	public int getTransactionCount() {
//		if(transactionCount > 0)
//			return transactionCount;
//		else
//			return this.transactionList.size();
//	}
//
//	public List<AssociationRule> getAssociationRuleSet() {
//		return associationRuleSet;
//	}
//
//	public void setAssociationRuleSet(AssociationRuleSet associationRuleSet) {
//		this.associationRuleSet = associationRuleSet;
//	}
//
//	public Item getItem(String itemID) {
//		return itemList.get(itemID);
//	}
//
//	public Map<String, Item> getItems() {
//		return itemList;
//	}
//
//	public void addItem(Item item) {
//		if(! this.itemList.containsKey(item.getItemID()))
//			this.itemList.put(item.getItemID(), item);
//	}

	@Override
	public void initModel() throws MiningException {

	}

	public Item getItem(String itemId) throws MiningException {
		MiningModelElement items = getElement(EMiningModel.index(AssociationRulesMiningModel.ITEM_SET));
		return (Item) items.getElement(itemId);
	}

    public Item getItem(int transIndex, int itemIndex) throws MiningException {
        return (Item) getElement(index(AssociationRulesMiningModel.TRANSACTION_LIST_SET, transIndex, itemIndex));
    }

    public Transaction getTransaction(String transId) throws MiningException {
        MiningModelElement transactions = getElement(EMiningModel.index(AssociationRulesMiningModel.TRANSACTION_LIST_SET));
        return (Transaction) transactions.getElement(transId);
    }

    public Transaction getTransaction(int transIndex) throws MiningException {
        return (Transaction) getElement(EMiningModel.index(AssociationRulesMiningModel.TRANSACTION_LIST_SET, transIndex));
    }

    public AssociationRule getAssociationRule(String ruleId) throws MiningException {
        MiningModelElement associationRules = getElement(EMiningModel.index(AssociationRulesMiningModel.ASSOCIATION_RULE_SET));
        return (AssociationRule) associationRules.getElement(ruleId);
    }

    public MiningModelElement getAssociationRuleSet() throws MiningException {
        return getElement(EMiningModel.index(AssociationRulesMiningModel.ASSOCIATION_RULE_SET));
    }

    public int getCurrentTransactionIndex() {
        Integer index = currents.get(Arrays.toString(INDEX_TRANSACTION_LIST_SET));
        if(index == null)
            return -1;
        return index;
    }

    public int getCurrentItemIndex() {
        Integer index = currents.get(Arrays.toString(INDEX_CURRENT_TRANSACTION_ITEM));
        if(index == null)
            return -1;
        return index;
    }

//	public Object clone() {
//		AssociationRulesMiningModel o = null;
//		o = (AssociationRulesMiningModel)super.clone();
//
//		if(itemList != null){
//			o.itemList = new HashMap<String, Item>();
//			for (Item item: itemList.values())
//				o.itemList.put(item.getItemID(), (Item)item.clone());
//		}
//
//		if(transactionList != null){
//			o.transactionList = (TransactionList) transactionList.clone();
//		}
//
//		if (associationRuleSet != null) {
//			o.associationRuleSet = new ArrayList<AssociationRule>();
//			for (AssociationRule assRule: associationRuleSet){
//				o.associationRuleSet.add(assRule);
//			}
//		}
//
//		o.currentItem = currentItem;
//		o.currentItemSet = currentItemSet;
//		o.currentTransaction = currentTransaction;
//		o.transactionCount = transactionCount;
//
//		return o;
//	}
//
//	@Override
//	public ArrayList<EMiningModel> split(int handlerCount)
//			throws MiningException {
//		transactionCount = transactionList.size();
//		ArrayList<EMiningModel> models = new ArrayList<EMiningModel>(handlerCount);
//		for(int i = 0; i < handlerCount; i++){
//				models.add((EMiningModel)clone());
//		}
//
//		if((transactionList != null) && (transactionList.size() > 0)){
//			int iModel = -1;
//			int count = Math.round(transactionList.size()/handlerCount) + 1;
//			int i = count;
//			for(Transaction tran : transactionList){
//				if(i == count){
//					iModel++;
//					i = 0;
//					((AssociationRulesMiningModel)models.get(iModel)).getTransactionList().clear();
//				}
//				((AssociationRulesMiningModel)models.get(iModel)).getTransactionList().add(tran);
//				i++;
//			}
//		}
//
////		if ((associationRuleSet != null) && (associationRuleSet.size() > 0)) {
////			int iModel = -1;
////			int count = Math.round(associationRuleSet.size()/handlerCount) + 1;
////			int i = count;
////			for (AssociationRule assRule: associationRuleSet){
////				if(i == count){
////					iModel++;
////					i = 0;
////					models.add((EMiningModel) clone());
////					((AssociationRulesMiningModel)models.get(iModel)).getAssociationRuleSet().clear();
////				}
////				((AssociationRulesMiningModel)models.get(iModel)).getAssociationRuleSet().add(assRule);
////				i++;
////			}
////		}
//
//		return models;
//	}
//
//	@Override
//	public void join(List<EMiningModel> joinModels) throws MiningException {
//		for(EMiningModel mm: joinModels){
//			if(mm == this)
//				continue;
//			AssociationRulesMiningModel armm = (AssociationRulesMiningModel) mm;
//			for(AssociationRule rule: armm.getAssociationRuleSet()){
//				if(!associationRuleSet.contains(rule)){
//					associationRuleSet.add(rule);
//				}
//			}
//			for(Transaction tran: armm.getTransactionList()){
//				if(!transactionList.contains(tran)){
//					transactionList.add(tran);
//				}
//			}
//
//			itemList.putAll(armm.getItems());
//		}
//		transactionCount = transactionList.size();
//	}


}
