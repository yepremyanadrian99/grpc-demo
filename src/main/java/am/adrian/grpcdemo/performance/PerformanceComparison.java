package am.adrian.grpcdemo.performance;

import am.adrian.grpcdemo.model.Person;
import am.adrian.grpcdemo.performance.model.JsonPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.util.LongSummaryStatistics;

public class PerformanceComparison implements Runnable {

    @Override
    public void run() {
        // JSON serialization-deserialization
        JsonPerson jsonPerson = new JsonPerson();
        jsonPerson.setName("Adrian");
        jsonPerson.setSurname("Yepremyan");
        jsonPerson.setAge(23);
        ObjectMapper objectMapper = new ObjectMapper();

        Runnable jsonRunnable = () -> {
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(jsonPerson);
                objectMapper.readValue(bytes, JsonPerson.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        // Proto serialization-deserialization
        Person person = Person.newBuilder()
                .setName("Adrian")
                .setSurname("Yepremyan")
                .setAge(23)
                .build();

        Runnable protoRunnable = () -> {
            byte[] bytes = person.toByteArray();
            try {
                Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        runPerformanceTest(jsonRunnable, "JSON");
        runPerformanceTest(protoRunnable, "PROTO");
    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        LongSummaryStatistics statistics = new LongSummaryStatistics();
        for (int i = 0; i < 20; ++i) {
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < 1_000_000; ++j) {
                runnable.run();
            }
            long endTime = System.currentTimeMillis();
            statistics.accept(endTime - startTime);
        }
        System.out.printf("Time spent to complete %s: %s\n", method, statistics);
    }
}
