/*public static Comparator<ScheduleRequest> requestComparator = new Comparator<ScheduleRequest>() {
	@Override
	public int compare(ScheduleRequest lhs, ScheduleRequest rhs){

	}
}*/



public static optimize(ScheduleRequestInputStream scheduleIn,
			OptimizationRequestInputStream optimizationsRequestsIn,
			ScheduleRequestWriter optimizationOut, 
			ContentScoreMap contentScoreMap,
			LocationValueMap locationValueMap) {

			//TreeMap to store all the otimization request with key being duaration of all possible optimazation requests 
			Map<Integer,LinkedList<OptimizationRequest>> optimizeMap = new HashMap<Integer,LinkedList<OptimizationRequest>>();

			//This is a hashmap of schedule requests with outer hashmap having locationId as key and inner hashmap has contentId as key and its corresponding 
			//end time as value
			HashMap<String,HashMap<String,ScheduleRequest>> locationContentMap = new HashMap<String, HashMap<String, ScheduleRequest>>();

			HashMap<String, ArrayList<Integer>> locationEndtimeMap = new HashMap<String, ArrayList<Integer>>();
			initialize(locationEndtimeMap);


			//Filling the optimation request map
			while(optimizationsRequestsIn.hasNext()){
				OptimizationRequest op = optimizationsRequestsIn.next();
				if(optimizeMap.get(op.duration==null)){
					LinkedList<OptimizationRequest> optimizeList = new LinkedList<OptimizationRequest>();
					optimizeMap.put(op.duration,temp);
				}
				LinkedList temp = optimizeMap.get(op.duration);
				temp.add(op);
				optimizeMap.put(op.duration,temp);
			}

			//
			while(scheduleIn.hasNext()){
				ScheduleRequest sc = scheduleIn.next();
				if(locationContentMap.get(sc.locationId)==null){
					HashMap<String,ScheduleRequest> contentMap = new HashMap<String,ScheduleRequest>();
					locationContentMap.put(sc.locationId,contentMap);
				}
				HashMap<String,ScheduleRequest> temp = locationContentMap.get(sc.locationId);
				//if(temp.size()<3){
				int minTime = findMinEndTime(localtionMap.get(sc.locationId));
					OptimizationRequest found = findOptimizationRequest(sc.startTime-minTime,sc.locationId,minTime);
						if(found!=null){
							optimizationOut(found.contentId,sc.locationId,sc.startTime,(sc.startTime+found.duration))
						}
					temp.put(sc.contentId,sc.endTime);
					locationContentMap.put(sc.locationId,temp);
				//}
				else{
					 Map.Entry<String,Integer> minEntry = findMinEndTime(temp);
					 minEndTime = minEntry.getValue();
					if(sc.startTime-minEndTime>0){
						OptimizationRequest found = findOptimizationRequest(sc.startTime-minEndTime,sc.locationId);
						if(found!=null){
							optimizationOut(found.contentId,sc.locationId,sc.startTime,(sc.startTime+found.duration))
						}
						
					}
					replaceWithEndtime(minEntry,sc);
				}

			}

	public static OptimizationRequest findOptimizationRequest(int gap,String locationId,int startTime){
		if(gap==0){
			return null;
		}
		if(optimizeMap.get(gap).size()!=0){
			do{
				OptimizationRequest temp = optimizeMap.get(gap).pop(); //removing the first possible optimizationrequest
				int endTime = startTime + temp.duration;
				if(locationContentMap.get(locationId).containsKey(temp.contentId)){
					if(locationContentMap.get(locationId).get(temp.contentId).startTime>startTime){
						if(locationContentMap.get(locationId).get(temp.contentId).startTime>endTime){
							return temp;
						}else{
							optimizeMap.get(gap).add(temp);
							continue;
						}
					}else if(locationContentMap.get(locationId).get(temp.contentId).endTime<startTime){
						return temp;
					}
					else{
						//Its a conflict add the request to the map again
						optimizeMap.get(gap).add(temp);
						continue;
					}
				}else{
					return temp;
				}
			}while(optimizeMap.get(gap).hasNext()); //request conflict go to the next request in the bucket.

			//No optimize request found in this bucket go to the next bucket
			return findOptimizationRequest(gap-1,locationId);
		}
		//No request in this bucket go to next bucket
		else{
			return findOptimizationRequest(gap-1,locationId);
		}
	}

	public static Integer findMinEndTime(HashMap<String,Integer>temp){
		if(temp==null){
			return null;
		}
		int Map.Entry<String,Integer> minEntry= new Map.Entry<String,Integer>();
		int min =MAX_NUM; 
		for (Map.Entry<String, Integer> entry : temp.entrySet()){
			if(entry.getValue()<min){
				minEntry=entry;
		}
		return minEntry;
	}

	public static void replaceWithEndtime(Map.Entry<String,Integer> oldEntry,ScheduleRequest newRequest){
		locationContentMap.get(newRequest.locationId).remove(oldEntry.key);
		locationContentMap.get(newRequest.locationId).put(newRequest.contentId,newRequest.endTime);
	}

	public static ArrayList<Integer> triplet(){
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(0);
		list.add(0);
		list.add(0);
		return list;
	}

	public static int TripletMin(ArrayList<Integer> tuple){
		int min=0;
		if(tuple.size()!=3) System.out.println("tuple size wrong here");
		for(int i=0; i<tuple.size();i++){
			min=tuple.get(min)<tuple.get(i)?min:i;
		}
		return min;
	}
	
}