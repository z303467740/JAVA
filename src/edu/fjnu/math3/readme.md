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
			fr = new FileReader("D:\\code\\javatest\\JAVAWOK\\src\\edu\\fjnu\\math3\\file.txt");
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
public class SJFSeize {
	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private Task now; // 当前文件
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
			fr = new FileReader("D:\\code\\javatest\\JAVAWOK\\src\\edu\\fjnu\\math3\\file.txt");
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
```
#### 实验截图
单进程

![enter description here][3]

双进程

![enter description here][4]
## 模拟实现SJF（抢占短作业优先）
SJF算法首先调度已到达的任务中，服务时间最短的任务，这里不要求实现任务的抢占。
按照FCFS算法的要求实现SJF算法，同样要求处理两种情况：
当只有一个处理队列时的情况
当有两个处理队列时的情况

#### 部分代码
```java

public class SJFSeize {
	static LinkedList<Task> TaskQueue = null;
	static private LinkedList<Task> waitQueue = new LinkedList<Task>();
	private int sumTime;
	private Task now; // 当前文件
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
			fr = new FileReader("D:\\code\\javatest\\JAVAWOK\\src\\edu\\fjnu\\math3\\file.txt");
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

```
#### 实验截图
单进程

![enter description here][5]

双进程

![enter description here][6]

注：以上三种算法大致都差不多，主要描述下抢占算法，这里设每做一次循环，为过一秒，等待队列从到达队列中获得一个Task实体（增加了一个属性RemainingTime，表示还剩下几秒这个进程完成），之后在寻找他们中间寻找RemainingTime最短的作业执行（RemainingTime-1），重复这个过程，当RemainingTime=0视为这个作业完成。


  [1]: ./1.png "1"
  [2]: ./2.png "2"
  [3]: ./3.png "3"
  [4]: ./4.png "4"
  [5]: ./5.png "5"
  [6]: ./6.png "6"