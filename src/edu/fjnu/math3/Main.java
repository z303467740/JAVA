package edu.fjnu.math3;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// OperationFile.inputFiles(); //Ð´ÈëÎÄ¼þ
		SJFSeize test1 = new SJFSeize("Test1");
		SJFSeize test2 = new SJFSeize("Test2");

		do {
			SJFSeize.addWaitQueue();
			test1.startUp();
			test2.startUp();
		} while (test1.getWaitQueueSize() != 0 || test2.getWaitQueueSize() != 0 || SJFSeize.TaskQueue.size() != 0);

	}

}
