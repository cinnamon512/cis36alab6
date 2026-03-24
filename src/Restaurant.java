public class Restaurant {
    private String name;
    private String foodType;
    private String address;
    private Person owner;
    private PhoneNumber phoneNumber;
    private int businessLicenseNumber;
    private double yearlyGrossRevenue;

    public void setName(String newName){
        this.name = newName;
    }
    public String getName(){
        return this.name;
    }

    public void setFoodType(String newFoodType){
        this.foodType = newFoodType;
    }
    public String getFoodType(){
        return this.foodType;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }
    public String getAddress(){
        return this.address;
    }

    public void setOwner(Person newOwner){
        this.owner = newOwner;
    }
    public Person getOwner(){
        return this.owner;
    }

    public void setPhoneNumber(PhoneNumber newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }
    public PhoneNumber getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setBusinessLicenseNumber(int newBusinessLicenseNumber){
        this.businessLicenseNumber = newBusinessLicenseNumber;
    }
    public int getBusinessLicenseNumber(){
        return this.businessLicenseNumber;
    }

    public void setYearlyGrossRevenue(double newYearlyGrossRevenue){
        this.yearlyGrossRevenue = newYearlyGrossRevenue;
    }
    public double getYearlyGrossRevenue(){
        return this.yearlyGrossRevenue;
    }

    public Restaurant(String name, String foodType, String address, Person owner, PhoneNumber phoneNumber, int businessLicenseNumber, double yearlyGrossRevenue){
        setName(name);
        setFoodType(foodType);
        setAddress(address);
        setOwner(owner);
        setPhoneNumber(phoneNumber);
        setBusinessLicenseNumber(businessLicenseNumber);
        setYearlyGrossRevenue(yearlyGrossRevenue);
    }

    public String invite(){
        return "Come on down to " + getName() + ", a restaurant serving " + getFoodType() + ", located at " + getAddress() + "!";
    }

    public String getTaxInfo(){
        return getName() + " / " + getOwner().getFullName() + " / " + getBusinessLicenseNumber() + " / " + getYearlyGrossRevenue();
    }

}
