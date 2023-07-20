package com.example.tourguideapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "password";
    private static final String ARG_PARAM3 = "userId";

    // TODO: Rename and change types of parameters
    private String name;
    private String password;
    private String userId;


    TextView tPassword;
    TextView tId;
    TextView tName;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @param password Parameter 2.
     * @param id Parameter 3.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String name, String password, String id) {

        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("password", password);
        args.putString("userId", id);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString(name);
            userId = getArguments().getString(userId);
            password = getArguments().getString(password);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        tName = view.findViewById(R.id.tName);
        tId = view.findViewById(R.id.tId);
        tPassword = view.findViewById(R.id.tPassword);

        Bundle args = getArguments();
        if (args != null) {
            String value = args.getString("name"); // Obtiene el valor del Bundle
            tName.setText(value);

            String value1 = args.getString("userId"); // Obtiene el valor del Bundle
            tId.setText(value1);

            String value2 = args.getString("password"); // Obtiene el valor del Bundle
            tPassword.setText(value2);

            // Realiza alguna acción con los datos recibidos
        }

        // Resto del código del método onCreateView()

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //((TextView)view.findViewById(R.id.nameAns)).setText(name);
        //((TextView)view.findViewById(R.id.passwordAns)).setText(password);
    }
}
