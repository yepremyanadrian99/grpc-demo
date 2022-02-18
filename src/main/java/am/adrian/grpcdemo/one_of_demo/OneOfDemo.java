package am.adrian.grpcdemo.one_of_demo;

import am.adrian.grpcdemo.model.Credentials;
import am.adrian.grpcdemo.model.EmailCredentials;
import am.adrian.grpcdemo.model.PhoneOTP;

public class OneOfDemo implements Runnable {

    @Override
    public void run() {
        EmailCredentials emailCredentials = EmailCredentials.newBuilder()
                .setEmail("adrian@test.com")
                .setPassword("Password1234")
                .build();

        PhoneOTP phoneCredentials = PhoneOTP.newBuilder()
                .setPhoneNumber("098-81-81-08")
                .setCode(1234)
                .build();

        // Last provided mode is going to be taken.
        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(emailCredentials)
                .setPhoneMode(phoneCredentials)
                .build();

        login(credentials);

        credentials = Credentials.newBuilder()
                .setPhoneMode(phoneCredentials)
                .setEmailMode(emailCredentials)
                .build();

        login(credentials);

        login(Credentials.getDefaultInstance());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void login(Credentials credentials) {
        if (credentials.hasEmailMode()) {
            credentials.getEmailMode().getEmail();
            credentials.getEmailMode().getPassword();
            System.out.println("Email mode: " + credentials);
        }
        if (credentials.hasPhoneMode()) {
            credentials.getPhoneMode().getPhoneNumber();
            credentials.getPhoneMode().getCode();
            System.out.println("Phone mode: " + credentials);
        }

        // Another nice way...
        switch (credentials.getModeCase()) {
            case EMAILMODE -> System.out.println("Switch -> Email mode");
            case PHONEMODE -> System.out.println("Switch -> Phone mode");
            case MODE_NOT_SET -> System.out.println("Switch -> Not set");
        }
    }
}
