package com.example.graduation.yallamana.presenation.login;


/**
 * Created by Mais

 */

public class LoginPresenter {
    private LoginView loginView;
   //private DataRepository dataRepository;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
     // dataRepository = DataRepository.getInstance();
    }

    public void submitLogin(String phone) {
        if (validateCredentials(phone)) {
            loginView.loginSuccess();
//            dataRepository.getToken(phone)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(aBoolean -> {
//                        if (aBoolean) {
//                            loginView.loginFailed();
//                        } else {
//                            loginView.loginSuccess();
//                        }
//                    }, throwable -> loginView.loginFailed());
//        } else {
//            loginView.validateFail();
        }
    }


    private boolean validateCredentials(String phone) {
        if ((phone.length() != 10 ) ) {
            return false;
        } else {
            return true;
        }
    }
}
