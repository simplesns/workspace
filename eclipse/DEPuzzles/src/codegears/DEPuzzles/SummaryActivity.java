package codegears.DEPuzzles;

import freehand.neandroid.GameActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity implements OnClickListener {

	public static final String EXTRA_GAME = "game";
	public static final String EXTRA_TITLE1 = "title1";
	public static final String EXTRA_TEXT1 = "text1";
	public static final String EXTRA_TITLE2 = "title2";
	public static final String EXTRA_TEXT2 = "text2";
	public static final String EXTRA_TITLE3 = "title3";
	public static final String EXTRA_TEXT3 = "text3";
	public static final String EXTRA_TITLE4 = "title4";
	public static final String EXTRA_TEXT4 = "text4";

	private Button back;
	private TextView game;
	private TextView title1;
	private TextView text1;
	private TextView title2;
	private TextView text2;
	private TextView title3;
	private TextView text3;
	private TextView title4;
	private TextView text4;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		GameActivity.setFullscreen(this);
		setContentView(R.layout.summary);
		Intent i = getIntent();
		game = (TextView) findViewById(R.id.SummaryGame);
		game.setText(i.getStringExtra(EXTRA_GAME));
		title1 = (TextView) findViewById(R.id.SummaryTitle1);
		title1.setText(i.getStringExtra(EXTRA_TITLE1));
		text1 = (TextView) findViewById(R.id.SummaryText1);
		text1.setText(i.getStringExtra(EXTRA_TEXT1));
		title2 = (TextView) findViewById(R.id.SummaryTitle2);
		title2.setText(i.getStringExtra(EXTRA_TITLE2));
		text2 = (TextView) findViewById(R.id.SummaryText2);
		text2.setText(i.getStringExtra(EXTRA_TEXT2));
		title3 = (TextView) findViewById(R.id.SummaryTitle3);
		title3.setText(i.getStringExtra(EXTRA_TITLE3));
		text3 = (TextView) findViewById(R.id.SummaryText3);
		text3.setText(i.getStringExtra(EXTRA_TEXT3));
		title4 = (TextView) findViewById(R.id.SummaryTitle4);
		title4.setText(i.getStringExtra(EXTRA_TITLE4));
		text4 = (TextView) findViewById(R.id.SummaryText4);
		text4.setText(i.getStringExtra(EXTRA_TEXT4));
		back = (Button) findViewById(R.id.SummaryBack);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (back.equals(view)) {
			finish();
		}
	}
}
