package org.eltech.ddm.classification.naivebayes;

import static org.junit.Assert.*;


import org.eltech.ddm.classification.ClassificationLoadlTest;
import org.eltech.ddm.classification.ClassificationMiningModel;
import org.eltech.ddm.handlers.ExecutionSettings;
import org.eltech.ddm.handlers.thread.MemoryType;
import org.eltech.ddm.handlers.thread.MultiThreadedExecutionEnvironment;
import org.eltech.ddm.handlers.thread.ThreadSettings;
import org.eltech.ddm.inputdata.DataSplitType;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm;
import org.eltech.ddm.miningcore.miningfunctionsettings.DataProcessingStrategy;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningAlgorithmSettings;
import org.eltech.ddm.miningcore.miningfunctionsettings.MiningModelProcessingStrategy;
import org.eltech.ddm.miningcore.miningtask.EMiningBuildTask;
import org.junit.Ignore;
import org.junit.Test;

public class NaiveBayesAttrsCycleParallelAlgorithmLoadTest extends ClassificationLoadlTest {
	private final int NUMBER_HANDLERS = 2;
	protected MiningAlgorithm algorithm;

	@Ignore
	@Test
	public void testSharedSDSM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.shared);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (shared SDSM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testSharedMDSM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.shared);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (shared MDSM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	@Test
	public void testSharedSDMM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.shared);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (shared SDMM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	@Test
	public void testSharedMDMM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.shared);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (shared MDMM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	@Test
	public void testDistrSDSM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.distributed);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (distributed SDSD) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	@Test
	public void testDistrMDSM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.distributed);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (distributed MDSD) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SingleMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Ignore
	@Test
	public void testDistrSDMM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.distributed);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (distributed SDMM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SingleDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testDistrMDMM(){
		ThreadSettings executionSettings = new ThreadSettings();
		executionSettings.setNumberHandlers(NUMBER_HANDLERS);
		executionSettings.setMemoryType(MemoryType.distributed);

		MultiThreadedExecutionEnvironment environment;
		try {
			environment = new MultiThreadedExecutionEnvironment(executionSettings);


			System.out.println("----- NaiveBayesAttrsCycleParallelAlgorithm (distributed MDMM) -------");

			for(int i=0; i < dataSets.length; i++){
				setSettings(i);
				miningSettings.setAlgorithmSettings(new EMiningAlgorithmSettings());
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.attributes);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel);

		        algorithm = new NaiveBayesAttrsCycleParallelAlgorithm(miningSettings);
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm);
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (ClassificationMiningModel) buildTask.execute();
				System.out.println("Finish algorithm");


				verifyModel();

			}

		} catch (MiningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
