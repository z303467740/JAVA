package edu.fjnu.math3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FCFS {
	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private boolean isFree;
	private Task now;
	private String name;

	public int getWaitQueueSize() {
		return waitQueue.size();
	}

	public FCFS(String name) {
		if (FCFS.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
		isFree = true;
	}

	static public void addWaitQueue() {
		if (TaskQueue.size() != 0) {
			waitQueue.offer(TaskQueue.poll());
		}
	}

	public void startUp() {

		if (isFree) {
			begin();
		} else {
			work();
		}

	}

	private void begin() {
		if (waitQueue.size() != 0) {
			this.now = FCFS.waitQueue.poll();
			isFree = false;
			now.setStartingTime(sumTime);
			now.setFinishingTime(now.getStartingTime() + now.getServiceTime());
			now.setTurnAroundTime(now.getFinishingTime() - now.getArrivalTime());
			now.setWeightTurnAround(now.getTurnAroundTime() * 1.0 / now.getServiceTime());
			System.out.println(this.name + ":" + now);
		}
	}

	private void work() {
		// TODO Auto-generated method stub
		sumTime++;
		if (sumTime == now.getFinishingTime()) {
			isFree = true;
		}
	}

	private void loadTaskQueue() {
		FCFS.TaskQueue = readFile();
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
