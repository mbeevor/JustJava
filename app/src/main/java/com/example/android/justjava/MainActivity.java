/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *  */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        int finalPrice = calculatePrice();
        TextView customerName = (TextView) findViewById(R.id.customer_name);
        String isCustomersName = customerName.getText().toString();
        String priceMessage = createOrderSummary(isCustomersName, finalPrice, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
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
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
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
