package edu.fjnu.math3;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// OperationFile.inputFiles();
		SJF test1 = new SJF("Test1");
		SJF test2 = new SJF("Test2");

		do {
			SJF.addWaitQueue();
			test1.startUp();
			test2.startUp();
		} while (test1.getWaitQueueSize() != 0 || test2.getWaitQueueSize() != 0 || SJF.TaskQueue.size() != 0);

	}

}
