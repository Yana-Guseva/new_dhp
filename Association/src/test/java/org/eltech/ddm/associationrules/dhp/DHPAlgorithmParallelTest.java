package org.eltech.ddm.associationrules.dhp;

import org.eltech.ddm.handlers.ExecutionSettings;
import org.eltech.ddm.handlers.thread.MultiThreadedExecutionEnvironment;
import org.eltech.ddm.handlers.thread.ThreadSettings;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningAlgorithmSettings;
import org.eltech.ddm.miningcore.miningtask.EMiningBuildTask;
import org.junit.Before;

import static org.junit.Assert.fail;

public class DHPAlgorithmParallelTest extends DHPModelTest {
    private final int NUMBER_HANDLERS = 2;

    protected EMiningAlgorithmSettings algorithmSettings;

    @Before
    public void setUp() throws Exception {

        algorithmSettings = new EMiningAlgorithmSettings();
        // Create and tuning algorithm settings
        algorithmSettings.setName(DHPAlgorithmParallel.class.getSimpleName());
        algorithmSettings.setClassname(DHPAlgorithmParallel.class.getName());
        algorithmSettings.setAlgorithm("DHP");
        algorithmSettings.setNumberHandlers(NUMBER_HANDLERS);
    }

    @org.junit.Test
    public void test() {
        try {
            setInputData();
            setMiningSettings(algorithmSettings);

            EMiningBuildTask buildTask = createBuidTask();

            model = (DHPModel) buildTask.execute();

            verifyModel();

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    private EMiningBuildTask createBuidTask() throws MiningException{
        ExecutionSettings executionSettings = new ThreadSettings();
        executionSettings.setDataSet(inputData);

        MiningAlgorithm algorithm = new DHPAlgorithmParallel(miningSettings);
        MultiThreadedExecutionEnvironment environment = new MultiThreadedExecutionEnvironment(executionSettings, algorithm);
        algorithmSettings.setEnvironment(environment);

        EMiningBuildTask buildTask = new EMiningBuildTask();
        buildTask.setInputStream(inputData);
        buildTask.setMiningAlgorithm(algorithm);
        buildTask.setMiningSettings(miningSettings);
        buildTask.setExecutionEnvironment(environment);

        return buildTask;
    }
}
