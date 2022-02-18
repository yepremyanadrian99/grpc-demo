package am.adrian.grpcdemo;

import am.adrian.grpcdemo.version_compatibility.VersionCompatibilityDemo;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        List.of(
                        new VersionCompatibilityDemo()
                )
                .forEach(Runnable::run);
    }
}
