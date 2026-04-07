// Lab 6 Tests
// Please see "Using JUnit in IntelliJ" in the bottom-most module to use this!
// v2: March 24, 2026

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;

import static javax.swing.UIManager.getInt;
import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class Lab6Tests {

        @Nested
        @Order(1)
        @DisplayName("PhoneNumber Tests")
        class PhoneNumberTest {

            private String ac = "234";
            private String ex = "567";
            private String li = "8901";

            @BeforeEach
            void setUp() {
                PhoneNumber.setAreaCodeDesignatorLeft("(");
                PhoneNumber.setAreaCodeDesignatorRight(")");
                PhoneNumber.setAreaCodeDesignatorPost(" ");
                PhoneNumber.setExchangeLineSeparator("-");
            }

            @AfterEach
            void tearDown() {
            }

            @Test
            @DisplayName("Test Static Variable Default Values")
            void testStaticVars() {
                assertEquals(PhoneNumber.getAreaCodeDesignatorLeft(), "(");
                assertEquals(PhoneNumber.getAreaCodeDesignatorRight(), ")");
                assertEquals(PhoneNumber.getAreaCodeDesignatorPost(), " ");
                assertEquals(PhoneNumber.getExchangeLineSeparator(), "-");
            }

            @Test
            @DisplayName("Test Static Setters and Getters")
            void testStaticSettersGetters() {
                PhoneNumber.setAreaCodeDesignatorLeft("((");
                assertEquals(PhoneNumber.getAreaCodeDesignatorLeft(), "((");
                PhoneNumber.setAreaCodeDesignatorRight("))");
                assertEquals(PhoneNumber.getAreaCodeDesignatorRight(), "))");
                PhoneNumber.setAreaCodeDesignatorPost("...");
                assertEquals(PhoneNumber.getAreaCodeDesignatorPost(), "...");
                PhoneNumber.setExchangeLineSeparator("---");
                assertEquals(PhoneNumber.getExchangeLineSeparator(), "---");
            }


            @Test
            @DisplayName("Three-String Constructor")
            void testConstructor1() {
                PhoneNumber pn = new PhoneNumber(ac, ex, li);
                assertEquals(ac, pn.getAreaCode());
                assertEquals(ex, pn.getExchange());
                assertEquals(li, pn.getLine());
            }

            @Test
            @DisplayName("One-String Constructor")
            void testConstructor2() {
                PhoneNumber pn = new PhoneNumber(ac+ex+li);
                assertEquals(ac, pn.getAreaCode());
                assertEquals(ex, pn.getExchange());
                assertEquals(li, pn.getLine());
            }

            @Test
            void setAreaCode() {
                PhoneNumber pn = new PhoneNumber(ac, ex, li);
                pn.setAreaCode("992");
                assertEquals("992", pn.getAreaCode());
            }

            @Test
            void setExchange() {
                PhoneNumber pn = new PhoneNumber(ac, ex, li);
                pn.setExchange("398");
                assertEquals("398", pn.getExchange());
            }

            @Test
            void setLine() {
                PhoneNumber pn = new PhoneNumber(ac, ex, li);
                pn.setLine("1441");
                assertEquals("1441", pn.getLine());
            }

            @Test
            @DisplayName("prettyPrint With Default Static Variable Values")
            void prettyPrintDefaultSeparators() {
                PhoneNumber pn = new PhoneNumber(ac, ex, li);
                String expected = String.format("(%s) %s-%s", ac, ex, li);
                assertEquals(expected, pn.prettyPrint());
            }

            @Test
            @DisplayName("prettyPrint With Custom Static Variable Values")
            void prettyPrintCustomSeparators() {
                PhoneNumber.setAreaCodeDesignatorLeft("((");
                PhoneNumber.setAreaCodeDesignatorRight("))");
                PhoneNumber.setAreaCodeDesignatorPost("...");
                PhoneNumber.setExchangeLineSeparator("---");

                PhoneNumber pn = new PhoneNumber(ac, ex, li);

                assertEquals("" +
                        PhoneNumber.getAreaCodeDesignatorLeft() + pn.getAreaCode() + PhoneNumber.getAreaCodeDesignatorRight() +
                        PhoneNumber.getAreaCodeDesignatorPost() +
                        pn.getExchange() + PhoneNumber.getExchangeLineSeparator() + pn.getLine(),

                        pn.prettyPrint()
                );
            }

        }

        @Nested
        @DisplayName("Person Tests")
        class PersonTest {

            String fname = "Bobby";
            String lname = "McGee";
            String bd = "May 1 2000";
            String add = "573 Oak Ave";
            PhoneNumber pn = new PhoneNumber("222", "444", "8888");
            String nat = "Thai";

            Person aPerson;

            @BeforeEach
            void setUp() {
                aPerson = new Person("Bobby", "McGee");;
            }
            @AfterEach
            void tearDown() {}

            @Test
            void prettyPrint() {
                aPerson = new Person(fname, lname, bd, add, pn, nat);

                assertEquals(
                        aPerson.getFirstName() + " " + aPerson.getLastName() + "\n" +
                        aPerson.getBirthDate() + "\n" +
                        aPerson.getAddress() + "\n" +
                        aPerson.getPhoneNumber().prettyPrint() + "\n" +
                        aPerson.getNationality(),

                        aPerson.prettyPrint()
                );
            }

            @Test
            void setFirstName() {
                aPerson.setFirstName("New FirstName");
                assertEquals("New FirstName", aPerson.getFirstName());
            }

            @Test
            void setLastName() {
                aPerson.setLastName("New LastName");
                assertEquals("New LastName", aPerson.getLastName());
            }

            @Test
            void setBirthDate() {
                aPerson.setBirthDate("March 19 1950");
                assertEquals("March 19 1950", aPerson.getBirthDate());
            }

            @Test
            void setAddress() {
                aPerson.setAddress("555 Oak Ave");
                assertEquals("555 Oak Ave", aPerson.getAddress());
            }

            @Test
            void setPhoneNumber() {
                PhoneNumber pn = new PhoneNumber("555", "666", "7777");
                aPerson.setPhoneNumber(pn);
                assertEquals(pn, aPerson.getPhoneNumber());
            }

            @Test
            void setNationality() {
                aPerson.setNationality("Moroccan");
                assertEquals("Moroccan", aPerson.getNationality());
            }

            @Test
            @DisplayName("Six-Arg Constructor")
            void testConstructor1() {
                aPerson = new Person(fname, lname, bd, add, pn, nat);
                assertEquals(fname, aPerson.getFirstName());
                assertEquals(lname, aPerson.getLastName());
                assertEquals(bd, aPerson.getBirthDate());
                assertEquals(add, aPerson.getAddress());
                assertEquals(pn, aPerson.getPhoneNumber());
                assertEquals(nat, aPerson.getNationality());
            }

            @Test
            @DisplayName("Four-Arg Constructor")
            void testConstructor3() {
                aPerson = new Person(fname, lname, bd, nat);
                assertEquals(fname, aPerson.getFirstName());
                assertEquals(lname, aPerson.getLastName());
                assertEquals(bd, aPerson.getBirthDate());
                assertEquals(nat, aPerson.getNationality());
            }

            @Test
            @DisplayName("Two-Arg Constructor")
            void testConstructor2() {
                aPerson = new Person(fname, lname);
                assertEquals(fname, aPerson.getFirstName());
                assertEquals(lname, aPerson.getLastName());
            }

        }

        @DisplayName("Restaurant Tests")
        @Nested
        public class RestaurantTests {

            String name = "House of Waffles";
            String ft = "Breakfast Fare";
            String address = "123 Main Street";
            Person owner = new Person("Alix", "Smith");
            PhoneNumber pn = new PhoneNumber("510","555","1212");
            int bus = 649823;
            double rev = 300000.0;

            Restaurant r;

            @BeforeEach
            void setUp() {
                this.r = new Restaurant(name, ft, address, owner, pn, bus, rev);
            }

            @AfterEach
            void tearDown() {
            }

            @Test
            @DisplayName("Seven-Arg Constructor")
            void testConstructor1() {
                this.r = new Restaurant(name, ft, address, owner, pn, bus, rev);

                assertEquals(r.getName(), name);
                assertEquals(r.getFoodType(), ft);
                assertEquals(r.getAddress(), address);
                assertEquals(r.getOwner(), owner);
                assertEquals(r.getPhoneNumber(), pn);
                assertEquals(r.getBusinessLicenseNumber(), bus);
                assertEquals(r.getYearlyGrossRevenue(), rev);
                }

            @Test
            void setName() {
                r.setName("foo");
                assertEquals("foo", r.getName());
            }

            @Test
            void setFoodType() {
                r.setFoodType("bar");
                assertEquals("bar", r.getFoodType());
            }

            @Test
            void setAddress() {
                r.setAddress("baz");
                assertEquals("baz", r.getAddress());
            }

            @Test
            void setOwner() {
                Person p = new Person("Blaze", "Espinosa");
                r.setOwner(p);
                assertEquals(p, r.getOwner());
            }

            @Test
            void setPhoneNumber() {
                PhoneNumber pn = new PhoneNumber("555", "111", "2323");
                r.setPhoneNumber(pn);
                assertEquals(pn, r.getPhoneNumber());
            }

            @Test
            void setBusinessLicenseNumber() {
                r.setBusinessLicenseNumber(4521);
                assertEquals(4521, r.getBusinessLicenseNumber());
            }

            @Test
            void setYearlyGrossRevenue() {
                r.setYearlyGrossRevenue(25000.0);
                assertEquals(25000.0, r.getYearlyGrossRevenue());
            }

            @Test
            void invite() {
                assertEquals("Come on down to " + r.getName() +
                        ", a restaurant serving " + r.getFoodType() +
                        ", located at " + r.getAddress() + "!",

                        r.invite());
            }

            @Test
            void getTaxInfo() {
                assertEquals(r.getName() + " / " + r.getOwner() + " / " + r.getBusinessLicenseNumber() + " / " + r.getYearlyGrossRevenue(),
                        r.getTaxInfo());
            }

        }


    @Nested
    @DisplayName("RestaurantDirectory Tests")
    class RestaurantDirectoryTest {

            // Helper method for testing: act as getter for RestaurantDirectory.size, since it's private and no getter exists
        // I've given it the distinctly-named __getSize because I don't want to confuse anyone into thinking this is how a normal getter can be used!
        public int __getSize(RestaurantDirectory rd) {
            Field s = null;
            try {
                s = RestaurantDirectory.class.getDeclaredField("size");
                s.setAccessible(true);
                if (!s.getType().getTypeName().equals("int")) {
                    fail("RestaurantDirectory class instance variable named 'size' is not of type int!");
                }
            } catch (NoSuchFieldException e) {
                fail("RestaurantDirectory class has no instance variable named 'size'!");
            }
                // size exists and is type int
            try {
                return s.getInt(rd);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

            RestaurantDirectory dir;


            PhoneNumber pn = new PhoneNumber("123", "555", "1212");
            Person owners[] = {
                    new Person("Alice", "Smith"),
                    new Person("Bob", "Bobby"),
                    new Person("Cindy", "Cinders"),
                    new Person("Doug", "Duggy"),
                    new Person("Eddie", "Eazy"),
                    new Person("Max", "Maxer") // Owns no restaurants
            };

            Restaurant[] restaurants = {
                    new Restaurant("Common Name", "Bread", "c", owners[0], pn, 123, 100.0),
                    new Restaurant("Common Name", "b", "c", owners[1], pn, 123, 100.0),
                    new Restaurant("Cindy's Place", "Thai", "c", owners[2], pn, 123, 200.0),
                    new Restaurant("Pancake Palace", "b", "c", owners[3], pn, 123, 200.0),
                    new Restaurant("Eazy's 1", "Thai", "c", owners[4], pn, 123, 300.865),
                    new Restaurant("Common Name", "b", "c", owners[3], pn, 123, 300.0),
                    new Restaurant("Eazy's 2", "Thai", "c", owners[4], pn, 123, 0.0),
                    new Restaurant("a", "Thai", "c", owners[0], pn, 123, 100.0),
                    new Restaurant("Eazy's 3", "b", "c", owners[4], pn, 123, 100.0),

            };

        @BeforeEach
        void setUp() {
            dir = new RestaurantDirectory();
            assertEquals(0, __getSize(dir));
            dir.addRestaurant(restaurants[0]);
            dir.addRestaurant(restaurants[1]);
            dir.addRestaurant(restaurants[2]);
            dir.addRestaurant(restaurants[3]);
            dir.addRestaurant(restaurants[4]);
            dir.addRestaurant(restaurants[5]);
            dir.addRestaurant(restaurants[6]);
            dir.addRestaurant(restaurants[7]);
            dir.addRestaurant(restaurants[8]);
        }

        @AfterEach
        void tearDown() {
        }

        @Test
        void addRestaurant() {
            RestaurantDirectory dir = new RestaurantDirectory();
            assertEquals(0, __getSize(dir));
            dir.addRestaurant(restaurants[0]);
            assertEquals(1, __getSize(dir));
            dir.addRestaurant(restaurants[1]);
            assertEquals(2, __getSize(dir));
            dir.addRestaurant(restaurants[2]);
            assertEquals(3, __getSize(dir));
            dir.addRestaurant(restaurants[3]);
            assertEquals(4, __getSize(dir));
            dir.addRestaurant(restaurants[4]);
            assertEquals(5, __getSize(dir));
            dir.addRestaurant(restaurants[0]); // intentially duping Restaurant instance
            assertEquals(6, __getSize(dir));
        }

        @Test
        void searchByOwnerName() {
            // Test case: zero matches
            Restaurant[] matches = dir.searchByOwnerName("Blank", "Blankette");
            assertEquals(0, matches.length);

            // Test case: one match
            matches = dir.searchByOwnerName("Cindy", "Cinders");
            assertEquals(1, matches.length);
            assertEquals("Cindy's Place", matches[0].getName());

            // Test case: three matches
            matches = dir.searchByOwnerName("Eddie", "Eazy");
            assertEquals(3, matches.length);
            assertEquals("Eazy's 1", matches[0].getName());
            assertEquals("Eazy's 2", matches[1].getName());
            assertEquals("Eazy's 3", matches[2].getName());
        }

        @Test
        void searchByName() {
            // Test case: no matches
            Restaurant[] matches = dir.searchByName("This Restaurant Does Not Exist");
            assertEquals(0, matches.length);

            // Test case: one match
            matches = dir.searchByName("Pancake Palace");
            assertEquals(1, matches.length);

            // Test case: three matches
            matches = dir.searchByName("Common Name");
            assertEquals(3, matches.length);
        }

        @Test
        void searchByFoodType() {
            // Test case: no matches
            Restaurant[] matches = dir.searchByFoodType("Plutonium Shavings");
            assertEquals(0, matches.length);

            // Test case: one match
            matches = dir.searchByFoodType("Bread");
            assertEquals(1, matches.length);

            // Test case: four matches
            matches = dir.searchByFoodType("Thai");
            assertEquals(4, matches.length);

        }

        @Test
        void totalGrossRevenue() {
            assertEquals(1400.865, dir.totalGrossRevenue());
        }

        @Test
        void computeTaxRevenue() {
            assertEquals(70.04325, dir.computeTaxRevenue(0.05));
            assertEquals(0.0, dir.computeTaxRevenue(0.0));
            assertEquals(700.4325, dir.computeTaxRevenue(0.5));

        }

        // Helper methods for accessing stretch goal methods at runtime, to avoid blocking compilation
        // if stretch goals aren't implemented

        // This should only be run if the runtime check for implementation has already been done.
        public Method getSearchByOwner(RestaurantDirectory rd) {
            Method sbo = null;
            try {
                sbo = RestaurantDirectory.class.getMethod("searchByOwner", new Class[] {Person.class});
            } catch (NoSuchMethodException e) {
                System.out.println("getSearchByOwner not implemented?");
                throw new RuntimeException(e);
            }
            return sbo;
        }

        // This should only be run if the runtime check for implementation has already been done.
        public Method getAddRestaurants(RestaurantDirectory rd) {
            Method ar = null;
            try {
                ar = RestaurantDirectory.class.getMethod("addRestaurants", new Class[] {Restaurant[].class});
            } catch (NoSuchMethodException e) {
                System.out.println("addRestaurants not implemented?");
                throw new RuntimeException(e);
            }
            return ar;
        }

        // This should only be run if the runtime check for implementation has already been done.
        public Method getFindDuplicateOwners(RestaurantDirectory rd) {
            Method fdo = null;
            try {
                fdo = RestaurantDirectory.class.getMethod("findDuplicateOwners");
            } catch (NoSuchMethodException e) {
                System.out.println("findDuplicateOwners not implemented?");
                throw new RuntimeException(e);
            }
            return fdo;
        }

        @Nested
        @DisplayName("Stretch Goal Tests (RestaurantDirectory)")
        @Order(Integer.MAX_VALUE)
        class stretchGoalTests {

            @Test
            @EnabledIf("searchByOwnerImplemented")
            void searchByOwner() throws InvocationTargetException, IllegalAccessException {
                Restaurant[] matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[0]);
                assertEquals(2, matches.length);
                assertEquals(restaurants[0], matches[0]);
                assertEquals(restaurants[7], matches[1]);
                matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[1]); // TODO better solution, like others below
                assertEquals(1, matches.length);
                matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[2]);
                assertEquals(1, matches.length);
                matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[3]);
                assertEquals(2, matches.length);
                matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[4]);
                assertEquals(3, matches.length);
                matches = (Restaurant[]) getSearchByOwner(dir).invoke(dir, owners[5]);
                assertEquals(0, matches.length);

            }

            @Test
            @EnabledIf("addRestaurantsImplemented")
            void addRestaurants() throws InvocationTargetException, IllegalAccessException {
                dir = new RestaurantDirectory(); // internal array length of 100
                assertEquals(0, __getSize(dir));
                assertTrue((boolean) getAddRestaurants(dir).invoke(dir, (Object) new Restaurant[] {restaurants[2], restaurants[0]})); // add two
                assertEquals(2, __getSize(dir));
                assertEquals(restaurants[2], dir.searchByName("Cindy's Place")[0]);
                assertEquals(restaurants[0], dir.searchByName("Common Name")[0]);
                assertTrue((boolean) getAddRestaurants(dir).invoke(dir, (Object) new Restaurant[] {restaurants[0]})); // add another copy of restaurant 0
                assertEquals(3, __getSize(dir));
                assertEquals(restaurants[0], dir.searchByName("Common Name")[0]);
                assertEquals(restaurants[0], dir.searchByName("Common Name")[1]);
                assertEquals(restaurants[2], dir.searchByName("Cindy's Place")[0]);
                assertTrue((boolean) getAddRestaurants(dir).invoke(dir, (Object) new Restaurant[] {restaurants[3], restaurants[4], restaurants[5]})); // add three more
                assertEquals(6, __getSize(dir));
                assertEquals(restaurants[3], dir.searchByName("Pancake Palace")[0]);
                assertEquals(restaurants[4], dir.searchByName("Eazy's 1")[0]);
                assertEquals(3, dir.searchByName("Common Name").length);

                // Add when no room to accept:
                assertFalse((boolean) getAddRestaurants(dir).invoke(dir, (Object) new Restaurant[95])); // All nulls; should handle this!
                assertEquals(6, __getSize(dir)); // and size should be unchanged.
            }

            @Test
            @EnabledIf("findDuplicateOwnersImplemented")
            void findDuplicateOwners() throws InvocationTargetException, IllegalAccessException {
                    Person[] duplicateOwners = (Person[]) getFindDuplicateOwners(dir).invoke(dir);
                    assertEquals(3, duplicateOwners.length);
                    assertTrue(Arrays.asList(duplicateOwners).contains(owners[0]));
                    assertTrue(Arrays.asList(duplicateOwners).contains(owners[3]));
                    assertTrue(Arrays.asList(duplicateOwners).contains(owners[4]));
            }

            boolean searchByOwnerImplemented() {
                try {
                    Method sbo = RestaurantDirectory.class.getDeclaredMethod("searchByOwner", new Class[] {Person.class});
                } catch (NoSuchMethodException e) {
                    return false;
                }
                return true;
            }

            boolean addRestaurantsImplemented() {
                try {
                    Method addR = RestaurantDirectory.class.getDeclaredMethod("addRestaurants", new Class[] {Restaurant.class.arrayType() } );
                } catch (NoSuchMethodException e) {
                    return false;
                }
                return true;
            }

            boolean findDuplicateOwnersImplemented() {
                try {
                    Method fdo = RestaurantDirectory.class.getDeclaredMethod("findDuplicateOwners", new Class[] {} );
                } catch (NoSuchMethodException e) {
                    return false;
                }
                return true;
            }

            }

        }



}



