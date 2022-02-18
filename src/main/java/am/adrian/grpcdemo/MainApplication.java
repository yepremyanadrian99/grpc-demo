package am.adrian.grpcdemo;

import am.adrian.grpcdemo.performance.PerformanceComparison;
import am.adrian.grpcdemo.serialize_deserialize.SerializeDeserialize;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        List.of(
                        new SerializeDeserialize(),
                        new PerformanceComparison()
                )
                .forEach(Runnable::run);
    }
}
