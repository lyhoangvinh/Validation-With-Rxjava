package lyhoangvinh.validationwithrxjava.com;

import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.observers.DisposableObserver;

public class ValidationEditTextUtil {

    public static void combineLatest(final Consumer2 consumer, EditText... editText) {
        List<Observable<String>> observables = createObservableTextChanges(editText);

        Observable<Boolean> observable = combineLatest(observables, new Consumer1() {
            @Override
            public Boolean isValidForm(String... t) throws Exception {
                return consumer.isValidForm(t);
            }
        });

        observable.subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                consumer.updateView(aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public static List<Observable<String>> createObservableTextChanges(EditText... editText) {
        List<Observable<String>> observables = new ArrayList<>();

        for (int i = 0; i < editText.length; i++) {
            observables.add(RxTextView.textChanges(editText[i]).skip(1).map(new Function<CharSequence, String>() {
                @Override
                public String apply(CharSequence charSequence) throws Exception {
                    return charSequence.toString();
                }
            }));
        }
        return observables;
    }

    public static Observable<Boolean> combineLatest(List<Observable<String>> observablesEditText, final Consumer1 consumer2) {
        switch (observablesEditText.size()) {
            case 2:
                return Observable.combineLatest(observablesEditText.get(0), observablesEditText.get(1), new BiFunction<String, String, Boolean>() {
                    @Override
                    public Boolean apply(String t1, String t2) throws Exception {
                        return consumer2.isValidForm(t1, t2);
                    }
                });

            case 3:
                return Observable.combineLatest(observablesEditText.get(0),
                        observablesEditText.get(1),
                        observablesEditText.get(2)
                        , new Function3<String, String, String, Boolean>() {
                            @Override
                            public Boolean apply(String s, String s2, String s3) throws Exception {
                                return consumer2.isValidForm(s, s2, s3);
                            }
                        });
            case 4:
                return Observable.combineLatest(observablesEditText.get(0),
                        observablesEditText.get(1),
                        observablesEditText.get(2),
                        observablesEditText.get(3), new Function4<String, String, String, String, Boolean>() {
                            @Override
                            public Boolean apply(String s, String s2, String s3, String s4) throws Exception {
                                return consumer2.isValidForm(s, s2, s3, s4);
                            }
                        });
            case 5:
                return Observable.combineLatest(observablesEditText.get(0),
                        observablesEditText.get(1),
                        observablesEditText.get(2),
                        observablesEditText.get(3),
                        observablesEditText.get(4), new Function5<String, String, String, String, String, Boolean>() {
                            @Override
                            public Boolean apply(String s, String s2, String s3, String s4, String s5) throws Exception {
                                return consumer2.isValidForm(s, s2, s3, s4, s5);
                            }
                        });

            case 6:
                return Observable.combineLatest(observablesEditText.get(0),
                        observablesEditText.get(1),
                        observablesEditText.get(2),
                        observablesEditText.get(3),
                        observablesEditText.get(4),
                        observablesEditText.get(5),
                        new Function6<String, String, String, String, String, String, Boolean>() {
                            @Override
                            public Boolean apply(String s, String s2, String s3, String s4, String s5, String s6) throws Exception {
                                return consumer2.isValidForm(s, s2, s3, s4, s5, s6);
                            }
                        });
            case 7:
                return Observable.combineLatest(observablesEditText.get(0),
                        observablesEditText.get(1),
                        observablesEditText.get(2),
                        observablesEditText.get(3),
                        observablesEditText.get(4),
                        observablesEditText.get(5),
                        observablesEditText.get(6),
                        new Function7<String, String, String, String, String, String, String, Boolean>() {
                            @Override
                            public Boolean apply(String s, String s2, String s3, String s4, String s5, String s6, String s7) throws Exception {
                                return consumer2.isValidForm(s, s2, s3, s4, s5, s6, s7);
                            }
                        });
        }
        return null;
    }

    public static void combineLatest
            (Observable<String> observables1, Observable<String> observables2,
             final Consumer consumer) {

        Observable<Boolean> observable = Observable.combineLatest(observables1, observables2, new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String t1, String t2) throws Exception {
                return consumer.isValidForm(t1, t2);
            }
        });

        observable.subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                consumer.updateView(aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public interface Consumer {
        Boolean isValidForm(String t1, String t2) throws Exception;

        void updateView(boolean valid);
    }

    public interface Consumer2 {
        Boolean isValidForm(String... t) throws Exception;

        void updateView(boolean valid);
    }

    public interface Consumer1 {
        Boolean isValidForm(String... t) throws Exception;
    }
}
