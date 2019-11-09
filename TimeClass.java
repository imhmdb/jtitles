public class TimeClass implements Time {
    private int HH, MM, SS, MS;

    public TimeClass() {
    }

    public TimeClass(String hh, String mm, String ss, String ms) {
		this.HH = Integer.parseInt(hh);
		this.MM = Integer.parseInt(mm);
		this.SS = Integer.parseInt(ss);
		this.MS = Integer.parseInt(ms);
	}

    public int getHH() {
        return HH;
    }

    public int getMM() {
        return MM;
    }

    public int getSS() {
        return SS;
    }

    public int getMS() {
        return MS;
    }

    public void setHH(int hh) {
        this.HH = hh;
    }

    public void setMM(int mm) {
    	if(mm>=0){
        this.MM = mm;
    	}
    	else{
    		 this.HH = this.HH -1;
    		 this.MM = 60 + mm;
    	}
    }

    public void setSS(int ss) {
    	if(ss>=0){
        this.SS = ss;
    	}
    	else{
    		this.setMM(this.MM-1);
    		this.SS = 60 + ss;
    	}
    }

    public void setMS(int ms) {
    	if(ms>=0){
        this.MS = ms;
    	}
    	else{
    		this.setMM(this.MM -1);
    		this.MS = 1000 + ms;
    	}
    }
}

