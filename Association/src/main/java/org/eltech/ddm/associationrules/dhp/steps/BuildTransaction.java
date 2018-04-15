package org.eltech.ddm.associationrules.dhp.steps;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.AssociationRulesMiningModel;
import org.eltech.ddm.associationrules.Item;
import org.eltech.ddm.associationrules.Transaction;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.inputdata.MiningVector;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningBlock;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import static org.eltech.ddm.miningcore.miningmodel.EMiningModel.index;

public class BuildTransaction extends MiningBlock {

    private final String itemIDsAttributeName;
    private final String transactionIDsAttributeName;

    /**
     * Constructor of algorithm's calculation step (not using data set)
     *
     * @param settings - settings for build model
     */
    public BuildTransaction(EMiningFunctionSettings settings) throws MiningException {
        super(settings);

        itemIDsAttributeName = ((AssociationRulesFunctionSettings) settings).getItemIDsAttributeName();
        transactionIDsAttributeName = ((AssociationRulesFunctionSettings) settings).getTransactionIDsAttributeName();
    }

    @Override
    protected EMiningModel execute(MiningInputStream data, EMiningModel model) throws MiningException {
        AssociationRulesMiningModel modelA = (AssociationRulesMiningModel) model;

        MiningVector mv = data.getVector(model.getCurrentVectorIndex());
        String transId = String.valueOf(mv.getValue(transactionIDsAttributeName));
        String itemId = String.valueOf(mv.getValue(itemIDsAttributeName));

//        System.out.println(transId + " " + itemId);

        Item item = (Item) createItem(itemId, modelA);
        Transaction transaction = createTransaction(transId, item, modelA);

        return model;
    }

    protected Transaction createTransaction(String transId, Item item, AssociationRulesMiningModel model) throws MiningException {
        Transaction transaction = model.getTransaction(transId);
        if (transaction == null) {
            transaction = new Transaction(transId);
            model.addElement(index(AssociationRulesMiningModel.TRANSACTION_LIST_SET), transaction);
        }

        if (!transaction.getItemIDList().contains(item.getItemID())) { // transaction has not duplicated items
            transaction.getItemIDList().add(item.getItemID());
            transaction.addItem(item);
        }

        return transaction;
    }

    protected MiningModelElement createItem(String itemId, AssociationRulesMiningModel model) throws MiningException {
        Item item = model.getItem(itemId);
        if (item == null) {
            item = new Item(itemId);
            model.addElement(index(AssociationRulesMiningModel.ITEM_SET), item);
        }
        return item;
    }
}
