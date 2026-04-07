public class Demonstrator {
    public static void main(String[] args){

        //1
        PhoneNumber p1 = new PhoneNumber("510","555","5555");

        Person owner1 = new Person("Michael", "Seidel", "01-12-2000", "123 Center Street", p1, "American");


        Restaurant restaurant1 = new Restaurant("Michael's Bistro", "American Fusion", "123 Center Street", owner1, p1, 1893091, 220000);

        PhoneNumber p2 = new PhoneNumber("8009990101");

        Person owner2 = new Person("Bobo", "the Clown");

        Restaurant restaurant2 = new Restaurant("Pizza Funktown", "pizza", "5000 Park Blvd", owner2, p2, 9838741, 150000);

        Person owner3 = new Person("Bobo", "the Clown", "01-01-0101", "Clownian");

        //1

        System.out.println();

        System.out.println(restaurant1.invite());
        System.out.println();
        System.out.println(restaurant1.getTaxInfo());
        System.out.println();

        //2
        System.out.println(restaurant2.invite());
        System.out.println();
        System.out.println(restaurant2.getTaxInfo());
        System.out.println();


    }
}