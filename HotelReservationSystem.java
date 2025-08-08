import java.util.Scanner;

public class HotelReservationSystem {

	public static void main(String[] args) {
		
		
		
		Room[] rooms = {new Room(101), new Room(102), //room class array is created to hold all the rooms of the hotel
				new Room(103), new Room(104),
				new Room(105), new Room(106)};
		
		
		
		Hotel hotel = new Hotel("Hotel Regenta", rooms); //Hotel object is created and assigned with Hotel name and rooms 
		
		
		
		Scanner sc = new Scanner(System.in); //creating scanner object for getting input from the user
		
		while(true) {
			
			System.out.println("Hotel Management System by Hotel Regenta\n");
			System.out.println("1. Check In");
			System.out.println("2. Check out");
			System.out.println("3. View Available rooms"); 
			System.out.println("4. Exit");
			System.out.println("Enter Your Choice: "); //printing input screen details and getting input from the user
			
			
			int choice = sc.nextInt(); //user input is stored in int variable choice and sent into switch case
			
			switch(choice) {
			case 1:
				hotel.checkIn(); //checkin method in hotel class is called
				break;
			case 2:
				hotel.checkOut(); //checkout method in hotel class is called
				break;
			case 3:
				hotel.displayAvailableRooms(); //displayavailablerooms method from hotel class is called
				break;
			case 4:
				System.out.println("Exiting the system.."); // exiting the system as per user input
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choices. Please try again\n");
			}
			
		}
	}

}
