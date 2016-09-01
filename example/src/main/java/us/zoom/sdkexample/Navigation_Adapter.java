package us.zoom.sdkexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by developer on 22-Jan-16.
 */
public class Navigation_Adapter extends RecyclerView.Adapter<Navigation_Adapter.MyViewHolder> {

    private String[] data;

    private View view;

    private ClickListner clickListner;


    public Navigation_Adapter(Context con, String[] data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText("" + data[position]);


    }

    public void setClickListner(ClickListner clickListner) {
        this.clickListner = clickListner;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListner != null) {
                        clickListner.itemClicked(v, getPosition());

                    }
                }
            });
            textView = (TextView) itemView.findViewById(R.id.textview);
            imageView = (ImageView) itemView.findViewById(R.id.image);

        }


    }


    public interface ClickListner {
        public void itemClicked(View view, int position);

        public void itemLongClick(View view, int position);
    }
}
