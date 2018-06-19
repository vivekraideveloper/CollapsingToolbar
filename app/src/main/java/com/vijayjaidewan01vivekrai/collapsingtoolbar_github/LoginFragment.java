package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    CardView card;
    EditText username, password;
    Button login;
    CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.login_fragment,container,false);z

        login = view.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = view.findViewById(R.id.login_username);
                password = view.findViewById(R.id.login_password);
                card = view.findViewById(R.id.login_card);
                coordinatorLayout = view.findViewById(R.id.card_coordinator);

                String name = username.getText().toString();
                Log.i("name", name);
                String pass = password.getText().toString();
                Log.i("pass", pass);
                //check the parameters to login
                if(!name.isEmpty() && !pass.isEmpty())
                {
                    coordinatorLayout.animate().translationYBy(-2000f).setDuration(300).alphaBy(1f);
                    //startActivity(intent);
                }

            }
        });
        return view;
    }
}
