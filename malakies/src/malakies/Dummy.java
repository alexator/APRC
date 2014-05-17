package japrc2013;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class StudyPlanner implements StudyPlannerInterface {

	private ArrayList<TopicInterface> topics = new ArrayList<TopicInterface>();
	private ArrayList<StudyBlockInterface> plan = new ArrayList<StudyBlockInterface>();
	private ArrayList<CalendarEventInterface> calEven = new ArrayList<CalendarEventInterface>();
	private ArrayList<Integer> durations = new ArrayList<Integer>();
	private ArrayList<Integer> finished = new ArrayList<Integer>();

	private StudyPlannerGUIInterface gui;

	private int blockSize = 60;
	private int minBlockSize = 10;
	private int breakLength = 10;
	private boolean finish;
	private Calendar dailyStart = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 9, 00, 00);
	private Calendar dailyEnd = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 17, 00, 00);
	private int nowHour = 0;
	private int nowMin = 0;
	private int nowMonth = 0;
	private int nowYear = 0;
	private int nowDay = 0;
	private Calendar nowTopicTime = new GregorianCalendar();

	@Override
	public void addTopic(String name, int duration) {
		boolean flag_same = false;

		for (int i = 0; i < getTopics().size(); i++) {
			if (getTopics().get(i).getSubject().equalsIgnoreCase(name)) {
				flag_same = true;
				throw new StudyPlannerException(
						"Two topics have the same name!");
			}
		}

		if (!flag_same) {
			topics.add(new Topic(name, duration));
		}
	}

	@Override
	public List<TopicInterface> getTopics() {
		return topics;
	}

	@Override
	public List<StudyBlockInterface> getStudyPlan() {
		return plan;
	}

	public void setGUI(StudyPlannerGUIInterface gui) {
		this.gui = gui;
	}

	@Override
	public void generateStudyPlan() {

		if (getTopics().isEmpty()) {
			throw new StudyPlannerException("There are no topics to create a plan!");
		} else {
			plan = new ArrayList<StudyBlockInterface>();
			durations = new ArrayList<Integer>();
			finished = new ArrayList<Integer>();
			finish = false;
			
			Calendar startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE),Calendar.getInstance().get(Calendar.SECOND));
			initNowTime(startTopicTime);

			for (int i = 0; i < getTopics().size(); i++) {
				durations.add(topics.get(i).getDuration());
			}

			for (int i = 0; i < getTopics().size(); i++) {
				finished.add(0);
			}
//			for (int i= 0; i< calEven.size(); i++) {
//				if (!isInDayWindow(calEven.get(i).getStartTime())){
//				plan.add(new StudyBlock(calEven.get(i).getName(), calEven.get(i).getStartTime(),calEven.get(i).getDuration()));
//				}
//			}
			for (int i= 0; i< calEven.size(); i++) {
				
//				System.out.println(calEven.get(i).getStartTime());
//				System.out.println(calEven.get(i).isValidTopicTarget());
				
			}

			while (!finish) {
				System.out.println("Hello");
				for (int i = 0; i < topics.size(); i++) {
					
					if (isInDayWindow(nowTopicTime)) {		
					} else {
						nextDay();
					}
						System.out.println(thereIsAnEvent(nowTopicTime));
					if	(thereIsAnEvent(nowTopicTime)) {
						System.out.println("test");
						for (int j = 0; j<calEven.size(); j++) {
							if(nowTopicTime.get(Calendar.DAY_OF_MONTH) == calEven.get(j).getStartTime().get(Calendar.DAY_OF_MONTH)) {
								if (nowTopicTime.equals(calEven.get(j).getStartTime())) {
									System.out.println("1.1");								
										System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
									
										plan.add(new StudyBlock(calEven.get(j).getName(), calEven.get(j).getStartTime(),calEven.get(j).getDuration()));
										increaseTime(nowTopicTime, calEven.get(j).getDuration());
										System.out.println("Topic ends:" + nowTopicTime.getTime());
									
										if (breakLength != 0 && !thereIsAnEvent(nowTopicTime)) {
											if(isValidBreakSlot(nowTopicTime)) {
//												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
												startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
												plan.add(new StudyBlock("break", startTopicTime, breakLength));
												increaseTime(nowTopicTime, breakLength);
											}
										}
								} else if (timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime) < blockSize && timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime) > 0 && isInDayWindow(calEven.get(j).getStartTime())) {
									System.out.println("2.2");
									if (durations.get(i) != 0){
										if (isInDayWindow(nowTopicTime)) {		
										} else {
											nextDay();
										}
										if(timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)>minBlockSize){
										if(durations.get(i) >=blockSize) {
											System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
											
//											startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),nowMonth, nowDay, nowHour,nowMin);
//											startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
											startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
										
										plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)));
										durations.set(i, (durations.get(i) - timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)));
										increaseTime(nowTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime));
//										plan.add(new StudyBlock(calEven.get(j).getName(), calEven.get(j).getStartTime(),calEven.get(j).getDuration()));
//										increaseTime(nowTopicTime, calEven.get(j).getDuration());
//										System.out.println("Topic ends:" + nowTopicTime.getTime());
//										System.err.println("hi0");
//										System.out.println(thereIsAnEvent(nowTopicTime));
//										if (breakLength != 0 && !thereIsAnEvent(nowTopicTime)) {
//											System.err.println("hi1");
//											if(isValidBreakSlot(nowTopicTime)) {
//												System.err.println("hi2");
////												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
////												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//												startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
//												plan.add(new StudyBlock("break", startTopicTime, breakLength));
//												increaseTime(nowTopicTime, breakLength);
//										}
//										}
										}
//										else if ((durations.get(i) == durations.get(i) % blockSize) && durations.get(i) >= minBlockSize && durations.get(i) < timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)) { //The remaining duration of the topic is less than BlockSize but it is greater than the minBlockSize
//											System.out.println("2: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
////											startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour,nowMin);
////											startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//											startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
//											plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime,(durations.get(i) % blockSize)));
//											increaseTime(nowTopicTime, (durations.get(i) % blockSize));
//											durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
//											if (breakLength != 0 && !thereIsAnEvent(nowTopicTime)) {
//												System.err.println("hi1");
//												if(isValidBreakSlot(nowTopicTime)) {
//													System.err.println("hi2");
////													startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
////													startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//													startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
//													plan.add(new StudyBlock("break", startTopicTime, breakLength));
//													increaseTime(nowTopicTime, breakLength);
//											}
//											}
//										System.out.println("Topic ends:" + nowTopicTime.getTime());
//										System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
//										}
										} else {
											increaseTime(nowTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime));
										}
										plan.add(new StudyBlock(calEven.get(j).getName(), calEven.get(j).getStartTime(),calEven.get(j).getDuration()));
										increaseTime(nowTopicTime, calEven.get(j).getDuration());
										System.out.println("Topic ends:" + nowTopicTime.getTime());
										System.err.println("hi0");
										System.out.println(thereIsAnEvent(nowTopicTime));
										if (breakLength != 0 && !thereIsAnEvent(nowTopicTime)) {
											System.err.println("hi1");
											if(isValidBreakSlot(nowTopicTime)) {
												System.err.println("hi2");
												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
												plan.add(new StudyBlock("break", startTopicTime, breakLength));
												increaseTime(nowTopicTime, breakLength);
										}
										}
										if(i== topics.size()-1){
											i=0;
										}else {
											i=i+1;
										}
									} 
								} else if(calEven.get(j).getStartTime().getTimeInMillis() < getDailyStartStudyTime().getTimeInMillis()){
									plan.add(new StudyBlock(calEven.get(j).getName(), calEven.get(j).getStartTime(),calEven.get(j).getDuration()));
									increaseTime(nowTopicTime, calEven.get(j).getDuration());
									
								}
						}
							}
					}
						
					if (durations.get(i) != 0) { // if there is a duration for this topic
						if	(!thereIsAnEvent(nowTopicTime)) {
						if (timeDiffInMin(getDailyEndStudyTime(),nowTopicTime) >= blockSize) { // The remaining time in day is greater or equal to the blockSize
							System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
//							System.out.println(durations.get(i));
							if (durations.get(i) >= blockSize) { //the remaining duration is greater or equal to the blockSize
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),nowMonth, nowDay, nowHour,nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, blockSize));
								increaseTime(nowTopicTime, blockSize);
								durations.set(i, (durations.get(i) - blockSize));
								System.out.println("Topic ends:" + nowTopicTime.getTime());
							} else if ((durations.get(i) == durations.get(i) % blockSize) && durations.get(i) >= minBlockSize) { //The remaining duration of the topic is less than BlockSize but it is greater than the minBlockSize
								System.out.println("2: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour,nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime,(durations.get(i) % blockSize)));
								increaseTime(nowTopicTime, (durations.get(i) % blockSize));
								durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
							} else if (durations.get(i) < minBlockSize) { // The remaining duration of this topics is less than BlockSize and less than minBlockSize but I study it for minBlockSize
								System.out.println("3: " + timeDiffInMin(getDailyEndStudyTime(), nowTopicTime));
								System.out.println("haha");
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, minBlockSize));
								increaseTime(nowTopicTime, minBlockSize);
								durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
							}
						} else if (timeDiffInMin(getDailyEndStudyTime(), nowTopicTime) >= minBlockSize) { //The remaining time in day is less than blockSize but greater or equal to minBlockSize
							System.out.println("4: " + timeDiffInMin(getDailyEndStudyTime(), nowTopicTime));
							if (timeDiffInMin(getDailyEndStudyTime(), nowTopicTime) != 0) {
								if(durations.get(i)>= blockSize){
								System.out.println(nowTopicTime.getTime());
								System.out.println(timeDiffInMin(getDailyEndStudyTime(), nowTopicTime));
								int block = timeDiffInMin(getDailyEndStudyTime(), nowTopicTime);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, block));
								increaseTime(nowTopicTime, block);
								durations.set(i, (durations.get(i) - block));}
								else {
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour,nowMin);
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
									startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
									plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime,(durations.get(i) % blockSize)));
									increaseTime(nowTopicTime, (durations.get(i) % blockSize));
									durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
								}
							}
						} else if (timeDiffInMin(getDailyEndStudyTime(), nowTopicTime) < minBlockSize) { //If the remaining time in day is less than minBlockSize skip to the next Day
							nextDay();
							if (durations.get(i) >= blockSize) { //the remaining duration is greater or equal to the blockSize
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),nowMonth, nowDay, nowHour,nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, blockSize));
								increaseTime(nowTopicTime, blockSize);
								durations.set(i, (durations.get(i) - blockSize));
								System.out.println("Topic ends:" + nowTopicTime.getTime());
							} else if ((durations.get(i) == durations.get(i) % blockSize) && durations.get(i) >= minBlockSize) { //The remaining duration of the topic is less than BlockSize but it is greater than the minBlockSize
								System.out.println("2: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour,nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime,(durations.get(i) % blockSize)));
								increaseTime(nowTopicTime, (durations.get(i) % blockSize));
								durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
							} else if (durations.get(i) < minBlockSize) { // The remaining duration of this topics is less than BlockSize and less than minBlockSize but I study it for minBlockSize
								System.out.println("3: " + timeDiffInMin(getDailyEndStudyTime(), nowTopicTime));
								System.out.println("haha");
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//								startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
								startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
								plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, minBlockSize));
								increaseTime(nowTopicTime, minBlockSize);
								durations.set(i, (durations.get(i) - (durations.get(i) % blockSize)));
							}
						}
					} else if

						if (breakLength != 0) { //If there is length for break period
							if (isValidBreakSlot(nowTopicTime)) {
								if (timeDiffInMin(getDailyEndStudyTime(), nowTopicTime) >= breakLength && !thereIsAnEventBreak(nowTopicTime)){
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
									startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
									plan.add(new StudyBlock("break", startTopicTime, breakLength));
									increaseTime(nowTopicTime, breakLength);
									System.out.println("Break ends: " + nowTopicTime.getTime());
								} else if(timeDiffInMin(getDailyEndStudyTime(), nowTopicTime) < breakLength && !thereIsAnEventBreak(nowTopicTime)) {
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//									startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
									startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
									plan.add(new StudyBlock("break", startTopicTime, timeDiffInMin(getDailyEndStudyTime(), nowTopicTime)));
									increaseTime(nowTopicTime, timeDiffInMin(getDailyEndStudyTime(), nowTopicTime));
									System.out.println("Break ends: " + nowTopicTime.getTime());
								} else if(thereIsAnEventBreak(nowTopicTime)) {
									for (int j = 0; j<calEven.size(); j++) {
										if(nowTopicTime.get(Calendar.DAY_OF_MONTH) == calEven.get(j).getStartTime().get(Calendar.DAY_OF_MONTH)) {
											if (timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime) < breakLength && timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)>0){
//												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
//												startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
												startTopicTime = new GregorianCalendar(nowYear, nowMonth, nowDay, nowHour, nowMin);
												plan.add(new StudyBlock("break", startTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)));
												increaseTime(nowTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime));
											}
										}
										}
								}
							}
						}
						}
//					}
				}
				if (durations.equals(finished)) {
					finish = true;
				}
			}
//			for(int i = 0; i<calEven.size(); i++){
//				if (isInDayWindow(calEven.get(i).getStartTime()) && nowTopicTime.getTimeInMillis() < calEven.get(i).getStartTime().getTimeInMillis()){
//					plan.add(new StudyBlock(calEven.get(i).getName(), calEven.get(i).getStartTime(),calEven.get(i).getDuration()));
//					}
//			}
			sortPlan();
//			for(int i=0; i<plan.size(); i++){
//				System.out.println(plan.get(i).getStartTime().getTimeInMillis());
//			}
			if (gui != null) {
				gui.notifyModelHasChanged();
			}
			}
			}
	
	public void sortPlan() {

		Collections.sort(plan, new Comparator<StudyBlockInterface>() {
			@Override
			public int compare(StudyBlockInterface o1, StudyBlockInterface o2) {
				return Long.compare(o1.getStartTime().getTimeInMillis(), o2.getStartTime().getTimeInMillis());
			}

		});
	}

	private boolean isInDayWindow(Calendar now) {

		Calendar endD = getDailyEndStudyTime();
		boolean cond = false;

		if (now.get(Calendar.HOUR_OF_DAY) >= getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY) && (now.get(Calendar.HOUR_OF_DAY) < getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY))) {
			if (timeDiffInMin(endD, now) >= minBlockSize) {
				return true;
//				cond = true;
			}
		} else {
			return false;
//			cond = false;
		}
		return cond;
	}
	
	private void increaseTime(Calendar now, int amount) {
		now.add(Calendar.MINUTE,amount);
		nowHour = now.get(Calendar.HOUR_OF_DAY);
		nowMin = now.get(Calendar.MINUTE);
	}
	
	private boolean isValidBreakSlot(Calendar now) {
		if (!durations.equals(finished) && now.get(Calendar.HOUR_OF_DAY) < getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY) && timeDiffInMin(getDailyStartStudyTime(), now) != 0) {
			if (now.get(Calendar.HOUR_OF_DAY) - getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY) != 0) {
				return true;
			}
		} 
		return false;
	}
	
	private boolean thereIsAnEvent(Calendar now) {
		boolean cond = false;
		for (int i = 0; i<calEven.size(); i++) {
//			if(now.get(Calendar.DAY_OF_MONTH) == calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH)) {
				if (now.equals(calEven.get(i).getStartTime())) {
					System.out.println("1");
					return true;
//					System.out.println(cond);
				} else if (timeDiffInMin(calEven.get(i).getStartTime(), now)<blockSize && timeDiffInMin(calEven.get(i).getStartTime(), now) > 0){
					return true;
//					cond = true;
//					System.out.println("2");
//					System.out.println(cond);
				} else if (calEven.get(i).getStartTime().getTimeInMillis() < getDailyStartStudyTime().getTimeInMillis()) {
					return true;
				}
//			} else {
//				return false;
////				cond = false;
//			}
		}
		System.out.println(cond);
		return cond;
	}
	
	private boolean thereIsAnEventBreak(Calendar now) {
		boolean cond = false;
		for (int i = 0; i<calEven.size(); i++) {
			if(now.get(Calendar.DAY_OF_MONTH) == calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH)) {
				if (now.equals(calEven.get(i).getStartTime())) {
					System.out.println("1");
					return true;
//					System.out.println(cond);
				} else if (timeDiffInMin(calEven.get(i).getStartTime(), now)<breakLength && timeDiffInMin(calEven.get(i).getStartTime(), now) > 0){
					return true;
//					cond = true;
//					System.out.println("2");
//					System.out.println(cond);
				} 
			} else {
				return false;
//				cond = false;
			}
		}
		System.out.println(cond);
		return cond;
	}

	// na do ti ginete otan allazei o xronos. na balo kai ena nowYear basika kapou.
	
	

	private void nextDay() {
		nowHour = getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY);
		nowMin = getDailyStartStudyTime().get(Calendar.MINUTE);
		nowTopicTime.set(Calendar.HOUR_OF_DAY, getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY));
		nowTopicTime.set(Calendar.MINUTE, getDailyStartStudyTime().get(Calendar.MINUTE));
		nowTopicTime.set(Calendar.SECOND, 00);
		nowTopicTime.add(Calendar.DATE, 1);
		nowDay = nowTopicTime.get(Calendar.DAY_OF_MONTH);
		nowMonth = nowTopicTime.get(Calendar.MONTH);
		nowYear = nowTopicTime.get(Calendar.YEAR);
		dailyStart.add(Calendar.DAY_OF_MONTH, 1);
		dailyEnd.add(Calendar.DAY_OF_MONTH, 1);
	}

	private void initNowTime(Calendar start) {
		nowTopicTime.clear();
		nowTopicTime.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
		nowTopicTime.set(Calendar.MINUTE, start.get(Calendar.MINUTE));
		nowTopicTime.set(Calendar.SECOND, start.get(Calendar.SECOND));
		nowTopicTime.set(Calendar.MONTH, start.get(Calendar.MONTH));
		nowTopicTime.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH));
		nowTopicTime.set(Calendar.YEAR, start.get(Calendar.YEAR));
		nowHour = nowTopicTime.get(Calendar.HOUR_OF_DAY);
		nowMin = nowTopicTime.get(Calendar.MINUTE);
		nowMonth = nowTopicTime.get(Calendar.MONTH);
		nowDay = nowTopicTime.get(Calendar.DAY_OF_MONTH);
		nowYear = nowTopicTime.get(Calendar.YEAR);
	}

	private int timeDiffInMin(Calendar timPar1, Calendar timPar2) {

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
		String time1;
		String time2;
		
		time1 = timeFormat.format(timPar1.getTime());
		time2 = timeFormat.format(timPar2.getTime());
		float timeparse1 = Float.parseFloat(time1);
		float timeparse2 = Float.parseFloat(time2);
		int timeparseint1 = (int) timeparse1;
		int timeparseint2 = (int) timeparse2;
		int actual1 = (int) (((timeparseint1 % 60) * 60) + ((Math.round(timeparse1 * 100)) % 100));
		int actual2 = (int) (((timeparseint2 % 60) * 60) + ((Math.round(timeparse2 * 100)) % 100));
		float diff = (float) ((actual1 - actual2));
		int diffint = (int) (diff);
		
		return diffint;

	}

	@Override
	public void deleteTopic(String topic) {
		for (int i = 0; i < getTopics().size(); i++) {
			if (getTopics().get(i).getSubject().equals(topic)) {
				topics.remove(i);
			}
		}
	}

	@Override
	public void generateStudyPlan(Calendar startStudy) {
		//
		// if (getTopics().isEmpty()) {
		// throw new
		// StudyPlannerException("There are no topics to create a plan!");
		// } else {
		// plan = new ArrayList<StudyBlockInterface>();
		// durations = new ArrayList<Integer>();
		// finished = new ArrayList<Integer>();
		// finish = false;
		//
		// Calendar nowTopicTime = new GregorianCalendar();
		// Calendar startEmptyTime = new GregorianCalendar();
		// Calendar nowEmptyTime = new GregorianCalendar();
		// startStudy.get(Calendar.HOUR_OF_DAY);
		// startStudy.get(Calendar.MINUTE);
		// nowTopicTime.set(Calendar.HOUR_OF_DAY,startStudy.get(Calendar.HOUR_OF_DAY));
		// nowTopicTime.set(Calendar.MINUTE, startStudy.get(Calendar.MINUTE));
		// nowEmptyTime.set(Calendar.HOUR_OF_DAY,getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY));
		// nowEmptyTime.set(Calendar.MINUTE,
		// getDailyStartStudyTime().get(Calendar.MINUTE));
		//
		// int nowHour = startStudy.get(Calendar.HOUR_OF_DAY);
		// int nowMin = startStudy.get(Calendar.MINUTE);
		// int nsh = getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY);
		// int nsm = getDailyStartStudyTime().get(Calendar.MINUTE);
		//
		//
		// while(
		// (startStudy.get(Calendar.HOUR_OF_DAY)-nowEmptyTime.get(Calendar.HOUR_OF_DAY)
		// )!= 0) {
		// startEmptyTime = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,
		// Calendar.DAY_OF_MONTH, nsh, nsm);
		// plan.add(new StudyBlock("Free Block",startEmptyTime , blockSize));
		// nowEmptyTime.add(startEmptyTime.MINUTE, blockSize);
		// nsh = nowEmptyTime.get(Calendar.HOUR_OF_DAY);
		// nsm = nowEmptyTime.get(Calendar.MINUTE);
		// }
		//
		//
		// for (int i = 0; i < getTopics().size(); i++) {
		// durations.add(topics.get(i).getDuration());
		// }
		//
		// for (int i = 0; i < getTopics().size(); i++) {
		// finished.add(0);
		// }
		//
		// while (!finish) {
		// if(nowHour != (getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY)-1)){
		// for (int i = 0; i < topics.size(); i++) {
		// if (durations.get(i) != 0) {
		// if (durations.get(i) >= blockSize) {
		// startStudy = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,
		// Calendar.DAY_OF_MONTH, nowHour, nowMin);
		// plan.add(new StudyBlock(topics.get(i).getSubject(),startStudy ,
		// blockSize));
		// nowTopicTime.add(Calendar.MINUTE, blockSize);
		// nowHour = nowTopicTime.get(Calendar.HOUR_OF_DAY);
		// nowMin = nowTopicTime.get(Calendar.MINUTE);
		// } else if (durations.get(i) == durations.get(i) % blockSize) {
		// startStudy = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,
		// Calendar.DAY_OF_MONTH, nowHour, nowMin);
		// plan.add(new StudyBlock(topics.get(i).getSubject(), startStudy,
		// (durations.get(i) % blockSize)));
		// nowTopicTime.add(Calendar.MINUTE, durations.get(i) % blockSize);
		// nowHour = nowTopicTime.get(Calendar.HOUR_OF_DAY);
		// nowMin = nowTopicTime.get(Calendar.MINUTE);
		// }
		//
		// if (durations.get(i) >= blockSize) {
		// durations.set(i, (durations.get(i) - blockSize));
		// } else {
		// durations.set(i, (durations.get(i) - (durations.get(i) %
		// blockSize)));
		// }
		//
		// if (breakLength != 0) {
		// if (!durations.equals(finished)) {
		// startStudy = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,
		// Calendar.DAY_OF_MONTH, nowHour, nowMin);
		// plan.add(new StudyBlock("break", startStudy, breakLength));
		// nowTopicTime.add(Calendar.MINUTE, breakLength);
		// nowHour = nowTopicTime.get(Calendar.HOUR_OF_DAY);
		// nowMin = nowTopicTime.get(Calendar.MINUTE);
		// }
		// }
		// }
		// }
		// }else if (nowHour ==
		// (getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY)-1)) {
		// nowHour = getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY);
		// nowMin = getDailyStartStudyTime().get(Calendar.MINUTE);
		// nowTopicTime.set(Calendar.HOUR_OF_DAY,getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY));
		// nowTopicTime.set(Calendar.MINUTE,
		// getDailyStartStudyTime().get(Calendar.MINUTE));
		// }
		//
		// if (durations.equals(finished)) {
		// finish = true;
		// }
		// }
		//
		// if (gui != null) {
		// gui.notifyModelHasChanged();
		// }
		// }
	}

	@Override
	public void setBlockSize(int size) {
		if (size > minBlockSize) {
			blockSize = size;
		} else {
			throw new StudyPlannerException(
					"The minimum study block should be 10 minutes!");
		}
	}

	@Override
	public void setBreakLength(int i) {
		breakLength = i;
	}

	@Override
	public void setDailyStartStudyTime(Calendar startTime) {
		if (getDailyEndStudyTime().equals(null)) {
			dailyStart = startTime;
		} else {
			if (startTime.compareTo(getDailyEndStudyTime()) < 0) {
				if (getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY)
						- getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY) < blockSize) {
					throw new StudyPlannerException(
							"Daily start time minus Daily end time must be larger than your Current Study Block ");
				} else {
					dailyStart = startTime;
				}
			} else if (startTime.compareTo(getDailyEndStudyTime()) > 0) {
				throw new StudyPlannerException(
						"Your starting time is after the the end Time");
			}
		}
	}

	@Override
	public void setDailyEndStudyTime(Calendar endTime) {
		if (getDailyStartStudyTime().equals(null)) {
			if (getDailyEndStudyTime().get(Calendar.HOUR_OF_DAY)
					- getDailyStartStudyTime().get(Calendar.HOUR_OF_DAY) < blockSize) {
				throw new StudyPlannerException("Daily start time minus Daily end time must be larger than your Current Study Block ");
			} else {
				dailyEnd = endTime;
			}
		} else {
			if (endTime.compareTo(getDailyStartStudyTime()) > 0) {
				dailyEnd = endTime;
			} else if (endTime.compareTo(getDailyEndStudyTime()) < 0) {
				throw new StudyPlannerException("Your ending Time is before the the start Time");
			}
		}
	}

	@Override
	public Calendar getDailyStartStudyTime() {
		return dailyStart;
	}

	@Override
	public Calendar getDailyEndStudyTime() {
		return dailyEnd;
	}

	@Override
	public void addCalendarEvent(String eventName, Calendar startTime, int duration) {
		
		Calendar now1 = new GregorianCalendar();
		Calendar now2 = new GregorianCalendar();
		boolean ok = false;
		long starts1;
		long ends1;
		long starts2;
		long ends2;
		
		if (calEven.isEmpty()) {
			calEven.add(new CalendarEvent(eventName, startTime, duration, null));
		} else {
			
			for(int i = 0; i<calEven.size(); i++) {
				if (startTime.get(Calendar.MONTH) == calEven.get(i).getStartTime().get(Calendar.MONTH) && startTime.get(Calendar.DAY_OF_MONTH) == calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH)) {
					now1.clear();
					now1.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY));
					now1.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE));
					now1.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
					now1.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
					now1.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
					now1.set(Calendar.SECOND, startTime.get(Calendar.SECOND));
					starts1 = now1.getTimeInMillis();
					now1.add(Calendar.MINUTE, duration);
					ends1 = now1.getTimeInMillis();
					
					now2.clear();
					now2.set(Calendar.HOUR_OF_DAY, calEven.get(i).getStartTime().get(Calendar.HOUR_OF_DAY));
					now2.set(Calendar.MINUTE, calEven.get(i).getStartTime().get(Calendar.MINUTE));
					now2.set(Calendar.MONTH, calEven.get(i).getStartTime().get(Calendar.MONTH));
					now2.set(Calendar.DAY_OF_MONTH, calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH));
					now2.set(Calendar.YEAR, calEven.get(i).getStartTime().get(Calendar.YEAR));
					now2.set(Calendar.SECOND, startTime.get(Calendar.SECOND));
					
					starts2 = now2.getTimeInMillis();
					now2.add(Calendar.MINUTE, calEven.get(i).getDuration());
					ends2 =  now2.getTimeInMillis();			
					
					if (((starts2< starts1)&&(ends2<=starts1))||((starts2>=ends1)&&(ends2>ends1))) {
						ok = true;
					}
					
					now1.clear();
					now2.clear();
					starts1 = 0;
					ends1 = 0;
					starts2 = 0;
					ends2 = 0;
				} else {
					ok = true;
				}
			}
			
			if(ok) {
				calEven.add(new CalendarEvent(eventName, startTime, duration, null));
			} else {
				throw new StudyPlannerException("Calendar Events Overlap");
			}
			
			now1.clear();
			now2.clear();
			starts1 = 0;
			ends1 = 0;
			starts2 = 0;
			ends2 = 0;
		}
	}

	@Override
	public void addCalendarEvent(String eventName, Calendar startTime, int duration, CalendarEventType type) {
		
		Calendar now1 = new GregorianCalendar();
		Calendar now2 = new GregorianCalendar();
		boolean ok = false;
		long starts1;
		long ends1;
		long starts2;
		long ends2;
		
		if (calEven.isEmpty()) {
			calEven.add(new CalendarEvent(eventName, startTime, duration, type));
		} else {
			
			for(int i = 0; i<calEven.size(); i++) {
				if (startTime.get(Calendar.MONTH) == calEven.get(i).getStartTime().get(Calendar.MONTH) && startTime.get(Calendar.DAY_OF_MONTH) == calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH)) {
					now1.clear();
					now1.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY));
					now1.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE));
					now1.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
					now1.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));
					now1.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
					now1.set(Calendar.SECOND, startTime.get(Calendar.SECOND));
					starts1 = now1.getTimeInMillis();
					now1.add(Calendar.MINUTE, duration);
					ends1 = now1.getTimeInMillis();
					
					now2.clear();
					now2.set(Calendar.HOUR_OF_DAY, calEven.get(i).getStartTime().get(Calendar.HOUR_OF_DAY));
					now2.set(Calendar.MINUTE, calEven.get(i).getStartTime().get(Calendar.MINUTE));
					now2.set(Calendar.MONTH, calEven.get(i).getStartTime().get(Calendar.MONTH));
					now2.set(Calendar.DAY_OF_MONTH, calEven.get(i).getStartTime().get(Calendar.DAY_OF_MONTH));
					now2.set(Calendar.YEAR, calEven.get(i).getStartTime().get(Calendar.YEAR));
					now2.set(Calendar.SECOND, startTime.get(Calendar.SECOND));
					
					starts2 = now2.getTimeInMillis();
					now2.add(Calendar.MINUTE, calEven.get(i).getDuration());
					ends2 =  now2.getTimeInMillis();			
					
					if (((starts2< starts1)&&(ends2<=starts1))||((starts2>=ends1)&&(ends2>ends1))) {
						ok = true;
					}
					
					now1.clear();
					now2.clear();
					starts1 = 0;
					ends1 = 0;
					starts2 = 0;
					ends2 = 0;
				} else {
					ok = true;
				}
			}
			
			if(ok) {
				calEven.add(new CalendarEvent(eventName, startTime, duration, type));
			} else {
				throw new StudyPlannerException("Calendar Events Overlap");
			}
			
			now1.clear();
			now2.clear();
			starts1 = 0;
			ends1 = 0;
			starts2 = 0;
			ends2 = 0;
		}
	}

	@Override
	public List<CalendarEventInterface> getCalendarEvents() {
		return calEven;
	}

	@Override
	public void saveData(OutputStream saveStream) {
		PrintWriter out = new PrintWriter(saveStream);
		out.println();
	}

	@Override
	public void loadData(InputStream loadStream) {
	}

}
