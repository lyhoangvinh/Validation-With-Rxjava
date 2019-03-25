package lyhoangvinh.validationwithrxjava.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView tvStatus;
    EditText edtName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btLogin);
        tvStatus = findViewById(R.id.tvStatus);

        ValidationEditTextUtil.combineLatest(new ValidationEditTextUtil.Consumer2() {
            @Override
            public Boolean isValidForm(String... t) throws Exception {
                return isValidFormMain(t[0], t[1]);
            }

            @Override
            public void updateView(boolean valid) {
                updateButton(valid);
            }
        }, edtName, edtPassword);
    }

    public void updateButton(boolean valid) {
        if (valid)
            btnLogin.setEnabled(true);
    }

    public boolean isValidFormMain(String name, String password) {
        boolean validName = !TextUtils.isEmpty(name);

        if (!validName) {
            edtName.setError("Please enter valid name");
        }

        boolean validPass = !TextUtils.isEmpty(password);
        if (!validPass) {
            edtPassword.setError("Please enter valid password");
        }
        return validName && validPass;
    }


//        Observable<String> nameObservable = RxTextView.textChanges(edtName).skip(1).map(new Function<CharSequence, String>() {
//            @Override
//            public String apply(CharSequence charSequence) throws Exception {
//                return charSequence.toString();
//            }
//        });
//        Observable<String> passwordObservable = RxTextView.textChanges(edtPassword).skip(1).map(new Function<CharSequence, String>() {
//            @Override
//            public String apply(CharSequence charSequence) throws Exception {
//                return charSequence.toString();
//            }
//        });
//
//        Observable<Boolean> observable = Observable.combineLatest(nameObservable, passwordObservable, new BiFunction<String, String, Boolean>() {
//            @Override
//            public Boolean apply(String s, String s2) throws Exception {
//                return isValidForm(s, s2);
//            }
//        });
//
//        observable.subscribe(new DisposableObserver<Boolean>() {
//            @Override
//            public void onNext(Boolean aBoolean) {
//                updateButton(aBoolean);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

}
