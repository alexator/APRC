package japrc2013;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudyPlanner implements StudyPlannerInterface {
	private ArrayList<TopicInterface> topics = new ArrayList<TopicInterface>();
	private ArrayList<StudyBlockInterface> plan = new ArrayList<StudyBlockInterface>();
	private ArrayList<Integer> durations = new ArrayList<Integer>();
	private ArrayList<Integer> finished = new ArrayList<Integer>();

	private StudyPlannerGUIInterface gui;

	private int blockSize;
	private int breakLength;
	private boolean finish;

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

			throw new StudyPlannerException(
					"There are no topics to create a plan!");

		} else {

			plan = new ArrayList<StudyBlockInterface>();
			durations = new ArrayList<Integer>();
			finished = new ArrayList<Integer>();

			for (int i = 0; i < getTopics().size(); i++) {
				durations.add(topics.get(i).getDuration());

			}
			System.out.println(durations);
			for (int i = 0; i < getTopics().size(); i++) {
				finished.add(0);
			}

			finish = false;

			while (!finish) {

				for (int i = 0; i < topics.size(); i++) {
					if (durations.get(i) != 0) {

						// System.out.println(i);

						if (durations.get(i) >= blockSize) {
							plan.add(new StudyBlock(topics.get(i).getSubject(),
									Calendar.getInstance(), blockSize));
						} else {
							plan.add(new StudyBlock(topics.get(i).getSubject(),
									Calendar.getInstance(),
									(durations.get(i) % blockSize)));
						}

						if (breakLength != 0) {
							plan.add(new StudyBlock("break", Calendar
									.getInstance(), breakLength));
						}

						if (durations.get(i) >= blockSize) {
							durations.set(i, (durations.get(i) - blockSize));
							System.out.println(durations.get(i));
						} else {
							durations
									.set(i, (durations.get(i) - (durations
											.get(i) % blockSize)));
						}
						System.out.println(durations.get(i));
					}
				}

				System.out.println(durations);
				System.out.println(finished);
				if (durations.equals(finished)) {
					finish = true;
				}
			}

			if (gui != null) {
				gui.notifyModelHasChanged();
			}
		}
		System.err.println("finished");
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
		if (getTopics().isEmpty()) {

			throw new StudyPlannerException(
					"There are no topics to create a plan!");

		} else {

			plan = new ArrayList<StudyBlockInterface>();

			for (int i = 0; i < topics.size(); i++) {
				plan.add(new StudyBlock(topics.get(i).getSubject(), startStudy,
						topics.get(i).getDuration()));
			}

			if (gui != null) {
				gui.notifyModelHasChanged();
			}

		}
	}

	@Override
	public void setBlockSize(int size) {
		blockSize = size;
	}

	@Override
	public void setBreakLength(int i) {
		breakLength = i;
	}

	@Override
	public void setDailyStartStudyTime(Calendar startTime) {
	}

	@Override
	public void setDailyEndStudyTime(Calendar endTime) {
	}

	@Override
	public Calendar getDailyStartStudyTime() {
		return null;
	}

	@Override
	public Calendar getDailyEndStudyTime() {
		return null;
	}

	@Override
	public void addCalendarEvent(String eventName, Calendar startTime,
			int duration) {
	}

	@Override
	public void addCalendarEvent(String eventName, Calendar startTime,
			int duration, CalendarEventType type) {
	}

	@Override
	public List<CalendarEventInterface> getCalendarEvents() {
		return null;
	}

	@Override
	public void saveData(OutputStream saveStream) {
	}

	@Override
	public void loadData(InputStream loadStream) {
	}

}
