package controller;

import java.util.ArrayList;
import java.util.Map;

import model.MonitoredData;

public interface Task3 {

    Map<String, Integer> ActivityFrequency(ArrayList<MonitoredData> activityList);
}
