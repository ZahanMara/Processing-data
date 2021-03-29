package controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import model.MonitoredData;
import model.Time;
import java.util.*;


public class MainController {

    public ArrayList<Integer> countDays(ArrayList<MonitoredData> activityList) {
        Task2 obj = (activity) -> {
            ArrayList<Integer> dayList = new ArrayList<Integer>();

            for (MonitoredData currentActivity : activity) {
                Time start = currentActivity.getStart();
                Time end = currentActivity.getEnd();
                int startDay = start.getDay();
                int endDay = end.getDay();

                if (!dayList.contains(startDay)) {
                    dayList.add(startDay);
                }
                if (!dayList.contains(endDay)) {
                    dayList.add(endDay);
                }
            }
            return dayList;
        };
        return obj.countDistinctDays(activityList);
    }

    public Map<String, Integer> activityFrequency(ArrayList<MonitoredData> activityList) {
        Task3 obj = (activity) -> {

            ArrayList<String> activityNames = new ArrayList<String>();
            Map<String, Integer> activityFrequencyMap = new HashMap<String, Integer>();
            int cntr = 0;

            for (MonitoredData currentActivity : activity) {

                Iterator<MonitoredData> itr = activity.iterator();

                cntr = 0;
                while (itr.hasNext()) {
                    MonitoredData curAct = itr.next();
                    if (curAct.getActivity().compareTo(currentActivity.getActivity()) == 0)
                        cntr++;
                }

                if (!activityNames.contains(currentActivity.getActivity())) {
                    activityNames.add(currentActivity.getActivity());
                    activityFrequencyMap.put(currentActivity.getActivity(), cntr);
                }
            }
            return activityFrequencyMap;
        };
        return obj.ActivityFrequency(activityList);
    }

    public Map<Integer, Map<String, Long>> activityFrequencyOnDays(ArrayList<MonitoredData> activityList) {

        Map<Integer, Map<String, Long>> freqOnDays = activityList.stream().collect(Collectors.groupingBy(element -> {
            return element.getStart().getDay();
        }, Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));

        return freqOnDays;
    }

    public Map<String, String> activityDuration(ArrayList<MonitoredData> activityList) {

        Task5 obj = (activity) -> {

            Map<String, Integer> actDuration = new HashMap<String, Integer>();
            ArrayList<String> activityNames = new ArrayList<String>();

            Iterator<MonitoredData> it = activity.iterator();
            int sum = 0;
            while (it.hasNext()) {
                MonitoredData currentMonitoredData = it.next();
                String activityName = currentMonitoredData.getActivity();
                sum = 0;
                for (MonitoredData current : activity) {
                    String activityNameString = current.getActivity();
                    if (activityName.compareTo(activityNameString) == 0) {

                        long start = (current.getStart().getYear()) * 365 * 24 * 60 * 60
                                + (current.getStart().getMonth()) * 30 * 24 * 60 * 60
                                + (current.getStart().getDay()) * 24 * 60 * 60
                                + (current.getStart().getHour()) * 60 * 60 + (current.getStart().getMinute()) * 60
                                + (current.getStart().getSecond());
                        long end = (current.getEnd().getYear()) * 365 * 24 * 60 * 60
                                + (current.getEnd().getMonth()) * 30 * 24 * 60 * 60
                                + (current.getEnd().getDay()) * 24 * 60 * 60
                                + (current.getEnd().getHour()) * 60 * 60 + (current.getEnd().getMinute()) * 60
                                + (current.getEnd().getSecond());
                        long duration = end - start;
                        sum += duration;
                    }
                }
                if (!activityNames.contains(activityName)) {
                    activityNames.add(activityName);
                    actDuration.put(activityName, sum);
                }
            }

            Map<String, String> actDurationString = new HashMap<String, String>();
            for (Entry<String, Integer> pair : actDuration.entrySet()) {
                String aName = pair.getKey();
                int duration = pair.getValue();

                int days = duration / 60 / 60 / 24;
                int hours = (duration - (days * 24 * 60 * 60)) / 60 / 60;
                int minutes = (duration - (days * 24 * 60 * 60) - hours * 60 * 60) / 60;
                int seconds = (duration - (days * 24 * 60 * 60) - hours * 60 * 60 - minutes * 60);

                StringBuilder sb = new StringBuilder();
                if(days == 1) {
                    sb.append(days).append(" day, ");
                }
                else sb.append(days).append(" days, ");

                if(hours == 1) {
                sb.append(hours).append(" hour, ");
                }
                else sb.append(hours).append(" hours, ");

                if(minutes == 1) {
                    sb.append(minutes).append(" minute and ");
                }
                else sb.append(minutes).append(" minutes and ");

                if(seconds == 1) {
                    sb.append(seconds).append(" second");
                }
                else sb.append(seconds).append(" seconds");

                if (duration > 10 * 60 * 60)
                    actDurationString.put(aName, sb.toString());
            }

            return actDurationString;
        };
        return obj.activityDuration(activityList);
    }

    public List<String> filterActivities(ArrayList<MonitoredData> activityList) {

        Map<String, Integer> actFreq = activityFrequency(activityList);
        Map<String, Long> lessThan5Min = activityList.stream().filter(element -> {
            long start = (element.getStart().getYear() - 2010) * 365 * 24 * 60 * 60
                    + (element.getStart().getMonth() - 1) * 30 * 24 * 60 * 60
                    + (element.getStart().getDay() - 1) * 24 * 60 * 60
                    + (element.getStart().getHour()) * 60 * 60 + (element.getStart().getMinute()) * 60
                    + (element.getStart().getSecond());
            long end = (element.getEnd().getYear() - 2010) * 365 * 24 * 60 * 60
                    + (element.getEnd().getMonth() - 1) * 30 * 24 * 60 * 60
                    + (element.getEnd().getDay() - 1) * 24 * 60 * 60 + (element.getEnd().getHour()) * 60 * 60
                    + (element.getEnd().getMinute()) * 60 + (element.getEnd().getSecond());
            long duration = end - start;
            return duration < 5 * 60;
        }).collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));

        return activityList.stream().filter(element -> {
            if (lessThan5Min.get(element.getActivity()) == null) {
                return false;
            } else return lessThan5Min.get(element.getActivity()) >= 0.9 * actFreq.get(element.getActivity());
        }).map(MonitoredData::getActivity).distinct().collect(Collectors.toList());
    }

    public void printTask2(ArrayList<MonitoredData> activityList) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("task_2.txt")));

            writer.write(Integer.toString(countDays(activityList).size()));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTask3(ArrayList<MonitoredData> activityList) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("task_3.txt")));

            Map<String, Integer> activityMap = activityFrequency(activityList);

            for (Entry<String, Integer> pair : activityMap.entrySet()) {
                String activityName = pair.getKey();
                int noOfOccurances = pair.getValue();

                writer.write("Activity: " + activityName + " -> " + noOfOccurances + " times" + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTask4(ArrayList<MonitoredData> activityList) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("task_4.txt")));

            Map<Integer, Map<String, Long>> freqOnDays = activityFrequencyOnDays(activityList);

            for (Entry<Integer, Map<String, Long>> pair : freqOnDays.entrySet()) {
                int day = pair.getKey();
                writer.write("Day " + day);
                writer.write("\n");

                for (Entry<String, Long> pair1 : pair.getValue().entrySet()) {
                    String activityName = pair1.getKey();
                    Long noOfOccurances = pair1.getValue();

                    if(noOfOccurances == 1) {
                        writer.write("       Activity: " + activityName + " happened " + noOfOccurances + " time" + "\n");
                    }
                    else writer.write("       Activity: " + activityName + " happened " + noOfOccurances + " times" + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTask5(ArrayList<MonitoredData> activityList) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("task_5.txt")));

            Map<String,String> activityDuration = activityDuration(activityList);

            for (Entry<String, String> pair : activityDuration.entrySet()) {
                String activityName = pair.getKey();
                String duration = pair.getValue();

                writer.write("Activity: " + activityName + " -> duration: " + duration + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTask6(ArrayList<MonitoredData> activityList) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("task_6.txt")));

            List<String> listOfActivities = filterActivities(activityList);
            for (String currentActivity : listOfActivities) {
                writer.write(currentActivity);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}