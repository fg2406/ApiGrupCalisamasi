package pojos;

public class BookingPojo {

  /**
  {
 "firstname": "Selim",
 "lastname": "Ak",
 "totalprice": 15000,
 "depositpaid": true,
 "bookingdates": {
 "checkin": "2020-09-09",
 "checkout": "2020-09-21"
 }

   */

  //pojo da datalari olusturmak icin once ic ten basladik,baska classta,simdi
    //diger datalr icin olusturduk

    private String firstname;
    private String lastname;
    private  int totalprice;
    private  boolean depositpaid;
    private BookingsDatesPojo bookingdates; //daha once olusturdugumuz pojo classindaki
    //pojo kalibini datatype olarak belirterekyeni pojonun icerisine gomduk

     //getter ve setter

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingsDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingsDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    //constructor olusturma,parametre ve parametresiz
    public BookingPojo() {
    }

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingsDatesPojo bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }

    //toString


    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
