package com.temxa.medas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Sonuc extends Activity {
	private ArrayList<Fatura>	faturalar;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sonuc_xml);

		ListView lv = (ListView) findViewById(R.id.listView1);

		faturalar = (ArrayList<Fatura>) getIntent().getSerializableExtra("veri");

		lv.setAdapter(new ListeOzel(this, R.layout.listview_ozel, faturalar));
	}
}
