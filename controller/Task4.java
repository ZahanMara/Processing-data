package controller;

import java.util.ArrayList;
import java.util.Map;

import model.MonitoredData;

public interface Task4 {

    Map<Integer , Map<String,Integer>> ActivityFrequencyOnDays(ArrayList<MonitoredData> activityList);
}
