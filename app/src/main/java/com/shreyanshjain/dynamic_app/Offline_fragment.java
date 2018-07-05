package com.shreyanshjain.dynamic_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.R;

public class Offline_fragment extends Fragment {


    public Offline_fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline_fragment, container, false);
    }

}
