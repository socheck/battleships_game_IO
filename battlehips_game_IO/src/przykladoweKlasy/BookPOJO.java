package przykladoweKlasy;

public class BookPOJO {
    private String title;
    private boolean inPrint;
    private  Integer saleAmount;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookPOJO{" +
                "title='" + title + '\'' +
                ", inPrint=" + inPrint +
                ", saleAmount=" + saleAmount +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isInPrint() {
        return inPrint;
    }

    public void setInPrint(boolean inPrint) {
        this.inPrint = inPrint;
    }

    public Integer getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }
}
