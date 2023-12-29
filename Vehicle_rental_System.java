import java.util.*;
public class rental{
    public static void main(String[] args) {
    	select();
    }
    public static void select() {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        BikeRentalSystem rentalSystem1=new BikeRentalSystem();
        
        Scanner sc=new Scanner(System.in);
        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); 
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        
        Bike bike1 = new Bike("B001", "Bajaj", "Pulser", 60.0); 
        Bike bike2 = new Bike("B002", "Honda", "Unicorn", 50.0);
        Bike bike3 = new Bike("B003", "Bajaj", "CT100", 40.0);
        rentalSystem1.addBike(bike1);
        rentalSystem1.addBike(bike2);
        rentalSystem1.addBike(bike3);
        
        while(true) {
        	
        System.out.println("\n Enter 1 for Admin login ");
        System.out.println("\n Enter 2 for User login ");
        System.out.println("\n Enter 3 for exit");
        int c=sc.nextInt();
        sc.nextLine();
        if(c==1) {
        	Admin();
        }else if(c==2) {
        System.out.println("\n ----Vehicle Rental System---- \n");
        System.out.println("\n Enter car or bike ?");
        String vehicle=sc.nextLine();
        
        if(vehicle.equals("car"))
        	rentalSystem.menu();
        else if(vehicle.equals("bike"))
        	rentalSystem1.menu1();
        }else if(c==3) {
        	System.out.println("\n Thank you...");
        	break;
        }else {
        	System.out.println("\n Invalid choice !!\n");
        }
    }
        
    }
    public static void Admin() {
    	CarRentalSystem rentalSystem = new CarRentalSystem();
    	BikeRentalSystem rentalSystem1=new BikeRentalSystem();
    	int choice=0;
    	while(true) {
    		Scanner sc=new Scanner(System.in);
    		System.out.println("\n Enter your choice:\n1.Bike entry\n2.Car entry\n3.exit");
    		choice=sc.nextInt();
    		sc.nextLine();
    	if(choice==1) {
    		System.out.println("\nEnter bike id :");
    		String b_id=sc.nextLine();
    		System.out.println("\nEnter brand :");
    		String b_brand=sc.nextLine();
    		System.out.println("\nEnter model :");
    		String b_model=sc.nextLine();
    		System.out.println("\nEnter cost per day :");
    		double b_cost=sc.nextDouble();
    		Bike bike4=new Bike(b_id,b_brand,b_model,b_cost);
    		rentalSystem1.addBike(bike4);
    		
    	}
    	else if(choice==2) {
    		
    		System.out.println("\nEnter car id :");
    		String c_id=sc.nextLine();
    		System.out.println("\nEnter brand :");
    		String c_brand=sc.nextLine();
    		System.out.println("\nEnter model :");
    		String c_model=sc.nextLine();
    		System.out.println("\nEnter cost per day :");
    		double c_cost=sc.nextDouble();
    		
    		Car car4=new Car(c_id,c_brand,c_model,c_cost);
    		rentalSystem.addCar(car4);
    	}
    	else if(choice==3) {
    		System.out.println("\n---Exit---\n");
    		select();
    	}else {
    		System.out.println("\n Invalid Choice\n");
    		select();
    	}
    	}
    }
}
class Car extends rental {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }
    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(double rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;
    private double price;

    public Customer(String customerId, String name, double price) {
        this.customerId = customerId;
        this.name = name;
        this.price=price;
    }

    public String getCustomerId() {
        return customerId;
    }
    public double getPrice() {
    	return price;
    }
    public String getName() {
        return name;
    }
}

class Rental1 {
    private Car car;
    private Customer customer;
    private double days;

    public Rental1(Car car, Customer customer, double days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getDays() {
        return days;
    }
}

class CarRentalSystem extends rental{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental1> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, double days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental1(car, customer, days));

        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental1 rentalToRemove = null;
        for (Rental1 rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);

        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void menu() {

        while (true) {
        	Scanner sc = new Scanner(System.in);
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                System.out.println("\nAvailable Cars:");
                int c=0;
                for(Car car : cars) {
                	if(car.isAvailable()) {
                		c++;
                	}
                }if(c==0) {
                	System.out.println("\n No cars Available !\n");
                }else {
                for (Car car : cars) {
                	if (car.isAvailable()) {
                		System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                	}
                }
                
                System.out.print("\nEnter the car ID you want to rent: ");
                String carId = sc.nextLine();

                System.out.print("Enter the number of days for rental: ");
                double rentalDays = sc.nextDouble();
                sc.nextLine(); 

                Car selectedCar = null;
                for (Car car : cars) {
                	if (car.getCarId().equals(carId) && car.isAvailable()) {
                		selectedCar = car;
                		break;
                	}
                }
                double price = selectedCar.calculatePrice(rentalDays);
                
                Customer newCustomer = new Customer("" + (customers.size() + 1), customerName, price);
                addCustomer(newCustomer);


                if (selectedCar != null) {
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: %.2f%n", newCustomer.getPrice());

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                }
//                else {
//                    System.out.println("\nInvalid car selection or car not available for rent.");
//                }
            } 
            }
                else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental1 rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    System.out.println("\n Select the Damage Level of the Car :\n");
                    System.out.println("\n 1.0 - Grade \n 2.A - Grade \n 3.B - Grade \n 4.C - Grade");
                    char damage_grade=sc.next().charAt(0);
                    double totprice=customer.getPrice();
                    if (customer != null) {
                        returnCar(carToReturn);
                        switch(damage_grade) {
                        case '0':
                        {
                        	System.out.println("\n --Grade zero - No Damage");
                        	
                        	break;
                        }
                        case 'A':
                        {
                        	System.out.println("\n --Grade A - Low Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*20);
                        	break;
                        }
                        case 'B':
                        {
                        	System.out.println("\n --Grade B - Medium Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*50);
                        	break;
                        }
                        case 'C':
                        {
                        	System.out.println("\n --Grade C - High Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*75);
                        	break;
                        }
                        }
                        System.out.println("\n Total Cost --- "+totprice+"\n");
                        System.out.println("Car returned successfully by " + customer.getName());
                    } 
                
//                    else
//                    {
//                        System.out.println("Car was not rented or rental information is missing.");
//                    }
                } 
                else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
            	System.out.println("\nThank you for using the Vehicle Rental System!");
                select();
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
            
        }
    }
}

class Bike {
    private String bikeId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Bike(String bikeId, String brand, String model, double basePricePerDay) {
        this.bikeId = bikeId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }
    public String getBikeId() {
        return bikeId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(double rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnBike() {
        isAvailable = true;
    }
}

class Customer_b {
    private String customerId;
    private String name;
    private double price;

    public Customer_b(String customerId, String name, double price) {
        this.customerId = customerId;
        this.name = name;
        this.price=price;
    }

    public String getCustomerId() {
        return customerId;
    }
    public double getPrice() {
    	return price;
    }
    public String getName() {
        return name;
    }
}

class Rental2 {
    private Bike bike;
    private Customer_b customer_b;
    private double days;

    public Rental2(Bike bike, Customer_b customer_b, double days) {
        this.bike= bike;
        this.customer_b = customer_b;
        this.days = days;
    }

    public Bike getbike() {
        return bike;
    }

    public Customer_b getCustomer_b() {
        return customer_b;
    }

    public double getDays() {
        return days;
    }
}

class BikeRentalSystem extends rental{
    private List<Bike> bikes;
    private List<Customer_b> customers_b;
    private List<Rental2> rentals_b;

    public BikeRentalSystem() {
        bikes = new ArrayList<>();
        customers_b = new ArrayList<>();
        rentals_b = new ArrayList<>();
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public void addCustomer_b(Customer_b customer_b) {
        customers_b.add(customer_b);
    }

    public void rentBike(Bike bike, Customer_b customer_b, double days) {
        if (bike.isAvailable()) {
            bike.rent();
            rentals_b.add(new Rental2(bike, customer_b, days));

        } else {
            System.out.println("Bike is not available for rent.");
        }
    }

    public void returnBike(Bike bike) {
        bike.returnBike();
        Rental2 rentalToRemove = null;
        for (Rental2 rental : rentals_b) {
            if (rental.getbike() == bike) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals_b.remove(rentalToRemove);

        } else {
            System.out.println("Bike was not rented.");
        }
    }

    public void menu1() {

        while (true) {
        	Scanner sc = new Scanner(System.in);
            System.out.println("===== Bike Rental System =====");
            System.out.println("1. Rent a Bike");
            System.out.println("2. Return a Bike");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.println("\n== Rent a Bike ==\n");
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                System.out.println("\nAvailable Bikes:");
                int c=0;
                for(Bike bike : bikes) {
                	if(bike.isAvailable()) {
                		c++;
                	}
                }if(c==0) {
                	System.out.println("\n No bikes Available !\n");
                }else {
                for (Bike bike : bikes) {
                	if (bike.isAvailable()) {
                		System.out.println(bike.getBikeId() + " - " + bike.getBrand() + " " + bike.getModel());
                	}
                }
                
                System.out.print("\nEnter the Bike ID you want to rent: ");
                String bikeId = sc.nextLine();

                System.out.print("Enter the number of days for rental: ");
                double rentalDays = sc.nextDouble();
                sc.nextLine(); 

                Bike selectedBike = null;
                for (Bike bike : bikes) {
                	if (bike.getBikeId().equals(bikeId) && bike.isAvailable()) {
                		selectedBike = bike;
                		break;
                	}
                }
                double price = selectedBike.calculatePrice(rentalDays);
                
                Customer_b newCustomer = new Customer_b("" + (customers_b.size() + 1), customerName, price);
                addCustomer_b(newCustomer);


                if (selectedBike != null) {
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Bike: " + selectedBike.getBrand() + " " + selectedBike.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", newCustomer.getPrice());

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentBike(selectedBike, newCustomer, rentalDays);
                        System.out.println("\nBike rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                }
//                else {
//                    System.out.println("\nInvalid Bike selection or bike not available for rent.");
//                }
            } 
            }
                else if (choice == 2) {
                System.out.println("\n== Return a Bike ==\n");
                System.out.print("Enter the bike ID you want to return: ");
                String bikeId = sc.nextLine();

                Bike bikeToReturn = null;
                for (Bike bike : bikes) {
                    if (bike.getBikeId().equals(bikeId) && !bike.isAvailable()) {
                        bikeToReturn = bike;
                        break;
                    }
                }

                if (bikeToReturn != null) {
                    Customer_b customer = null;
                    for (Rental2 rental : rentals_b) {
                        if (rental.getbike() == bikeToReturn) {
                            customer = rental.getCustomer_b();
                            break;
                        }
                    }
                    System.out.println("\n Select the Damage Level of the Car :\n");
                    System.out.println("\n 1.0 - Grade \n 2.A - Grade \n 3.B - Grade \n 4.C - Grade");
                    char damage_grade=sc.next().charAt(0);
                    double totprice=customer.getPrice();
                    if (customer != null) {
                        returnBike(bikeToReturn);
                        switch(damage_grade) {
                        case '0':
                        {
                        	System.out.println("\n --Grade zero - No Damage");
                        	
                        	break;
                        }
                        case 'A':
                        {
                        	System.out.println("\n --Grade A - Low Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*20);
                        	break;
                        }
                        case 'B':
                        {
                        	System.out.println("\n --Grade B - Medium Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*50);
                        	break;
                        }
                        case 'C':
                        {
                        	System.out.println("\n --Grade C - High Damage");
                        	totprice=customer.getPrice()+((customer.getPrice()/100)*75);
                        	break;
                        }
                        }
                        System.out.println("\n Total Cost --- "+totprice+"\n");
                        System.out.println("Bike returned successfully by " + customer.getName());
                    } 
                
//                    else
//                    {
//                        System.out.println("Bike was not rented or rental information is missing.");
//                    }
                } 
                else {
                    System.out.println("Invalid bike ID or bike is not rented.");
                }
            } else if (choice == 3) {
            	System.out.println("\nThank you for using the Vehicle Rental System!");
                select();
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
            
        }
    }
}
