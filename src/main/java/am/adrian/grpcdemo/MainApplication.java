package am.adrian.grpcdemo;

import am.adrian.grpcdemo.one_of_demo.OneOfDemo;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        List.of(
                        new OneOfDemo()
                )
                .forEach(Runnable::run);
    }
}
