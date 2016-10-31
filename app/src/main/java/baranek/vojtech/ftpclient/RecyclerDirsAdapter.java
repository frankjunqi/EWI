package baranek.vojtech.ftpclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.net.ftp.FTPFile;

import java.util.ArrayList;

/**
 * Created by Farmas on 17.10.2015.
 * <p/>
 * Custom adapter for RecylclerView for displaying files
 */
public class RecyclerDirsAdapter extends RecyclerView.Adapter<RecyclerDirsAdapter.CustomViewHolder> {

    private ArrayList<FTPFile> files = new ArrayList<>();
    private Context context;
    private FileDirClickListen fileClickListen;

    private int currentPostion = -1;// 默认选中项目

    public interface FileDirClickListen {
        void fileDirClick(String dirPath);
    }

    public RecyclerDirsAdapter(Context context) {
        this.context = context;
    }

    public void setFiles(FTPFile[] files) {
        this.files.clear();
        for (int i = 0; files != null && i < files.length; i++) {
            this.files.add(files[i]);
        }
        notifyDataSetChanged();
    }

    public void setOnDirClickListener(FileDirClickListen fileClickListen) {
        this.fileClickListen = fileClickListen;
    }

    @Override
    public RecyclerDirsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerDirsAdapter.CustomViewHolder holder, final int position) {
        final String filename = files.get(position).getName();
        holder.tv1.setText(String.valueOf(filename));

        if (position == currentPostion) {
            holder.tv1.setBackgroundResource(R.drawable.bg_left_tab_sel);
            holder.tv1.setTextColor(context.getResources().getColor(R.color.main_white));
        } else {
            holder.tv1.setBackgroundResource(R.drawable.bg_left_tab);
            holder.tv1.setTextColor(context.getResources().getColor(R.color.title_dark));
        }

        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPostion = position;
                if (fileClickListen != null) {
                    fileClickListen.fileDirClick(filename);
                }
                notifyDataSetChanged();
            }
        });
        holder.tv1.setTag(holder);
    }

    @Override
    public int getItemCount() {
        if (files == null) {
            return 0;
        }
        int i = files.size();
        return i;
    }

    /**
     * Custom view holder for row
     */

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv1;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.tv1 = (TextView) itemView.findViewById(R.id.title);
        }
    }

}

