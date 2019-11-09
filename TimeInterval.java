public class TimeInterval implements Comparable<TimeInterval> {
Integer endTime;
Integer startTime;


    public TimeInterval(Integer startTime, Integer endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
	public int compareTo(TimeInterval that) {
		if (startTime.compareTo(that.endTime) > 0) {
			return 1;
		}
		if (endTime.compareTo(that.startTime) < 0) {
			return -1;
		}
		return 0;
    		}
    	
}
