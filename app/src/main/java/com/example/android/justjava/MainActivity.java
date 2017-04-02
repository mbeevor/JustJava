/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *  */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamOption = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamOption.isChecked();
        CheckBox chocolateOption = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateOption.isChecked();
        int finalPrice = calculatePrice(hasChocolate, hasWhippedCream);
        EditText customerName = (EditText) findViewById(R.id.customer_name);
        String isCustomersName = customerName.getText().toString();
        String priceMessage = createOrderSummary(isCustomersName, finalPrice, hasWhippedCream, hasChocolate);
//        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param addChocolate is if the user added chocolate to their order
     * @param addWhippedCream is if the user added Whipped Cream to their order
     * @return total price
     */
    private int calculatePrice(boolean addChocolate, boolean addWhippedCream) {
        int coffeePrice = 5;

        if (addWhippedCream) {
            coffeePrice = coffeePrice + 1;
        }

        if (addChocolate) {
            coffeePrice = coffeePrice + 2;
        }
        return quantity * coffeePrice;
    }

    /**
     * Displays the order summary
     *
     * @return summary of order
     * @param CustomersName is customer's name
     * @param addWhippedCream  specifies if whipped cream was added
     * @param addChocolate specifies if chocolate was added
     */
    private String createOrderSummary(String CustomersName, int price, boolean addWhippedCream, boolean addChocolate) {
        String summary = "Name: " + CustomersName + "\nAdd whipped cream? " + addWhippedCream +
                "\nAdd chocolate? " + addChocolate + "\nQuantity: " + quantity + "\nTotal: £" + price + "\nThank You!";
        return summary;
    }


    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {

            Toast.makeText(this, "You can't have that many coffees!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {

            Toast.makeText(this, "You cannot have fewer than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        orderSummaryTextView.setText(message);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int coffee) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + coffee);
    }

}
