package com.example.graduation.yallamana.presenation.login.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.graduation.yallamana.R;
import butterknife.BindView;


public class verificationFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

        private OnFragmentInteractionListener mListener;

        public verificationFragment() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           getActivity().overridePendingTransition(R.anim.myanimation, R.anim.myanimation);


            // Sets the Toolbar to act as the ActionBar for this Activity window.
            // Make sure the toolbar exists in the activity and is not null



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.green));
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));


            return inflater.inflate(R.layout.fragment_varification, container, false);
        }

        // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }


        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);


        }


}

