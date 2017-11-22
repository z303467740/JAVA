package edu.fjnu.math3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SJFSeize {
	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private Task now;
	private String name;

	public int getWaitQueueSize() {
		return waitQueue.size();
	}

	public class TaskComparator implements Comparator<Task> {

		@Override
		public int compare(Task o1, Task o2) {
			// TODO Auto-generated method stub
			return (o1.getRemainingTime() - o2.getRemainingTime());
		}

	}

	public SJFSeize(String name) {
		if (SJFSeize.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
	}

	static public void addWaitQueue() {
		if (TaskQueue.size() != 0) {
			waitQueue.offer(TaskQueue.poll());
		}
	}

	public void startUp() {
		begin();
	}

	private void begin() {
		Collections.sort(waitQueue, new TaskComparator());
		if (waitQueue.size() != 0) {
			this.now = SJFSeize.waitQueue.peek();
			if (now.getServiceTime() == now.getRemainingTime())
				now.setStartingTime(sumTime);
			work();
		}
	}

	private void work() {
		// TODO Auto-generated method stub
		now.setRemainingTime(now.getRemainingTime() - 1);
		sumTime++;
		if (now.getRemainingTime() == 0) {
			now.setFinishingTime(sumTime);
			now.setTurnAroundTime(now.getFinishingTime() - now.getArrivalTime());
			now.setWeightTurnAround(now.getTurnAroundTime() * 1.0 / now.getServiceTime());
			System.out.println(this.name + ":" + now);
			waitQueue.remove();
		}

	}

	private void loadTaskQueue() {
		SJFSeize.TaskQueue = readFile();
	}

	private LinkedList<Task> readFile() {
		System.out.println("TeskBegin");
		LinkedList<Task> queue = new LinkedList<Task>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("src\\edu\\fjnu\\math3\\file.txt");
			br = new BufferedReader(fr);
			String line = "";
			String[] infos = null;
			while ((line = br.readLine()) != null) {
				Task temp = new Task();
				infos = line.split(" ");
				temp.setTaskID(Integer.parseInt(infos[0]));
				temp.setArrivalTime(Integer.parseInt(infos[1]));
				temp.setServiceTime(Integer.parseInt(infos[2]));
				temp.setRemainingTime(Integer.parseInt(infos[2]));
				queue.offer(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return queue;

	}

}
