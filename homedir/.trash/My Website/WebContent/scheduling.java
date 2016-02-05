import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ymoswal on 11/13/2015.
 */
public class scheduling {
    static HashMap<String,HashMap<Integer,ArrayList<Integer>>> locationMap = new HashMap<String,HashMap<Integer,ArrayList<Integer>>>();
    // // String(key) is location id ..nested hashmap key is time unit..Array list is list of content ids
    public static void schedule(ScheduleRequestInputStream scheduleRequestsIn,
                                ScheduleRequestWriter scheduleRequestsOut,
                                ScheduleRequestWriter scheduleRequestsReject) throws IOException {


        while(scheduleRequestsIn.hasNext()){
            ScheduleRequest sc = scheduleRequestsIn.next();
            // to check if location id is there in hashmap
            checkLocationId(sc);
            //TO CHECK IF content ids are less than 3 during the time duration
            if(checkSize(sc)){
                //to check if there are no existing conflicting content ids
                if(checkConflict(sc)){
                    scheduleRequestsOut.writeScheduleRequest(sc);
                    addInMap(sc);
                    continue;
                }
            }
            scheduleRequestsOut.scheduleRequestsReject(sc);
        }
    }

    public static void checkLocationId(ScheduleRequest sc){
        // insert new location id  if location id is not present
        if(!locationMap.containsKey(sc.locationId)){
            HashMap<Integer,ArrayList<Integer>> timeMap = new HashMap<Integer,Arraylist>();
            locationMap.put(sc.locationId,timeMap);
        }
    }

    public static boolean checkSize(ScheduleRequest sc){
        HashMap<Integer,ArrayList<Integer>> temp = locationMap.get(sc.locationId);
        // checking from start time to end time
        for(int i=sc.startTime;i<=sc.endTime;i++){
            if(temp.containsKey(i)){
                // if the timeunit is present in the inner hashmap, get its corresponding arraylist.
                if(temp.get(i).size()>3){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkConflict(ScheduleRequest sc){
        HashMap<Integer,ArrayList<Integer>> temp = locationMap.get(sc.locationId);
        for(int i=sc.startTime;i<=sc.endTime;i++){
            if(temp.containsKey(i)){
                //if the timeunit is present in the inner hashmap, get its corresponding arraylist and check if it contains the content id
                if(temp.get(i).contains(sc.contentId){
                    return false;
                }
            }
        }
        return true;
    }
    // if all conditions satisfy then add in the location map
    public static void addInMap(ScheduleRequest sc){
        HashMap<Integer,ArrayList<Integer>> temp = locationMap.get(sc.locationId);
        for(int i=sc.startTime;i<=sc.endTime;i++){
            // add the key if not present in the hashmap
            if(!temp.containsKey(i)){
                ArrayList<Integer> contentIds = new ArrayList<Integer>();
                contentIds.add(sc.contentId);
                temp.put(i,contentIds);
            }else{
                temp.get(i).add(sc.contentId);
            }
        }
        locationMap.put(sc.locationId,temp);
    }

}
