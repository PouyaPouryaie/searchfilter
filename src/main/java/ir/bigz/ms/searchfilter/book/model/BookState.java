package ir.bigz.ms.searchfilter.book.model;

public enum BookState {
    STOCK(0),SELL(1),EMPTY(2);
    int state;
    BookState(int state) {this.state = state;}
}
