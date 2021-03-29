package controller;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class FileReader {

    private ArrayList<MonitoredData> activityList = new ArrayList<MonitoredData>();

    public void readFromFile(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            Iterator<String> itr = stream.iterator();
            while (itr.hasNext()) {
                String currentLine = itr.next();

                String[] myLine = currentLine.split("\\t+");
                String start_time = myLine[0];
                String end_time = myLine[1];
                String activity = myLine[2];

                // START MOMENT
                String[] start_moment = start_time.split("\\s+");

                StringTokenizer date = new StringTokenizer(start_moment[0], "-");
                int year = Integer.parseInt(date.nextToken());
                int month = Integer.parseInt(date.nextToken());
                int day = Integer.parseInt(date.nextToken());

                StringTokenizer time = new StringTokenizer(start_moment[1], ":");
                int hour = Integer.parseInt(time.nextToken());
                int minute = Integer.parseInt(time.nextToken());
                int second = Integer.parseInt(time.nextToken());

                Time start = new Time(year, month, day, hour, minute, second);

                // END MOMENT
                String[] end_moment = end_time.split("\\s+");

                StringTokenizer date1 = new StringTokenizer(end_moment[0], "-");
                int year1 = Integer.parseInt(date1.nextToken());
                int month1 = Integer.parseInt(date1.nextToken());
                int day1 = Integer.parseInt(date1.nextToken());

                StringTokenizer time1 = new StringTokenizer(end_moment[1], ":");
                int hour1 = Integer.parseInt(time1.nextToken());
                int minute1 = Integer.parseInt(time1.nextToken());
                int second1 = Integer.parseInt(time1.nextToken());

                Time end = new Time(year1, month1, day1, hour1, minute1, second1);

                // ADD ACTIVITY TO THE LIST
                MonitoredData data = new MonitoredData(start, end, activity);

                this.activityList.add(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MonitoredData> getActivityList() {
        return activityList;
    }

    public void setActivityList(ArrayList<MonitoredData> activityList) {
        this.activityList = activityList;
    }
}
