package ir.bigz.ms.searchfilter.book.filter;

public class BookFilterRequest {

    private String title;
    private String sbn;

    public BookFilterRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSbn() {
        return sbn;
    }

    public void setSbn(String sbn) {
        this.sbn = sbn;
    }
}
