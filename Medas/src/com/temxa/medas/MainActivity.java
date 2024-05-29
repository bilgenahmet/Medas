package com.temxa.medas;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ImageView				a;
	private ProgressDialog	pd;
	EditText				tesisatno, sorguresmi;
	TextView				sonuc, deneme;
	Button					sorguu;
	SorguYap				Sorgula	= new SorguYap();
	String					kontrol, tesisat;
	Intent					intent;
	Bundle					bundle;
	static FaturaBilgiDizi	gonder	= new FaturaBilgiDizi();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intent = new Intent(this, Sonuc.class);
		bundle = new Bundle();

		a = (ImageView) findViewById(R.id.kontrol_resim);

		sonuc = (TextView) findViewById(R.id.textView3);
		deneme = (TextView) findViewById(R.id.textView1);
		sonuc.setMovementMethod(new ScrollingMovementMethod());
		sorguu = (Button) findViewById(R.id.sorgu);

		sorguu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				tesisatno = (EditText) findViewById(R.id.tesisat_no);
				tesisat = tesisatno.getText().toString();

				sorguresmi = (EditText) findViewById(R.id.kontrol_yaz);
				kontrol = sorguresmi.getText().toString();
				new SorguTask().execute(tesisat, kontrol);

			}
		});

		new ImageTask().execute("http://online.meramedas.com.tr/cgi-bin/gf_check.cgi");

	}

	private class ImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showdialog();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				return Sorgula.Resim(params[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {
				a.setImageBitmap(result);
			}
			pd.dismiss();
		}

	}

	private class SorguTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			showdialog();
		}

		@Override
		protected String doInBackground(String... params) {
			return Sorgula.sorgulamayap(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {

				Document doc = Jsoup.parse(result);
				Elements tables = doc.select(".datatable");
				if (tables.size() > 1) {
					Elements elems = tables.get(1).select("tr");
					if (!elems.isEmpty()) {
						ArrayList<Fatura> faturalar = new ArrayList<Fatura>(elems.size());

						for (Element e : elems) {
							Fatura f = new Fatura(e.child(3).ownText(), e.child(4).ownText(), e.child(7).ownText());
							faturalar.add(f);

							// FaturaBilgi fatur = new FaturaBilgi();
							// fatur.ilktarih = e.children().get(3).ownText();
							// fatur.sontarih = e.children().get(4).ownText();
							// fatur.para = e.children().get(7).ownText();
							// gonder.gonbilgi.add(fatur);
						}

						intent.putExtra("veri", faturalar);
						startActivity(intent);
					}

				}

				// bundle.putSerializable("veri", gonder);
				// intent.putExtras(bundle);
				// startActivity(intent);
			}
			pd.dismiss();
		}
	}

	void showdialog() {
		pd = ProgressDialog.show(this, "Sorgulama", "Tesisat sorgusu yapılıyor. Lütfen bekleyiniz...", true, true);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	}

}
