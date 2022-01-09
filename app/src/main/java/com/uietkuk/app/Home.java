package com.uietkuk.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uietkuk.app.constant.Config;
import com.uietkuk.app.model.Announcement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    private ArrayList<Announcement> announcementArrayList=new ArrayList<Announcement>();
    private ListAdapter listadapter;
    RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        /*
        TextView textView;
        textView=view.findViewById(R.id.textView5);

        String currDateAndTime=java.text.DateFormat.getDateTimeInstance().format(new Date());
        textView.setText(currDateAndTime);

         */
        //initialze the recycler view

//        Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
//        startActivity(intent);


        recyclerView=view.findViewById(R.id.recylerViewHome);

        fetchingJson();
        return view;
    }

    private void fetchingJson() {
        String url= Config.API_URL+"/announcement";
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ObjectMapper objectMapper=new ObjectMapper();
                    int len=response.length();
                    System.out.println(len);
                    for(int i=0;i<len;i++){

                        JSONObject jsonObject=response.getJSONObject(i);
                        Announcement ann2=objectMapper.readValue(jsonObject.toString(), Announcement.class);



//                        String id=jsonObject.getString("id");
//                        String name=jsonObject.getString("name");
//                        String url=jsonObject.getString("url");
//                        String date=jsonObject.getString("date");
//                        System.out.println("enter ");
//
//                        Announcement ann=new Announcement(Long.parseLong(id), name, url, LocalDate.parse(date));
//                        System.out.println(ann);
                        announcementArrayList.add(ann2);


                    }
                    buildRecyclerView();
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Eroor ", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);


    }

    private void buildRecyclerView() {
        listadapter=new ListAdapter(getActivity().getApplicationContext(), announcementArrayList);
        recyclerView.setAdapter(listadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }


}
    