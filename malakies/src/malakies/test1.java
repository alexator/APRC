package malakies;

import japrc2013.StudyBlock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class test1 {
	
	static ArrayList <String> nnn = new ArrayList<String> () ;
	
	static Calendar startTopicTime = new GregorianCalendar(2013,11, 10, 9, 15);
	static Calendar nowTopicTime = new GregorianCalendar(2013,11, 10, 17, 30);
	static SimpleDateFormat d_f = new SimpleDateFormat("HH.mm");
	static String time1;
	static String time2;
	
	


	public static void main(String[] args) {
		System.out.println(startTopicTime.getTimeInMillis());
		System.out.println(nowTopicTime.getTimeInMillis());
		
	}

}


if	(thereIsAnEvent(nowTopicTime)) {
	System.out.println("test");
	for (int j = 0; j<calEven.size(); j++) {
		if(nowTopicTime.get(Calendar.DAY_OF_MONTH) == calEven.get(j).getStartTime().get(Calendar.DAY_OF_MONTH)) {
			if (nowTopicTime.equals(calEven.get(j).getStartTime())) {
				System.out.println("1.1");
				increaseTime(nowTopicTime, calEven.get(j).getDuration());
				if (isInDayWindow(nowTopicTime)) {		
				} else {
					nextDay();
				}
				if (durations.get(i) != 0) {
					if (breakLength != 0){
						if(isValidBreakSlot(nowTopicTime)){
							startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
							plan.add(new StudyBlock("break", startTopicTime, breakLength));
							increaseTime(nowTopicTime, breakLength);
						}
					}
					System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
				startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),nowMonth, nowDay, nowHour,nowMin);
				plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, blockSize));
				durations.set(i, (durations.get(i) - blockSize));
				increaseTime(nowTopicTime, blockSize);
				System.out.println("Topic ends:" + nowTopicTime.getTime());
				if (breakLength != 0){
					if(isValidBreakSlot(nowTopicTime)){
						startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), nowMonth, nowDay, nowHour, nowMin);
						plan.add(new StudyBlock("break", startTopicTime, breakLength));
						increaseTime(nowTopicTime, breakLength);
					}
				}
				}
			} else if (timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime) < blockSize && timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)>0) {
				System.out.println("2.2");
				if (durations.get(i) != 0){
				if (isInDayWindow(nowTopicTime)) {		
				} else {
					nextDay();
				}
				System.out.println("Topic " + "start: " + nowTopicTime.getTime() + " remaining hours in day: " + timeDiffInMin(getDailyEndStudyTime(),nowTopicTime));
				startTopicTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),nowMonth, nowDay, nowHour,nowMin);
				plan.add(new StudyBlock(topics.get(i).getSubject(), startTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)));
				durations.set(i, (durations.get(i) - timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime)));
				increaseTime(nowTopicTime, timeDiffInMin(calEven.get(j).getStartTime(), nowTopicTime));
				System.out.println("Topic ends:" + nowTopicTime.getTime());
			} 
			}
		}
	}
} else {