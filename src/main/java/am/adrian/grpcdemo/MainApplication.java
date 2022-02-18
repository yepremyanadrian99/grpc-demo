package am.adrian.grpcdemo;

import am.adrian.grpcdemo.composition.CompositionDemo;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        List.of(
                        new CompositionDemo()
                )
                .forEach(Runnable::run);
    }
}
