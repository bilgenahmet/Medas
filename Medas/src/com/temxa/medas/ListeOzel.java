package com.temxa.medas;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListeOzel extends ArrayAdapter<Fatura> {

	private int				resourceid;
	private LayoutInflater	inflater;

	public ListeOzel(Context context, int resource, List<Fatura> objects) {
		super(context, resource, objects);
		this.resourceid = resource;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			convertView = inflater.inflate(resourceid, parent, false);
			holder = new ViewHolder();

			holder.faturaTarih = (TextView) convertView.findViewById(com.temxa.medas.R.id.textView1);
			holder.sonTarih = (TextView) convertView.findViewById(com.temxa.medas.R.id.textView2);
			holder.tutar = (TextView) convertView.findViewById(com.temxa.medas.R.id.textView3);

			convertView.setTag(holder);
		} else holder = (ViewHolder) convertView.getTag();

		Fatura f = getItem(position);

		holder.faturaTarih.setText(f.getFaturaTarih());
		holder.sonTarih.setText(f.getSonTarih());
		holder.tutar.setText(f.getTutar());

		return convertView;
	}

	static class ViewHolder {
		TextView	faturaTarih, sonTarih, tutar;
	}

}
