package com.example.miskaatask;

import android.content.Context;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.caverock.androidsvg.SVG;

import com.pixplicity.sharp.Sharp;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomAdapter extends ArrayAdapter{


    List<Details> details;
    Context mContext;

    public CustomAdapter(@NonNull Context context,List<Details> det) {
        super(context, R.layout.card_view,det);
        this.details=det;
        this.mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_view,parent,false);
        TextView cName=view.findViewById(R.id.cName);
        ImageView imageView=view.findViewById(R.id.flag);
        TextView capital=view.findViewById(R.id.capital);
        TextView region=view.findViewById(R.id.subregion);
        TextView population=view.findViewById(R.id.population);
        TextView borders=view.findViewById(R.id.borders);

        cName.setText(details.get(position).getCountryName());

        //Picasso.with(mContext).load(details.get(position).getFlagUrl()).into(imageView);

        //Glide.with(mContext).load(Uri.parse(details.get(position).getFlagUrl())).into(imageView);
        Utils.fetchSvg(mContext,details.get(position).getFlagUrl(),imageView);



        capital.setText(details.get(position).getCapital());
        region.setText(details.get(position).getRegion());
        population.setText(details.get(position).getPopulation()+"");

        List<String> borderList=details.get(position).getBorders();
        String temp="";

        for(String border:borderList){
            temp=temp+border+"\n";
        }

        borders.setText(temp);

        return view;
    }
}

 class Utils {

    private static OkHttpClient httpClient;

    // this method is used to fetch svg and load it into target imageview.
    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 1024 * 1024))
                    .build();
        }

        // here we are making HTTP call to fetch data from URL.
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // we are adding a default image if we get any error.
                target.setImageResource(R.drawable.ic_launcher_background);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // sharp is a library which will load stream which we generated
                // from url in our target imageview.
                InputStream stream = response.body().byteStream();
                Sharp.loadInputStream(stream).into(target);
                stream.close();
            }
        });
    }
}
