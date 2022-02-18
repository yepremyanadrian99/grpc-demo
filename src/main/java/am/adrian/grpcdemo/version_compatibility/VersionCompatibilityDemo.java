package am.adrian.grpcdemo.version_compatibility;

import am.adrian.grpcdemo.model.Television;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionCompatibilityDemo implements Runnable {

    @Override
    @SneakyThrows
    public void run() {
//        Television television = Television.newBuilder()
//                .setBrand("Sony")
//                .setModel(2021)
//                .setType(Type.QHD)
//                .build();
//
        Path path = Paths.get("tv-v1");
//        Files.write(path, television.toByteArray());

        byte[] bytes = Files.readAllBytes(path);
        System.out.println(Television.parseFrom(bytes));
    }
}
