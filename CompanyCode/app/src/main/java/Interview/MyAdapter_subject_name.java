package Interview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.os.IResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.q_a.companycode.Listitem_Name;
import com.q_a.companycode.R;

import java.util.Base64;
import java.util.List;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class MyAdapter_subject_name extends ArrayAdapter<Listitem_subject_name> {
    private List<Listitem_subject_name> arraylistItems;
    private Context context;

    public MyAdapter_subject_name(List<Listitem_subject_name> listItems,@NonNull Context context) {
        super(context, R.layout.listitem_subject_name,listItems);
        this.arraylistItems = listItems;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_subject_name,null,true);

        TextView tvq=view.findViewById(R.id.textSubject);
        TextView tvn=view.findViewById(R.id.textNumber);



        tvq.setText(arraylistItems.get(position).getSubject());
        tvn.setText(arraylistItems.get(position).getNumber()+" Question");
        return view;
    }

}