package com.example.read.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.read.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

public class User_Fragment extends Fragment {

    Button btnGopY;
    Button btnDanhDau, btnLichSu, btnDangXuat;
    Switch swBandem;
    CallbackManager callbackManager;
    TextView textViewName;
    LoginButton loginButton;
    ProfilePictureView profilePictureView;
    String name;

    public static Boolean checkLogin;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frament_user, container, false);


        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        profilePictureView = (ProfilePictureView) view.findViewById(R.id.friendProfilePicture);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        swBandem = (Switch) view.findViewById(R.id.bandem);
        btnLichSu = (Button) view.findViewById(R.id.lichsu);
        btnDanhDau = (Button) view.findViewById(R.id.tindaluu);
        textViewName = (TextView) view.findViewById(R.id.nameFacebook);
        btnDangXuat = (Button) view.findViewById(R.id.btnDangxuat);
        btnGopY = (Button) view.findViewById(R.id.btngopy);
        btnDangXuat.setVisibility(View.INVISIBLE);
        textViewName.setVisibility(View.INVISIBLE);
        loginButton.setReadPermissions("public_profile");
        loginButton.setFragment(this);
        setLogin_Button();

        setLogout_Button();


        String checkname = sharedPreferences.getString("fullname", "fail");
        String checkid = sharedPreferences.getString("id", "fail");
        checkLogin = sharedPreferences.getBoolean("checklogin", false);
        if (!checkid.equals("fail")) {

            loginButton.setVisibility(View.INVISIBLE);
            textViewName.setVisibility(View.VISIBLE);
            btnDangXuat.setVisibility(View.VISIBLE);
            textViewName.setText(checkname);
            profilePictureView.setProfileId(checkid);

        }


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            swBandem.setChecked(true);
        }
        swBandem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = new Intent(getActivity().getApplicationContext(), getContext().getClass());
                    startActivity(intent);
                    getActivity().finish();

                    //getActivity().getSupportFragmentManager().beginTransaction().commit();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Intent intent = new Intent(getActivity().getApplicationContext(), getContext().getClass());
                    startActivity(intent);
                    getActivity().finish();

                }

            }
        });


        btnLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LichSuActivity.class);
                startActivity(intent);
            }
        });
        btnDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhDauActivity.class);
                startActivity(intent);
            }
        });
        btnGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "boyhsky@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[24H RSS] Góp ý của người dùng");
                getContext().startActivity(Intent.createChooser(emailIntent, null));
            }
        });


        return view;
    }

    private void setLogout_Button() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btnDangXuat.setVisibility(View.INVISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                textViewName.setVisibility(View.INVISIBLE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                profilePictureView.setProfileId(null);
                editor.remove("fullname");
                editor.remove("id");
                editor.apply();
                checkLogin = false;
            }
        });
    }


    private void setLogin_Button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginButton.setVisibility(View.INVISIBLE);
                textViewName.setVisibility(View.VISIBLE);
                btnDangXuat.setVisibility(View.VISIBLE);
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.toString());
                try {
                    name = object.getString("name");
                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    textViewName.setText(name);
                    checkLogin = true;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fullname", name);
                    editor.putString("id", profilePictureView.getProfileId().toString());
                    editor.putBoolean("checklogin", checkLogin);
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
