package com.example.android.java;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BlankFragment extends Fragment {

    // Define an instance of the OnFragmentInteractionListener
    // interface, which will be assigned an Activity that
    // implements it.
    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_blank, container, false);

        // Get a reference to the button from the Fragment's XML layout
        Button button = fragmentView.findViewById(R.id.button);

        // Set a click listener on the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Notify the Activity (listener) of the
                // Fragment's interaction / button click
                // by calling it's callback method
                mListener.onFragmentInteraction();
            }
        });

        // Return the Fragment View object
        return fragmentView;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction();
//        }
//    }

    // onAttach() attaches the Fragment to the context, a.k.a the listening Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // If the Activity implements OnFragmentInteractionListener...
        if (context instanceof OnFragmentInteractionListener) {

            // Attach the Fragment to the Activity, so that interactions in this
            // Fragment can be communicated to the Activity
            // Save a reference to the parent Activity (mListener).
            // The Activity's type is now that of OnFragmentInteractionListener,
            // meaning it implements the method "onFragmentInteraction()"
            mListener = (OnFragmentInteractionListener) context;


        } else { // If the Activity does not implement OnFragmentInteractionListener...

            // Throw a RunTimeException
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Detach the listening Activity from the Fragment
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    // Implement this interface in the MainActivity class,
    // or whatever Activity/Fragment wants to listen to this Fragment
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
