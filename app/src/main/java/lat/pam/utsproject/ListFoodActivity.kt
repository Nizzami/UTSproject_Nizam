package lat.pam.utsproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino),
            Food("Nasi goreng", "Nasi goreng sedap khas Indonesia", R.drawable.nasigoreng),
            Food("Cheese Cake", "Cheese Cake yang lezat dibuat secara langsung", R.drawable.cheesecake),
            Food("Cireng", "Cireng yang enak dan murah", R.drawable.cireng),
            Food("Donut", "Donut bulat mantap pisan", R.drawable.donut),
            Food("Sparkling Tea", "Minuman yang segar", R.drawable.sparkling_tea),
            Food("Mie Goreng", "Mie Goreng sedap kesukaan semua orang", R.drawable.mie_goreng),
            Food("Kopi Hitam", "Minuman segar menghilangkan kantuk", R.drawable.kopi_hitam)
        )

        adapter = FoodAdapter(foodList) { selectedFood ->
            // Start OrderActivity and pass the selected food details
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra("FOOD_NAME", selectedFood.name)
                putExtra("FOOD_DESCRIPTION", selectedFood.description)
                putExtra("FOOD_IMAGE", selectedFood.imageResourceId) // Optional: Pass image resource ID
            }
            startActivity(intent)
        }



        recyclerView.adapter = adapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}