package com.example.todo_list.activity;


import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import com.example.todo_list.R;



public class AlarmActivity extends BaseActivity {

    private static AlarmActivity inst;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.timeAndData)
    TextView timeAndData;
    @BindView(R.id.closeButton)
    Button closeButton;
    MediaPlayer mediaPlayer;

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notification);
        mediaPlayer.start();

        if(getIntent().getExtras() != null) {
            title.setText(getIntent().getStringExtra("TITLE"));
            description.setText(getIntent().getStringExtra("DESC"));
            timeAndData.setText(getIntent().getStringExtra("DATE") + ", " + getIntent().getStringExtra("TIME"));
        }

        Glide.with(getApplicationContext()).load(R.drawable.alert).into(imageView);
        closeButton.setOnClickListener(view -> finish());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
