
public class maintesting {
	 private static int timeToMS(Time t){
	        return (t.getHH()*60*60*1000)+(t.getMM()*60*1000)+(t.getSS()*1000)+t.getMS();
	    }
	public static void main(String[] args) {
		SubtitleSeq seq = SubtitleSeqFactory.getSubtitleSeq();
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "17", "040"), new TimeClass("00", "02", "18", "724"), "Monsieur Rom..."));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "25", "120"), new TimeClass("00", "02", "26", "531"), "Opar."));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "27", "520"), new TimeClass("00", "02", "29", "409"), "INHALES DEEPLY"));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "29", "480"), new TimeClass("00", "02", "31", "721"), "We found it."));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "40", "160"), new TimeClass("00", "02", "41", "446"), "Captain Moulle?"));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "46", "200"), new TimeClass("00", "02", "48", "089"), "Form your lines."));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "48", "480"), new TimeClass("00", "02", "49", "527"), "(GUNS COCKING)"));
		seq.addSubtitle(new SubtitleClass(new TimeClass("00", "02", "55", "800"), new TimeClass("00", "02", "57", "609"), "Maxims ready, sir!"));
		
		List<Subtitle> a = seq.getSubtitles();
		a.findFirst();
		while(!a.last()) {
			System.out.println("Text:");
			System.out.println(a.retrieve().getText());
			System.out.println("Time, start, end:");
			System.out.println("START  ,"+timeToMS(a.retrieve().getStartTime()));
			System.out.println("END  ,"+timeToMS(a.retrieve().getEndTime()));
			a.findNext();
		}
	
		System.out.println("Text:");
		System.out.println(a.retrieve().getText());
		System.out.println("Time, start, end:");
		System.out.println("START  ,"+timeToMS(a.retrieve().getStartTime()));
		System.out.println("END  ,"+timeToMS(a.retrieve().getEndTime()));
		
		seq.shift(-150000);
		
		List<Subtitle> b = seq.getSubtitles();
		b.findFirst();
		while(!b.last()) {
			System.out.println("Text:");
			System.out.println(b.retrieve().getText());
			System.out.println("Time, start, end:");
			System.out.println("START  ,"+timeToMS(b.retrieve().getStartTime()));
			System.out.println("END  ,"+timeToMS(b.retrieve().getEndTime()));
			b.findNext();
		}
	
		System.out.println("Text:");
		System.out.println(b.retrieve().getText());
		System.out.println("Time, start, end:");
		System.out.println("START  ,"+timeToMS(b.retrieve().getStartTime()));
		System.out.println("END  ,"+timeToMS(b.retrieve().getEndTime()));
		
		

	
	}


}