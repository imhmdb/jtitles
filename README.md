# jtitles
a java implementation of a highly optimized cli subtitle editor implemented using sorted map (BST + DoubleLinkedList)

Subtitles are texts of dialogue or speeches translated into another language or a written text of the dialog in the same language Usually projected on the lower part of the screen. One of the ways to store subtitles is to have them in a separate file to work alongside with the video stream, there are many file formats that represents the subtitles and the simplest one of them is the SubRip format which is also known as (SRT) format.

This tool manipulates the subtitle ﬁles and manages them, the tool has many useful operations that helps the user to modify the subtitles as needed in a very efficient way by taking Performance as priority.

![class](https://user-images.githubusercontent.com/34659256/68526745-1969eb80-02f0-11ea-9f56-d9aa6111f316.png)

# Implementation

## The SortedBST
In a previous version of the tool we used a linkedList as a data structure to store and access the subtitles, we later found that using a linked list might be the slower option specially in large sizes of data, due to this fact we decided to use a Sorted Binary search tree to gain a much more faster performance, we conducted a performance test to our code using the LinkedList implementation and the SBST Implementation to see the deference between them, you can see in the table and graph below how huge is the difference between the LinkedList and the SBST. 

![comparison](https://user-images.githubusercontent.com/34659256/68526824-d0fefd80-02f0-11ea-951f-31b562ad54e3.jpg)

## addSubtitle(Subtitle st): 
Due to high efficiency of the SBST (Sorted binary search tree) ADT that we used, the method addSubtitle depends entirely on the insert method of the SBST, it adds in the subtitle in chronological order using the key (TimeInterval) .
```java
public void addSubtitle(Subtitle st) {
  Integer StartTimeInt = timeToMS(st.getStartTime());
  Integer EndTimeInt = timeToMS(st.getEndTime());
  TimeInterval TI = new TimeInterval(StartTimeInt,EndTimeInt);
  Subtitles.insert(TI,st);
}
```

## getSubtitle(Time time):
This method is a huge factor on the performance side, the searching time is now way more less than before, given credit to the Binary search tree which makes the Time Complexity	of the search Θ(log(n))
```java
public Subtitle getSubtitle(Time time) {
 Integer interval = timeToMS(time);
 TimeInterval timeinter = new TimeInterval(interval,interval);
   if(Subtitles.find(timeinter)) {
      return Subtitles.retrieve().second;
   }
   return null;
   
}
```

## SubtitleSeqFactory class
The class SubtitleSeqFactory works as the provider for the Subtitle Sequences (SubtitleSeq), it has only two methods, the important one among them is The method loadSubtitleSeq(String fileName) which reads an SRT file and convert it into a SubtitleSeq so the tool Can manage the Subtitles through it, The method uses a bufferedReader to read the file and then store it into a StringBuffer to save the whole file into an Array Of Strings where every index contains only one line of the file.
The SRT format Stores the time of a subtitle in a line that comes before the lines of text, the Time is stored in a format that looks exactly like this: ”00:00:35,536 --> 00:00:37,746” The start Time and End time are separated by the Arrow. In our implementation, we used the arrow to determine if we are in a Start of a new Subtitle using the method of The Class String .indexOf(String str).
```java
//the method .indexOF() returns -1 if the string does not contain //the substring otherwise, returns the index of the first character //of the String.
int Arrow = CurrentLine.indexOf("-->");
//if you're at a line that contains the arrow, then you're at the head of a new subtitle
//therefor you start extracting the Subtitle from the strings.
if (Arrow > -1) {
   String startTimeString = CurrentLine.substring(0, Arrow);
   String endTimeString = CurrentLine.substring(Arrow+4, CurrentLine.length());



//setting the Time Objects after extracting and parsing the Start      and end time from the strings
startTime.setHH(Integer.parseInt( startTimeString.substring(0,2)));
startTime.setMM(Integer.parseInt( startTimeString.substring(3,5)));
startTime.setSS(Integer.parseInt( startTimeString.substring(6,8)));
startTime.setMS(Integer.parseInt( startTimeString.substring(9,12)));

endTime.setHH(Integer.parseInt( endTimeString.substring(0,2)));
endTime.setMM(Integer.parseInt( endTimeString.substring(3,5)));
endTime.setSS(Integer.parseInt( endTimeString.substring(6,8)));
endTime.setMS(Integer.parseInt( endTimeString.substring(9,12)));
```

Knowing this, if we start reading the lines of the SRT file and found a line that contains the Arrow that separates the Start and End Times, we will Start Extracting and Parsing the time from the line of the Arrow, and then read the next lines to get the text until we reach an empty line which means that we are at the end of the subtitle and that we should start adding what we extracted into a Subtitle Object and then add it to a Subtitle Sequence using the method addSubtitle(Subtitle st) of the Class SubtitleSeqClass.

# more about this tool:
this tool was developed as a project for a data structres course in college, it can read SRT files and do many changes to the subtitles like adding new subtitles or shifting the time and cutting some subtitle ... etc all in a quick and time efficient way.
