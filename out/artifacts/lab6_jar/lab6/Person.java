public class Person {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private PhoneNumber phoneNumber;
    private String nationality;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String newBirthDate) {
        this.birthDate = newBirthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String newNationality) {
        this.nationality = newNationality;
    }

    public Person() {
        setFirstName("First");
        setLastName("Last");
        setBirthDate("00-00-0000");
        setAddress("123 A Street");
        setPhoneNumber(new PhoneNumber());
        setNationality("Country");
    }

    public Person(String firstName, String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(String firstName, String lastName, String birthDate, String nationality){
        this(firstName, lastName);
        setBirthDate(birthDate);
        setNationality(nationality);
    }

    public Person(String firstName, String lastName, String birthDate, String address, PhoneNumber phoneNumber, String nationality){
        this(firstName, lastName, birthDate, nationality);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public String prettyPrint(){
        return getFirstName() + " " + getLastName() + "\n" + getBirthDate() + "\n" + getAddress() + "\n" + phoneNumber.prettyPrint() + "\n" + getNationality();
    }
}
