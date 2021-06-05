package com.example.miskaatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Api NamesApi;

    private List<Details> details;

    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lView);

        details=new ArrayList<>();


        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://restcountries.eu/rest/v2/").addConverterFactory(GsonConverterFactory.create()).build();

        NamesApi = retrofit.create(Api.class);

        dispalay(this);

      //  customAdapter=new CustomAdapter(this,R.layout.card_view,details);
      //  listView.setAdapter(customAdapter);
    }

    void dispalay(Context context){
        Call<List<Names>> call=NamesApi.getNames();
        call.enqueue(new Callback<List<Names>>() {
            @Override
            public void onResponse(Call<List<Names>> call, Response<List<Names>> response) {
                if(!response.isSuccessful()){

                    return;
                }
                List<Names> names=response.body();
                for(Names name: names){
                    Details content=new Details(name.getName(),name.getCapital(),name.getFlag(),name.getRegion(),name.getSubregion(),name.getPopulation(),name.getBorders());
                    Log.i("Tag",name.getName());
                    Log.i("Tag",name.getCapital());
                    Log.i("Tag",name.getFlag());

                    List<String> t=name.getBorders();

                    for(String temp:t){
                        Log.i("Tag",temp);
                    }

                    Log.i("pop",name.getPopulation()+"");


                    details.add(content);
                }
                customAdapter=new CustomAdapter(context,details);
                listView.setAdapter(customAdapter);
                //TODO: set content in listview
            }

            @Override
            public void onFailure(Call<List<Names>> call, Throwable t) {
                //TODO: Error
            }
        });
    }
}