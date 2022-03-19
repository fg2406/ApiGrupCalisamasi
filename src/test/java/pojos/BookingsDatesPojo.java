package pojos;

public class BookingsDatesPojo {

  /**
  "bookingdates": {
        "checkin": "2022-03-01",
                "checkout": "2022-03-11"
    }
     */

  //private data
  private  String checkin;
  private String checkout;


  //getter setter
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //3.adim Constucter,parametre ve parametresiz

    public BookingsDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingsDatesPojo() {
    }

    //4.adim toString kaliba dokmek icin
    @Override
    public String toString() {
        return "BookingsDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
