/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.example.android.justjava;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity {
    int num = 0;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increase(View view) {
        if (num < 10)
            num++;
        setQuantity(num);
    }

    public void decrease(View view) {
        if (num > 0)
            num--;
        setQuantity(num);
    }

    public void orderSummary(View view) {
        TextView Name = (TextView) findViewById(R.id.name);
        String name = Name.getText().toString();
        message = "Name: " + name;
        int price = num * 10;

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox3);
        if (checkBox.isChecked()) {
            message += "\n Extra Sugar ";
            price += num * 5;
        }

        checkBox = (CheckBox) findViewById(R.id.checkBox5);
        if (checkBox.isChecked()) {
            message += "\n Extra Milk";
            price += num * 7;
        }

        message += "\nTotal: $" + price;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message + "\nThank You!");
    }

    private void setQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    public void order(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Coffee");
        intent.putExtra(Intent.EXTRA_TEXT, "No. of Coffee: " + num + "\n" + message);
        if (intent.resolveActivity(getPackageManager()) != null && message != "")
            startActivity(Intent.createChooser(intent, "send email:"));
        else if (message == "") {
            TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
            priceTextView.setText("Please update \nOrder Summary First");
        } else {
            TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
            priceTextView.setText("Order Can't be Processed\nSORRY!");
        }
    }
}