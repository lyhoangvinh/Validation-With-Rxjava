# Validation With Rxjava

How to use RxJava in Android
In this tutorial, I am going to illustrate how you can use RxJava in android applications and build apps with much less code.

I am going to build a login application which would take a username and a password and match it with already initialized values to check whether to allow the login or not.

## Steps

1. Create a basic app with two input fields and one submit button

2. Add dependencies for RxJava and RxAndroid

3. Create observables for those input fields

4. Apply validations

5. Create an observer to listen to those observables.

# Libraries
RxAndroid, RxJava2.

Add two dependencies for RxJava and RxAndroid

```gradle
  implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'

  implementation 'io.reactivex.rxjava2:rxjava:2.2.0'

  implementation 'com.jakewharton.rxbinding2:rxbinding-appcompat-v7:2.1.1'

```

# Links
I learned a lot from this article https://www.vokal.io/blog/reactive-forms-with-rxandroid
