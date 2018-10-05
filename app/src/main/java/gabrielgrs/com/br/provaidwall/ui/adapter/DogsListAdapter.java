package gabrielgrs.com.br.provaidwall.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Callback;
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
        View createdView = LayoutInflater.from(mContext).inflate(R.layout.dogs_list_item, parent, false);
        return new ViewHolder(createdView);
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
        private static final int LIST_DOG_IMAGE_SIZE = 155;
        private static final int ZOOM_DOG_IMAGE_SIZE = 400;
        private ImageView mListImageView;
        private ShimmerFrameLayout mListImageShimmer;
        private ImageView mDialogImageView;
        private ShimmerFrameLayout mDialogShimmer;

        ViewHolder(View itemView) {
            super(itemView);
            configureView(itemView);
        }

        private void configureView(View itemView) {
            configureListViews(itemView);
            configureOnClickListener();
        }

        private void configureOnClickListener() {
            mListImageView.setOnClickListener(this);
        }

        private void configureListViews(View itemView) {
            mListImageView = itemView.findViewById(R.id.dogs_list_item_imageview);
            mListImageShimmer = itemView.findViewById(R.id.dogs_list_shimmer_view_container);
        }

        void setDogImage(String imageUrl) {
            Picasso.get()
                    .load(imageUrl)
                    .resize(LIST_DOG_IMAGE_SIZE, LIST_DOG_IMAGE_SIZE)
                    .centerCrop()
                    .noFade()
                    .into(mListImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mListImageShimmer.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            mListImageShimmer.setVisibility(View.GONE);
                        }
                    });
        }

        @Override
        public void onClick(View view) {
            AlertDialog alertDialog = configureAlertDialog();
            configureZoomDogViews(alertDialog);

            Picasso.get()
                    .load(mDogImageLinkList.get(getAdapterPosition()))
                    .resize(ZOOM_DOG_IMAGE_SIZE, ZOOM_DOG_IMAGE_SIZE)
                    .centerCrop()
                    .noFade()
                    .into(mDialogImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mDialogShimmer.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            mDialogShimmer.setVisibility(View.GONE);
                        }
                    });

        }

        // Não foi possível utilizar o Butterknife aqui pois o aplicativo crashava e eu nao consegui reservar um tempo para analisar o porque do crash
        private void configureZoomDogViews(AlertDialog alertDialog) {
            mDialogImageView = alertDialog.findViewById(R.id.dialog_list_dogs_item_imageview);
            mDialogShimmer = alertDialog.findViewById(R.id.dialog_zoom_shimmer_view_container);
        }

        @NonNull
        private AlertDialog configureAlertDialog() {
            AlertDialog.Builder builder = configureAlertBuilder();
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return alertDialog;
        }

        @NonNull
        private AlertDialog.Builder configureAlertBuilder() {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setCancelable(true);
            builder.setView(R.layout.dogs_zoom);
            return builder;
        }
    }

}
