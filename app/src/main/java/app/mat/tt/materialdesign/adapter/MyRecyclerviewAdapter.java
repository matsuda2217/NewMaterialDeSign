package app.mat.tt.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.mat.tt.materialdesign.Infomation;
import app.mat.tt.materialdesign.R;

/**
 * Created by TT on 6/23/2016.
 */
public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Infomation>  data = Collections.emptyList();

    public MyRecyclerviewAdapter(Context context, List<Infomation>data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = inflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Infomation curr = data.get(position);
        holder.icon.setImageResource(curr.id);
        holder.title.setText(curr.title);
        Log.e("Data Size",curr.id + curr.title );
    }

    @Override
    public int getItemCount() {

        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title =(TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
