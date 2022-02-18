package am.adrian.grpcdemo.wrapper_demo;

import am.adrian.grpcdemo.model.WrapperTest;
import com.google.protobuf.Int32Value;

public class WrapperDemo implements Runnable {

    @Override
    public void run() {
        WrapperTest wrapperTest = WrapperTest.newBuilder()
                .setAge(
                        Int32Value.newBuilder()
                                .setValue(100)
                                .build()
                )
                .build();
        System.out.println(wrapperTest.getAge());
    }
}
