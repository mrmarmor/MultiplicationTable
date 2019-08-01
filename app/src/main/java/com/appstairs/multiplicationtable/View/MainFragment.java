package com.appstairs.multiplicationtable.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.appstairs.multiplicationtable.Base;
import com.appstairs.multiplicationtable.Controller.MultiplicationRecyclerController;
import com.appstairs.multiplicationtable.R;

import static com.appstairs.multiplicationtable.Base.BINARY;
import static com.appstairs.multiplicationtable.Base.DECIMAL;
import static com.appstairs.multiplicationtable.Base.HEX;

public class MainFragment extends Fragment implements Spinner.OnItemSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private int numOfColumns = 10;
    private @Base int matrixBase = DECIMAL;

    private String mParam1;//params from activity
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() { }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initRecycler();
        initSizeSpinner();
        initBaseSpinner();

        return view;
    }

    private void initRecycler() {
        RecyclerView multiplicationTable_RV = view.findViewById(R.id.multiplication_RV);
        StaggeredGridLayoutManager sglManager = new StaggeredGridLayoutManager(numOfColumns, StaggeredGridLayoutManager.VERTICAL);
        multiplicationTable_RV.setLayoutManager(sglManager);
        MultiplicationRecyclerController controller = new MultiplicationRecyclerController(
                getActivity(), numOfColumns, matrixBase);
        multiplicationTable_RV.setAdapter(controller);
    }

    private void initSizeSpinner() {
        Spinner sizeSpinner = view.findViewById(R.id.multiplication_matrixSize_spinner);
        sizeSpinner.setSelection(7);//the 7th element is "10"
        sizeSpinner.setOnItemSelectedListener(this);
    }

    private void initBaseSpinner() {
        Spinner baseSpinner = view.findViewById(R.id.multiplication_matrixBase_spinner);
        baseSpinner.setOnItemSelectedListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.multiplication_matrixSize_spinner:
                changeMatrixSize(position + 3);//matrix size minimum is 3. so i need to increment by 3
                break;

            case R.id.multiplication_matrixBase_spinner:
                changeMatrixBase(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    private void changeMatrixSize(int position) {
        numOfColumns = position;
        initRecycler();
    }

    private void changeMatrixBase(int position) {
        matrixBase = (position == 0)? DECIMAL : (position == 1)? BINARY : HEX;
        initRecycler();
    }

    //a listener to pass data from fragment to activity
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
