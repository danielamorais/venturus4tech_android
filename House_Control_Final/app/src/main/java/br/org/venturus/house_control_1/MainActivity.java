package br.org.venturus.house_control_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch switchView = (Switch) findViewById(R.id.lamp_switch);
        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onSwitchClicked(isChecked);
            }
        });
    }

    public void onSwitchClicked(boolean isChecked) {

        TextView switchText = (TextView) findViewById(R.id.lamp_switch_text);
        ImageView cardBackground = (ImageView) findViewById(R.id.card_bg);
        ImageView lightBulb = (ImageView) findViewById(R.id.lamp);

        if (isChecked) {
            switchText.setText(R.string.switch_text_on);

            cardBackground.setImageResource(R.drawable.bgon);

            lightBulb.setImageResource(R.drawable.icon_luz_on);
        } else {
            switchText.setText(R.string.switch_text_off);

            cardBackground.setImageResource(R.drawable.bgoff);

            lightBulb.setImageResource(R.drawable.icon_luz_off);
        }
    }
}
