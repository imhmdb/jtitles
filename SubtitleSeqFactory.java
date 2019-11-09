import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SubtitleSeqFactory {

	// Return an empty subtitles sequence 
	public static SubtitleSeq getSubtitleSeq() {
		return new SubtitleSeqClass();
	}

	 private static int timeToMS(Time t){
	        return (t.getHH()*60*60*1000)+(t.getMM()*60*1000)+(t.getSS()*1000)+t.getMS();
	    }
	// Load a subtitle sequence from an SRT file. If the file does not exist or
	// is corrupted (incorrect format), null is returned.
	public static SubtitleSeqClass loadSubtitleSeq(String fileName) {
		if(!fileName.toLowerCase().contains(".srt")){
			return null;
		}
		
		String subLines[];
		int numberOfLines;
		SubtitleSeqClass subSeq = new SubtitleSeqClass();
		//reads the file into a StringBuffer
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			return null;
		}

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line).append("\n");
			}
		} catch (IOException e) {
			return null;
		}

		//Converts the stringBuffer into an array of Strings, it splits every line as a new string in the array.
		subLines = stringBuffer.toString().split("\n");
		String[] correctCheckingStrings = stringBuffer.toString().split("\n\n");
		int numberOfchecking = correctCheckingStrings.length;
		numberOfLines = subLines.length;
		
		//We check if the file is in correct syntax
		if(numberOfchecking>0){
			for(int c = 0; c<numberOfchecking; c++){
				String[] checking = correctCheckingStrings[c].split("\n");
				if(checking.length>0){
				try{
					int one = Integer.parseInt(checking[1].substring(0, 2));
					int two = Integer.parseInt(checking[1].substring(3, 5));
					int three = Integer.parseInt(checking[1].substring(6, 8));
					int four = Integer.parseInt(checking[1].substring(9, 12));
					int five = Integer.parseInt(checking[1].substring(17, 19));
					int six = Integer.parseInt(checking[1].substring(20, 22));
					int seven = Integer.parseInt(checking[1].substring(23, 25));
					int eight = Integer.parseInt(checking[1].substring(26, 29));
					if(!checking[1].contains("-->")){
						return null;
					}
				}
				catch(Exception e){
					return null;
					}
				}else{return null;}
			}
		}else{return null;}
		
		
		//if The file has 0 line, nothing to do here. otherwise, continue.
		if (numberOfLines > 0) {
			String CurrentLine = "";
			 String previousLine = subLines[0];
			 int id = 1;
			 
			 try{ if(Integer.parseInt (previousLine.trim())!=1){
				 return null;
			 }}
			 catch(NumberFormatException e){
				 return null;
			 }
			

			for (int i=1; i<numberOfLines; i++) {
				Subtitle temp = new SubtitleClass();
				Time startTime = new TimeClass();
				Time endTime = new TimeClass();
				CurrentLine = subLines[i];
				// the method . indexOF() returns -1 if the string does not contain the subString
				// otherwise, returns the index of the first character of the String
				int Arrow = CurrentLine.indexOf("-->");
				//if you're at a line that contains the arrow, then you're at the head of a new subtitle
				//Therefore you start extracting the Subtitle from the strings.
				if (Arrow > -1) {
					String startTimeString = CurrentLine.substring(0, Arrow);
					String endTimeString = CurrentLine.substring(Arrow+4, CurrentLine.length());
				
					try{if(Integer.parseInt (subLines[i-1].trim())!=id){
						return null;
						}}
					catch(NumberFormatException e){return null;}
					
					id++;
					//setting the Time Objects after extracting and parsing the Start and end time from the strings
					try{
					startTime.setHH(Integer.parseInt( startTimeString.substring(0,2)));
					startTime.setMM(Integer.parseInt( startTimeString.substring(3,5)));
					startTime.setSS(Integer.parseInt( startTimeString.substring(6,8)));
					startTime.setMS(Integer.parseInt( startTimeString.substring(9,12)));

					endTime.setHH(Integer.parseInt( endTimeString.substring(0,2)));
					endTime.setMM(Integer.parseInt( endTimeString.substring(3,5)));
					endTime.setSS(Integer.parseInt( endTimeString.substring(6,8)));
					endTime.setMS(Integer.parseInt( endTimeString.substring(9,12)));
					}
					catch(NumberFormatException e){
						return null;
					}
					

					String text = "";
					int numberOfTextLines = i;
					String nextLine = CurrentLine;
					while ((numberOfTextLines<(numberOfLines-1)) && (nextLine.length() != 0)){
						numberOfTextLines++;
						nextLine = subLines[numberOfTextLines];
						text += "\n"+nextLine;
						text = text.trim();
					}
					if(timeToMS(startTime)>timeToMS(endTime)){
						return null;
					}
					//we Assign the information we extracted into a Subtitle object, and then we add it to the Subtitles List.
					temp.setStartTime(startTime);
					temp.setEndTime(endTime);
					if(text.length()!=0){
						temp.setText(text);
					}else{
						return null;
					}
					
					subSeq.addSubtitle(temp);
				}
			}
			return subSeq;
		}
		return null;
	}
}
