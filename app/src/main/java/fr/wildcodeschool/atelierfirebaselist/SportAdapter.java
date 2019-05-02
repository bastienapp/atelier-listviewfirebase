package fr.wildcodeschool.atelierfirebaselist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SportAdapter extends ArrayAdapter<Sport> {

    public SportAdapter(Context context, List<Sport> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Sport sport = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sport, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(sport.getName());

        return convertView;
    }
}
