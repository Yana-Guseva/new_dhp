package org.eltech.ddm.associationrules.dhp;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.inputdata.file.MiningArffStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningdata.ELogicalData;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningAlgorithmSettings;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;
import org.junit.Assert;

public class DHPModelTest {
    protected String dataSets[] = {"T_200", "T_2000", "T_20000", "I_5", "I_10", "I_15", "I_10_20", "I_10_30", "I_10_50"};

    private static final String ALGO_NAME = "DHPAlgorithm";
    private static final String ALGO_PATH = "org.eltech.ddm.associationrules.dhp.DHPAlgorithm";
    protected AssociationRulesFunctionSettings miningSettings;
    protected MiningInputStream inputData;
    protected org.eltech.ddm.associationrules.apriori.dhp.DHPMiningModel miningModel;
    protected DHPModel  model;

    // ==== Methods for data attributes Weather Nominal ===============
    protected void setInputData() throws MiningException {
        // Load input data
        inputData = new MiningArffStream("../data/arff/association/T_200.arff");
    }

    protected void setMiningSettings(EMiningAlgorithmSettings algorithmSettings) throws MiningException {
        ELogicalData logicalData = inputData.getLogicalData();
        algorithmSettings.setName(ALGO_NAME);
        algorithmSettings.setClassname(ALGO_PATH);

        miningSettings = new AssociationRulesFunctionSettings(logicalData);
        miningSettings.setTransactionIDsArributeName("transactId");
        miningSettings.setItemIDsArributeName("itemId");
        miningSettings.setMinConfidence(0.01);
        miningSettings.setMinSupport(0.01);
        miningSettings.setAlgorithmSettings(algorithmSettings);
        miningSettings.verify();

    }

    protected void verifyModel() throws MiningException {
        Assert.assertNotNull(model);
        System.out.println("AssociationRuleSet: number of rules = " + model.getAssociationRuleSet().size());
        int l = 0;
        MiningModelElement largeItemSets = model.getLargeItemSets();
        for (int i = 0; i < largeItemSets.size(); i++) {
            System.out.println("Level " + l++ + " number of rules " + largeItemSets.getElement(i).size());

        }
    }
}
