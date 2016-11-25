package com.rsc.rschackathon.activities;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rsc.rschackathon.R;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    private static final String TAG = MainActivity.class.getName();

    private static final String CLIENT_ID = "981151316904-0g546k4u8lnncv8bhne98tj791ilrbsb.apps.googleusercontent.com";

    private CallbackManager callbackManager;

    private GoogleApiClient mGoogleApiClient;

    public String token;

    @BindView(R.id.facebook_button)
    LoginButton facebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        facebookButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebookButton.registerCallback(callbackManager, mCallback);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestProfile().requestIdToken(CLIENT_ID).build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @OnClick(R.id.login_button)
    public void loginClicked() {
        startActivity(LoginActivity.createIntent(this));
    }

    @OnClick(R.id.google_button)
    public void googleClicked() {
        googleLogin();
    }

    private void googleLogin() {
        String[] accountTypes = new String[]{"com.google", "com.google.android.legacyimap"};
        Intent signInIntent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {

            AccessToken accessToken = loginResult.getAccessToken();
            Log.i("TAG", accessToken.getToken());
/*
            if (accessToken.getToken() == null) {
                Log.i(TAG, "Neuspješna prijava");
            } else {
                Call<ApiResponse> call = networkService.getAPI().getApiKey(accessToken.getToken());
                call.enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(final Call<ApiResponse> call, final Response<ApiResponse> response) {

                        if (response.body() == null) {
                            showMessage("Greška na serveru");
                        } else {
                            Log.i("TAG", "on response " + response.body().getData().getApiKey());
                            String API_KEY = response.body().getData().getApiKey();
                            writeTokenToSharedPreferences(response.body().getData().getApiKey());

                            Call<User> userCall = networkService.getAPI().getUser(API_KEY);
                            userCall.enqueue(new Callback<User>() {

                                @Override
                                public void onResponse(final Call<User> call, final Response<User> response) {
                                    Log.i("TAG", "user status " + response.body().getData().getTypeName());
                                    /// promjena usera
                                    writeUserTypeToSharedPreferences(response.body().getData().getTypeName());
                                    if (response.body().getData() != null) {
                                        if (response.body().getData().getTypeName().equals("Premium")) {
                                            goToWelcomeScreen.goToDeckList();
                                        } else {

                                            goToWelcomeScreen.goToWelcome();
                                        }
                                    } else {
                                        showMessage("Greška na serveru");
                                    }
                                }

                                @Override
                                public void onFailure(final Call<User> call, final Throwable t) {
                                    showMessage("Neuspješno povezivanje sa serverom");
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(final Call<ApiResponse> call, final Throwable t) {
                        Log.i("TAG", "on failure " + t.getMessage());
                        showMessage("Neuspješno povezivanje sa serverom");
                    }
                });
            }*/
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.i(TAG, "Neuspješna prijava");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        String mEmail;

        if (requestCode == RC_SIGN_IN) {
            if (data != null) {
                if (data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME) == null) {
                    Log.i("TAG", "Neuspješna priajva");
                } else {
                    mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Log.i("TAG", mEmail);
                    new GetGoogleToken(MainActivity.this).execute(mEmail);
                }
            }
        }

    }

    private static final class GetGoogleToken extends AsyncTask<String, Void, Boolean> {

        public final MainActivity mainActivity;

        public GetGoogleToken(final MainActivity loginFragment) {
            this.mainActivity = loginFragment;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                mainActivity.token = GoogleAuthUtil
                        .getToken(mainActivity, params[0], "oauth2:" + Scopes.PROFILE + " " + Scopes.EMAIL + " " + Scopes.PLUS_LOGIN);
                Log.i("TAG", " google token " + mainActivity.token);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
                return false;
            } catch (UserRecoverableAuthException e) {
                mainActivity.startActivityForResult(e.getIntent(), mainActivity.RC_SIGN_IN);
                return false;
            } catch (GoogleAuthException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {/*
                if (mainActivity.token != null) {
                    Call<ApiResponse> call = mainActivity.networkService.getAPI().getApiKeyGoogle(mainActivity.token);
                    call.enqueue(new Callback<ApiResponse>() {

                        @Override
                        public void onResponse(final Call<ApiResponse> call, final Response<ApiResponse> response) {
                            Log.i("TAG", "google apykey " + response.body().getData().getApiKey());
                            if (response.body() != null) {
                                mainActivity.writeTokenToSharedPreferences(response.body().getData().getApiKey());
                                Call<User> userCall = mainActivity.networkService.getAPI().getUser(response.body().getData().getApiKey());
                                userCall.enqueue(new Callback<User>() {

                                    @Override
                                    public void onResponse(final Call<User> call, final Response<User> response) {
                                        if (response.body() != null) {
                                            mainActivity.writeUserTypeToSharedPreferences(response.body().getData().getTypeName());
                                            if (response.body().getData().getTypeName().equals("Premium")) {

                                                mainActivity.goToWelcomeScreen.goToDeckList();
                                            } else {
                                                mainActivity.goToWelcomeScreen.goToWelcome();
                                            }
                                        } else {
                                            mainActivity.showMessage("Neuspješno povezivanje s serverom");
                                        }
                                    }

                                    @Override
                                    public void onFailure(final Call<User> call, final Throwable t) {
                                        mainActivity.showMessage("Neuspješno povezivanje s serverom");
                                    }
                                });
                            } else {
                                mainActivity.showMessage("Neuspješno povezivanje s serverom");
                            }
                        }

                        @Override
                        public void onFailure(final Call<ApiResponse> call, final Throwable t) {
                            mainActivity.showMessage("Neuspješno povezivanje s serverom");
                        }
                    });
                } else {
                    mainActivity.showMessage("Neuspješna priajva");
                }*/
            }
        }
    }
}