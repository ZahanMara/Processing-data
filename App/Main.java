package App;

import java.util.ArrayList;
import controller.FileReader;
import controller.MainController;
import model.MonitoredData;

public class Main {

    public static void main(String[] args) {

        String fileName = "Activities.txt";
        FileReader inputFile = new FileReader();
        inputFile.readFromFile(fileName); // it creates a list of objects of type MonitoredData

        ArrayList<MonitoredData> activityList = inputFile.getActivityList();

       MainController controller = new MainController();
       controller.printTask2(activityList);
       controller.printTask3(activityList);
       controller.printTask4(activityList);
       controller.printTask5(activityList);
       controller.printTask6(activityList);
    }
}
