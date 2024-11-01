package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var foodName: String
    private lateinit var foodDescription: String
    private var foodImageResourceId: Int = 0

    private lateinit var servings: String
    private lateinit var orderingName: String
    private lateinit var additionalNotes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        foodName = intent.getStringExtra("FOOD_NAME") ?: "Unknown Food"
        foodDescription = intent.getStringExtra("FOOD_DESCRIPTION") ?: "No Description"
        foodImageResourceId = intent.getIntExtra("FOOD_IMAGE", 0)

        findViewById<Button>(R.id.btnOrder).setOnClickListener {
            // Retrieve additional data from EditText fields
            servings = findViewById<EditText>(R.id.etServings).text.toString()
            orderingName = findViewById<EditText>(R.id.etName).text.toString()
            additionalNotes = findViewById<EditText>(R.id.etNotes).text.toString()

            // Log to check foodName before sending to ConfirmationActivity
            Log.d("OrderActivity", "Food Name: $foodName")

            // Navigate to ConfirmationActivity
            val confirmationIntent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("FOOD_NAME", foodName)
                putExtra("FOOD_DESCRIPTION", foodDescription)
                putExtra("FOOD_IMAGE", foodImageResourceId)
                putExtra("SERVINGS", servings) // Pass the number of servings
                putExtra("ORDERING_NAME", orderingName) // Pass the ordering name
                putExtra("ADDITIONAL_NOTES", additionalNotes) // Pass the additional notes
            }
            startActivity(confirmationIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}