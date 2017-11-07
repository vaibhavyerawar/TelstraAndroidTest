package com.TelstraAndroidTest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.TelstraAndroidTest.R;
import com.TelstraAndroidTest.model.Fact;
import com.TelstraAndroidTest.model.FactsCollection;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;

/**
 * Adapter class for fact listview.
 */

public class FactsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Fact> mFactsList;
    private int listItemCount = 0;
    private ImageLoader mImageLoader;

    public FactsListAdapter(Context context){
        mContext = context;
        mImageLoader = getImageLoader();
    }

    public void setListDataSource(FactsCollection factsCollection){
        mFactsList = factsCollection.getFactsList();
        listItemCount = mFactsList.size();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItemCount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
           viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                                                        R.layout.fact_list_item_layout, null);
            viewHolder.txtViewFactTitle = (TextView) convertView.findViewById(
                                                                           R.id.txtViewFactTitle);
            viewHolder.txtViewFactDescription = (TextView) convertView.findViewById(
                                                                     R.id.txtViewFactDescription);
            viewHolder.imgViewFactIcon = (ImageView) convertView.findViewById(
                                                                            R.id.imgViewFactIcon);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        Fact fact = mFactsList.get(position);

        viewHolder.txtViewFactTitle.setText(fact.getTitle());
        viewHolder.txtViewFactDescription.setText(fact.getDescription());
        mImageLoader.displayImage(fact.getImageURL(),viewHolder.imgViewFactIcon);

        return convertView;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = ImageLoader.getInstance();
            mImageLoader.init(getImageLoaderConfiguration());
        }
        return mImageLoader;
    }

    protected ImageLoaderConfiguration getImageLoaderConfiguration() {
        return new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(getDisplayImageOptions())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
    }

    protected DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .showImageForEmptyUri(R.drawable.ic_fact)
                .showImageOnFail(R.drawable.ic_fact)
                .showImageOnLoading(R.drawable.ic_fact)
                .build();
    }

    class ViewHolder{

        TextView  txtViewFactTitle;
        TextView  txtViewFactDescription;
        ImageView imgViewFactIcon;
    }
}