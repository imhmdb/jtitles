
public class SubtitleSeqClass implements SubtitleSeq{
    private SortedBST<TimeInterval,Subtitle> Subtitles = new SortedBST<>();

    private int timeToMS(Time t){
        return (t.getHH()*60*60*1000)+(t.getMM()*60*1000)+(t.getSS()*1000)+t.getMS();
    }
    
    @Override
    public void addSubtitle(Subtitle st) {
        Integer StartTimeInt = timeToMS(st.getStartTime());
        Integer EndTimeInt = timeToMS(st.getEndTime());
        TimeInterval TI = new TimeInterval(StartTimeInt,EndTimeInt);
        Subtitles.insert(TI,st);
    }

    @Override
    public List<Subtitle> getSubtitles() {
        List<Subtitle> subs = new LinkedList<Subtitle>();
        if(!Subtitles.empty()){
        	Subtitles.findFirst();
            while(!Subtitles.last()){
                subs.insert(Subtitles.retrieve().second);
                Subtitles.findNext();
            }
            subs.insert(Subtitles.retrieve().second);
        }
        return subs;
    }

    @Override
    public Subtitle getSubtitle(Time time) {
    	Integer interval = timeToMS(time);
    	TimeInterval timeinter = new TimeInterval(interval,interval);
    	if(Subtitles.find(timeinter)) {
    		return Subtitles.retrieve().second;
    	}
    	return null;
    	
    }

    @Override
    public int nbNodesInSearchPath(Time time) {
        int counter = 0;
        if(!Subtitles.empty()) {
        	Integer interval = timeToMS(time);
         	TimeInterval timeinter = new TimeInterval(interval,interval);
         	counter = Subtitles.nbNodesInSearchPath(timeinter);
        }
        return counter;
    }

    @Override
    public List<Subtitle> getSubtitles(Time startTime, Time endTime) {
        LinkedList<Subtitle> subsAtTimes = new LinkedList<Subtitle>();
        if(!Subtitles.empty()) {
            Subtitles.findFirst();
            while (!Subtitles.last()) {
                boolean startIslessThanOrEqualToEndOfSub = timeToMS(startTime)<=timeToMS(Subtitles.retrieve().second.getEndTime());
                boolean endIsMoreOrEqualToStartOfSub = timeToMS(endTime) >= timeToMS(Subtitles.retrieve().second.getStartTime()) ;

                if (startIslessThanOrEqualToEndOfSub&&endIsMoreOrEqualToStartOfSub) {
                    subsAtTimes.insert(Subtitles.retrieve().second);
                }
                Subtitles.findNext();
            }
            boolean startIslessThanOrEqualToEndOfSub = timeToMS(startTime)<=timeToMS(Subtitles.retrieve().second.getEndTime());
            boolean endIsMoreOrEqualToStartOfSub = timeToMS(endTime) >= timeToMS(Subtitles.retrieve().second.getStartTime()) ;
            if (startIslessThanOrEqualToEndOfSub&&endIsMoreOrEqualToStartOfSub) {
                subsAtTimes.insert(Subtitles.retrieve().second);
            }
        }
        return subsAtTimes;
    }

    @Override
    public void shift(int offset) {
        if(!Subtitles.empty()) {
            Subtitles.findFirst();
            int seconds = (offset / 1000) % 60;
            int minutes = (offset / (1000 * 60)) % 60;
            int hours = (offset/(1000*60*60));
            int ms = offset%1000;

            while (!Subtitles.last()) {
                Time start = Subtitles.retrieve().second.getStartTime();
                Time end = Subtitles.retrieve().second.getEndTime();
                if (timeToMS(start) + (offset) < 0) {
                    start.setHH(0);
                    start.setMM(0);
                    start.setSS(0);
                    start.setMS(0); }
                else{
                    start.setHH(start.getHH() + hours);
                    start.setMM(start.getMM() + minutes);
                    start.setSS(start.getSS() + seconds);
                    start.setMS(start.getMS() + ms);
                }
                if (timeToMS(end) + (offset) <= 0) {
                    Subtitles.remove();
                } else {
                    end.setHH(end.getHH() + hours);
                    end.setMM(end.getMM() + minutes);
                    end.setSS(end.getSS() + seconds);
                    end.setMS(end.getMS() + ms);
                    Subtitles.findNext();
                }
            }

            Time start = Subtitles.retrieve().second.getStartTime();
            Time end = Subtitles.retrieve().second.getEndTime();

            if (timeToMS(start) + (offset) < 0) {
                start.setHH(0);
                start.setMM(0);
                start.setSS(0);
                start.setMS(0);
            } else {
                start.setHH(start.getHH() + hours);
                start.setMM(start.getMM() + minutes);
                start.setSS(start.getSS() + seconds);
                start.setMS(start.getMS() + ms); }

            if (timeToMS(end) + (offset) <= 0) {
                Subtitles.remove();
            } else {
                end.setHH(end.getHH() + hours);
                end.setMM(end.getMM() + minutes);
                end.setSS(end.getSS() + seconds);
                end.setMS(end.getMS() + ms);
            }
        }
    }
}
