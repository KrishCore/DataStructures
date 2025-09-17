
public class Job {
    private int jobNumber;
    private int submissionTime;
    private int pages;
    private int bufferingStart;
    private int bufferingEnd;
    private int printStart;
    private int printEnd;

    public Job(int jobNumber, int submissionTime, int pages) {
        this.jobNumber = jobNumber;
        this.submissionTime = submissionTime;
        this.pages = pages;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public int getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(int submissionTime) {
        this.submissionTime = submissionTime;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getBufferingStart() {
        return bufferingStart;
    }

    public void setBufferingStart(int bufferingStart) {
        this.bufferingStart = bufferingStart;
    }

    public int getBufferingEnd() {
        return bufferingEnd;
    }

    public void setBufferingEnd(int bufferingEnd) {
        this.bufferingEnd = bufferingEnd;
    }

    public int getPrintStart() {
        return printStart;
    }

    public void setPrintStart(int printStart) {
        this.printStart = printStart;
    }

    public int getPrintEnd() {
        return printEnd;
    }

    public void setPrintEnd(int printEnd) {
        this.printEnd = printEnd;
    }
}
