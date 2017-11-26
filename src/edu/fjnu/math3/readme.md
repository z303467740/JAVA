---
title: JAVA集合类综合小实验
tags: JAVA,面向对象,基础
---

## 模拟实现FCFS（先来先服务）算法
### FCFS算法按照任务到达的顺序进行服务，先来先服务
### 每个Task对象可以描述为至少包含下列属性：
taskID //任务ID
arrivalTime //到达时间
serviceTime //服务时间
startingTime //开始时间
finishingTime //完成时间=开始时间+服务时间
turnAroundTime //周转时间=完成时间-达到时间
weightTurnAround //带权周转时间=周转时间/服务时间
其他的属性根据程序需要设置
饰符，一般称之为“公共的”。被其修饰的类、属性以及方法不仅可以跨类访问，而且     允许跨包访问。 
### 任务(Task)的ID、开始时间和服务时间由文件读入，形如右图所示。这个任务列表文件首先由程序生成，每秒一个任务达到，服务时间由{6,2,1,3,9}这个集合中的数据随机获取。文件列表要包含至少100个任务。
 先要求实现如下要求的FCFS
1.当只有一个处理队列时的情况
2.当有两个处理队列时的情况

#### 部分代码
```java
public class FCFS {
	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	static private int sumTime = -1;
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
		sumTime++;
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

		if (sumTime == now.getFinishingTime()) {
			isFree = true;
			begin();
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
				infos = line.split("	");
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
```
#### 实验截图
单进程

![enter description here][1]
双进程

![enter description here][2]


## 模拟实现SJF（短作业优先）
SJF算法首先调度已到达的任务中，服务时间最短的任务，这里不要求实现任务的抢占。
按照FCFS算法的要求实现SJF算法，同样要求处理两种情况：
当只有一个处理队列时的情况
当有两个处理队列时的情况


#### 部分代码
```java
public class SJF {

	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	static private int sumTime = -1;
	private boolean isFree;
	private Task now;
	private String name;

	public int getWaitQueueSize() {
		return waitQueue.size();
	}

	public class TaskComparator implements Comparator<Task> {

		@Override
		public int compare(Task o1, Task o2) {
			// TODO Auto-generated method stub
			return (o1.getServiceTime() - o2.getServiceTime());
		}

	}

	public SJF(String name) {
		if (SJF.TaskQueue == null) {
			loadTaskQueue();
		}
		this.name = name;
		isFree = true;
	}

	public static void addWaitQueue() {
		sumTime++;
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
		Collections.sort(waitQueue, new TaskComparator());
		if (waitQueue.size() != 0) {
			this.now = SJF.waitQueue.poll();
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

		if (sumTime == now.getFinishingTime()) {
			isFree = true;
			begin();
		}

	}

	private void loadTaskQueue() {
		SJF.TaskQueue = readFile();
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
				infos = line.split("	");
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
```
#### 实验截图
单进程

![enter description here][3]

双进程

![enter description here][4]




  [1]: ./1.png "1"
  [2]: ./2.png "2"
  [3]: ./3.png "3"
  [4]: ./4.png "4"
