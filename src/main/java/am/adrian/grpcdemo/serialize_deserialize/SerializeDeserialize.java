package am.adrian.grpcdemo.serialize_deserialize;

import am.adrian.grpcdemo.model.Person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializeDeserialize implements Runnable {

    @Override
    public void run() {
        try {
            Person person = Person.newBuilder()
                    .setName("Adrian")
                    .setSurname("Yepremyan")
                    .setAge(23)
                    .build();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            person.writeTo(os);

            byte[] bytes = os.toByteArray();
            Person newPerson = Person.parseFrom(bytes);

            System.out.println("New Person...");
            System.out.println(newPerson);

//        Path path = Paths.get("person.serialized");
//        Files.write(path, person.());
//
//        byte[] bytes = Files.readAllBytes(path);
//        Person deserializedPerson = Person.parseFrom(bytes);
//        System.out.println(deserializedPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
