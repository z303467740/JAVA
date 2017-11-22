package edu.fjnu.math3;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// OperationFile.inputFiles();
		FCFS test1 = new FCFS("Test1");
		FCFS test2 = new FCFS("Test2");

		do {
			FCFS.addWaitQueue();
			test1.startUp();
			test2.startUp();
		} while (test1.getWaitQueueSize() != 0 || test2.getWaitQueueSize() != 0 || FCFS.TaskQueue.size() != 0);

	}

}
