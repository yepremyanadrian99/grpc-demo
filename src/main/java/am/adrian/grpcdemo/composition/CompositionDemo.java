package am.adrian.grpcdemo.composition;

import am.adrian.grpcdemo.enumeration.BodyStyle;
import am.adrian.grpcdemo.model.Address;
import am.adrian.grpcdemo.model.Car;
import am.adrian.grpcdemo.model.Dealer;
import am.adrian.grpcdemo.model.Person;

public class CompositionDemo implements Runnable {

    @Override
    public void run() {
        Address mainAddress = Address.newBuilder()
                .setCountry("Armenia")
                .setCity("Yerevan")
                .setStreet("Some street")
                .setHouse(12)
                .build();

        Car grandCherokee = Car.newBuilder()
                .setMake("Jeep")
                .setModel("Grand Cherokee")
                .setYear(2006)
                .setBodyStyle(BodyStyle.SUV)
                .build();

        Car camry = Car.newBuilder()
                .setMake("Toyota")
                .setModel("Camry")
                .setYear(2022)
                .setBodyStyle(BodyStyle.SEDAN)
                .build();

        Person person = Person.newBuilder()
                .setName("Adrian")
                .setSurname("Yepremyan")
                .setAge(23)
                .putAddresses("main", mainAddress)
                .addCars(grandCherokee)
                .addCars(camry)
                .build();

        // Camry's bodyStyle is not printed, as SEDAN,
        // being the first enum element, is considered a default value,
        // and the default values are not shown in proto3.
        System.out.println(person);

        Dealer dealer = Dealer.newBuilder()
                .putCars(12000, grandCherokee)
                .putCars(35000, camry)
                .build();

        int price = 10_000;
        try {
            dealer.getCarsOrThrow(price);
        } catch (IllegalArgumentException e) {
            System.out.printf("No cars available for price: %d\n", price);
        }
        System.out.println("No car found with provided price point, maybe try this?");
        System.out.println(dealer.getCarsOrDefault(price, grandCherokee));
    }
}
