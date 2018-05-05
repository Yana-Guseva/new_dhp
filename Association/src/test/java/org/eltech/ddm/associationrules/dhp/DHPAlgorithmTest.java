package org.eltech.ddm.associationrules.dhp;

import com.univocity.parsers.csv.CsvParserSettings;
import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associationrules.apriori.AprioriAlgorithmSettings;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.inputdata.file.MiningArffStream;
import org.eltech.ddm.inputdata.file.MiningCsvStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm;
import org.eltech.ddm.miningcore.miningdata.ELogicalData;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DHPAlgorithmTest {
    private static final String ALGO_NAME = "DHPAlgorithm";
    private static final String ALGO_PATH = "org.eltech.ddm.associationrules.dhp.DHPAlgorithm";

    protected MiningInputStream inputData;
//    protected MiningArffStream inputData;
    private AssociationRulesFunctionSettings miningSettings;
    protected MiningAlgorithm algorithm;
    protected DHPModel  model;

    @Before
    public void setUp() throws Exception {
//        CsvParserSettings settings = new CsvParserSettings();
//        settings.setDelimiterDetectionEnabled(true);
//        settings.setHeaderExtractionEnabled(true);
//        settings.setNormalizeLineEndingsWithinQuotes(true);


    }

    @Test
    public void test() throws MiningException {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setDelimiterDetectionEnabled(true);
        settings.setHeaderExtractionEnabled(true);
        settings.setNormalizeLineEndingsWithinQuotes(true);
        settings.setReadInputOnSeparateThread(false);

//        this.inputData = new MiningArffStream("../data/arff/association/" + "T_200" + ".arff");
        this.inputData = new MiningCsvStream("transactions_1" + ".csv", settings);
//        this.inputData = new MiningCsvStream("T_200" + ".csv", settings);
        createMiningSettings();
        // Create and tuning algorithm
        algorithm = new DHPAlgorithm(miningSettings);

        //Building modl
        model = (DHPModel) algorithm.buildModel(inputData);

        verifyModel();
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

    private void createMiningSettings() throws MiningException {
        ELogicalData logicalData = inputData.getLogicalData();

        AprioriAlgorithmSettings algorithmSettings = new AprioriAlgorithmSettings();
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
}
