package model;

public class MonitoredData {

    private Time start;
    private Time end;
    private String activity;

    public MonitoredData(Time start, Time end, String activity) {
        this.start = start;
        this.end = end;
        this.activity = activity;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

