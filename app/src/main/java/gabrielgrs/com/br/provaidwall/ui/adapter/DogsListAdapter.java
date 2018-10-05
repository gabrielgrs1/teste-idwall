package gabrielgrs.com.br.provaidwall.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gabrielgrs.com.br.provaidwall.R;

/**
 * Created by gabrielgrs
 * Date: 02/10/18
 * Time: 11:53 PM
 * Project: ProvaIDwall
 */
public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.ViewHolder> {
    private final List<String> mDogImageLinkList;
    private final Context mContext;

    public DogsListAdapter(Context context, List<String> mDogImageLinkList) {
        this.mContext = context;
        this.mDogImageLinkList = mDogImageLinkList;
    }

    @Override
    public DogsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(mContext).inflate(R.layout.dogs_list_item, parent, false);
        return new ViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDogImage(mDogImageLinkList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDogImageLinkList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mDogImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mDogImageView = itemView.findViewById(R.id.list_dogs_item_imageview);
            mDogImageView.setOnClickListener(this);
        }

        void setDogImage(String imageUrl) {
            Picasso.get()
                    .load(imageUrl)
                    .resize(180, 180)
                    .centerCrop()
                    .into(mDogImageView);
        }


        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

            builder.setCancelable(true);
            builder.setView(R.layout.dogs_zoom);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            ImageView mDialogListItemImageView = alertDialog.findViewById(R.id.dialog_list_dogs_item_imageview);


            Picasso.get()
                    .load(mDogImageLinkList.get(getAdapterPosition()))
                    .resize(400,400)
                    .centerCrop()
                    .noFade()
                    .into(mDialogListItemImageView);

        }
    }

}
