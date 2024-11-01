package lat.pam.utsproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var foodName: String
    private lateinit var foodDescription: String
    private var foodImageResourceId: Int = 0

    private lateinit var servings: String
    private lateinit var orderingName: String
    private lateinit var additionalNotes: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        foodName = intent.getStringExtra("FOOD_NAME") ?: "Unknown Food"
        foodDescription = intent.getStringExtra("FOOD_DESCRIPTION") ?: "No Description"
        foodImageResourceId = intent.getIntExtra("FOOD_IMAGE", 0)

        servings = intent.getStringExtra("SERVINGS") ?: "0"
        orderingName = intent.getStringExtra("ORDERING_NAME") ?: "Unknown"
        additionalNotes = intent.getStringExtra("ADDITIONAL_NOTES") ?: "None"

        // Log to verify that foodName is received correctly
        Log.d("ConfirmationActivity", "TextView Updated - Food Name: ${findViewById<TextView>(R.id.tvFoodName).text}")

        findViewById<TextView>(R.id.tvFoodName).text = foodName
        findViewById<TextView>(R.id.tvFoodDescription).text = foodDescription
        findViewById<TextView>(R.id.tvServings).text = "Number of Servings: $servings"
        findViewById<TextView>(R.id.tvOrderingName).text = "Ordering Name: $orderingName"
        findViewById<TextView>(R.id.tvNotes).text = "Additional Notes: $additionalNotes"

        findViewById<Button>(R.id.backtoMenu).setOnClickListener {

            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}