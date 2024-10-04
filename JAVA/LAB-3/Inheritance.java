// class Vehicle {
//     String model;
//     int year;
//     String color;

//     public Vehicle(String model, int year, String color) {
//         this.model = model;
//         this.year = year;
//         this.color = color;
//     }

//     public void dispaly() {
//         System.out.println("Model : " + model);
//         System.out.println("Year  : " + year);
//         System.out.println("Color : " + color);
//     }
// }

// class Car extends Vehicle {
//     int number_of_door;

//     public Car(String model, int year, String color, int number_of_door) {
//         super(model, year, color);
//         this.number_of_door = number_of_door;
//     }

//     public void dispaly() {
//         super.dispaly();
//         System.out.println("Number of Doors : " + number_of_door);
//     }
// }

// public class Inheritance {
//     public static void main(String[] args) {

//         Vehicle vehicle = new Vehicle("Camry Model of Toyota", 2000, "Black");
//         System.out.println("Vehicle :- ");
//         vehicle.dispaly();
//         System.out.println();

//         Car car = new Car("Jetta", 1979, "White", 4);
//         System.out.println("Car :- ");
//         car.dispaly();
//         System.out.println();

//     }
// }
