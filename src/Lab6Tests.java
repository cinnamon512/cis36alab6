// Lab 6 Tests
// Please see "Using JUnit in IntelliJ" in the bottom-most module to use this!

import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
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
                aPerson = new Person("Bobby", "McGee");
            }

            @AfterEach
            void tearDown() {
            }

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

            @BeforeEach
            void setUp() {
                this.r = new Restaurant(name, ft, address, owner, pn, bus, rev);
            }

            @AfterEach
            void tearDown() {
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
            assertEquals(0, dir.getSize());
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
            assertEquals(0, dir.getSize());
            dir.addRestaurant(restaurants[0]);
            assertEquals(1, dir.getSize());
            dir.addRestaurant(restaurants[1]);
            assertEquals(2, dir.getSize());
            dir.addRestaurant(restaurants[2]);
            assertEquals(3, dir.getSize());
            dir.addRestaurant(restaurants[3]);
            assertEquals(4, dir.getSize());
            dir.addRestaurant(restaurants[4]);
            assertEquals(5, dir.getSize());
            dir.addRestaurant(restaurants[0]); // intentially duping Restaurant instance
            assertEquals(6, dir.getSize());
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
        }

        @Test
        void searchByOwner() {
        }

        @Test
        void addRestaurants() {
        }

        @Test
        void deDupeArrayOfPerson() {
        }

        @Test
        void findDuplicateOwners() {
        }

        @Nested
        @DisplayName("Stretch Goal Tests (RestaurantDirectory)")
        @Order(Integer.MAX_VALUE)
        class stretchGoalTests {

            @Test
            @EnabledIf("searchByOwnerImplemented")
            void searchByOwner() {
                Restaurant[] matches = dir.searchByOwner(owners[0]);
                assertEquals(2, matches.length);
                assertEquals(restaurants[0], matches[0]);
                assertEquals(restaurants[7], matches[1]);
                matches = dir.searchByOwner(owners[1]);
                assertEquals(1, matches.length);
                matches = dir.searchByOwner(owners[2]);
                assertEquals(1, matches.length);
                matches = dir.searchByOwner(owners[3]);
                assertEquals(2, matches.length);
                matches = dir.searchByOwner(owners[4]);
                assertEquals(3, matches.length);
                matches = dir.searchByOwner(owners[5]);
                assertEquals(0, matches.length);

            }

            @Test
            @EnabledIf("addRestaurantsImplemented")
            void addRestaurants() {
                dir = new RestaurantDirectory(); // internal array length of 100
                assertEquals(0, dir.getSize());
                assertTrue(dir.addRestaurants(new Restaurant[] {restaurants[2], restaurants[0]})); // add two
                assertEquals(2, dir.getSize());
                assertEquals(restaurants[2], dir.searchByName("Cindy's Place")[0]);
                assertEquals(restaurants[0], dir.searchByName("Common Name")[0]);
                assertTrue(dir.addRestaurants(new Restaurant[] {restaurants[0]})); // add another copy of restaurant 0
                assertEquals(3, dir.getSize());
                assertEquals(restaurants[0], dir.searchByName("Common Name")[0]);
                assertEquals(restaurants[0], dir.searchByName("Common Name")[1]);
                assertEquals(restaurants[2], dir.searchByName("Cindy's Place")[0]);
                assertTrue(dir.addRestaurants(new Restaurant[] {restaurants[3], restaurants[4], restaurants[5]})); // add three more
                assertEquals(6, dir.getSize());
                assertEquals(restaurants[3], dir.searchByName("Pancake Palace")[0]);
                assertEquals(restaurants[4], dir.searchByName("Eazy's 1")[0]);
                assertEquals(3, dir.searchByName("Common Name").length);

                // Add when no room to accept:
                assertFalse(dir.addRestaurants(new Restaurant[95])); // All nulls; should handle this!
                assertEquals(6, dir.getSize()); // and size should be unchanged.

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
            }

        }



}



