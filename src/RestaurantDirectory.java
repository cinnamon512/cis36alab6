import java.beans.PropertyEditorSupport;

public class RestaurantDirectory {
    private Restaurant[] restaurants;
    private int size = 0;

//////////////////////////// A ////////////////////////////////////////////////////////

    public RestaurantDirectory(){
        size = 100;
        restaurants = new Restaurant[size];
    }

    public RestaurantDirectory(int capacity){
        size = capacity;
        restaurants = new Restaurant[size];
    }

////// /////////////////////////// 1 /////////////////////////////////////////////////////////

    public void addRestaurant(Restaurant r){
        restaurants[size] = r;
        size += 1;
    }

////////////////////////////////////// 2 ///////////////////////////////////////////////////////

    public Restaurant[] searchByOwnerName(String fn, String ln){
        int length = 0;
        for(int i = 0; i < size; i++){
            if(restaurants[i].getOwner().getFirstName().equals(fn) && restaurants[i].getOwner().getLastName().equals(ln)){
                length += 1;
            }
        }

        Restaurant[] match = new Restaurant[length];
        int counter = 0;

        for(int i = 0; i < size; i++){
            if(restaurants[i].getOwner().getFirstName().equals(fn) && restaurants[i].getOwner().getLastName().equals(ln)){
                match[counter] = restaurants[i];
                counter++;
            }
        }
        return match;
    }

//////////////////////////////////////////////// 3 ////////////////////////////////////////

    public Restaurant[] searchByName(String name){
        int length = 0;
        for(int i = 0; i < size; i++){
            if(restaurants[i].getName().equals(name)){
                length += 1;
            }
        }

        Restaurant[] match = new Restaurant[length];
        int counter = 0;

        for(int i = 0; i < size; i++){
            if(restaurants[i].getName().equals(name)){
                match[counter] = restaurants[i];
                counter++;
            }
        }
        return match;
    }

////////////////////////////////////////// 4 //////////////////////////////////

    public Restaurant[] searchByFoodType(String ft){
        int length = 0;
        for(int i = 0; i < size; i++){
            if(restaurants[i].getFoodType().equals(ft)){
                length += 1;
            }
        }

        Restaurant[] match = new Restaurant[length];
        int counter = 0;

        for(int i = 0; i < size; i++){
            if(restaurants[i].getFoodType().equals(ft)){
                match[counter] = restaurants[i];
                counter++;
            }
        }
        return match;
    }

///////////////////////////////////////////// 5 /////////////////////////////////////////////

    public double totalGrossRevenue(){
        double total = 0;
        for(int i = 0; i < size; i++){
            total += restaurants[i].getYearlyGrossRevenue();
        }
        return total;
    }

////////////////////////////////// 6 /////////////////////////////////////////////////////////////

    public double computeTaxRevenue(double tr){
        return this.totalGrossRevenue() * (tr + 1);
    }

//////////////////////////// 7 /////////////////////////////////////////////////

    public Restaurant[] searchByOwner(Person o){
        int length = 0;
        for(int i = 0; i < size; i++){
            if(restaurants[i].getOwner() == o){
                length += 1;
            }
        }

        Restaurant[] match = new Restaurant[length];
        int counter = 0;

        for(int i = 0; i < size; i++){
            if(restaurants[i].getOwner() == o){
                match[counter] = restaurants[i];
                counter++;
            }
        }
        return match;
    }

//////////////////////////////////////// 8 //////////////////////////////////////////////

    public boolean addRestaurants(Restaurant[] rs){
        if(size + rs.length > restaurants.length){
            return false;
        }

        for(int i = 0; i < rs.length; i++){
            restaurants[size] = rs[i];
            size++;
        }

        //works
//        int counter = 0;
//        for(int i = size; i < size + rs.length; i++){
//            restaurants[i] = rs[counter];
//            size++;
//        }
        return true;
    }

    /// /////////////////////////// 9 //////////////////////////////////////////////////////

    public Person[] findDuplicateOwners(){

        Person[] compare = new Person[size];
        int length = 0;

        for(int i = 0; i < size; i++){

            Person owner = restaurants[i].getOwner();
            int duplicate = 0;

            //scans array to compare owners
            for (int j = i + 1; j < size; j++){  ///// int j = 0
                if(owner == restaurants[j].getOwner()){
                    duplicate++;
                }
            }

            // If there is a duplicate
            if(duplicate > 0){  //// duplicate > 1
                boolean added = false;


                //check to see if already in list
                for (int k = 0; k < length; k++){
                    if (compare[k] == owner){
                        added = true;
                        break;
                    }
                }

                // adds to list if not already there
                if(!added){
                    compare[length] = owner;
                    length++;
                }
            }

        }

        // Creates the array with correct size to return
        Person[] duplicateOwners = new Person[length];

        for(int i = 0; i < length; i++){
            duplicateOwners[i] = compare[i];
        }

        return duplicateOwners;
    }
    
}
