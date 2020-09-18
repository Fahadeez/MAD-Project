package pk.edu.iqra.onlinemart24_7

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.electronic_category_data.*
import kotlinx.android.synthetic.main.electronic_category_data.view.*
import pk.edu.iqra.onlinemart24_7.model.Orders
import pk.edu.iqra.onlinemart24_7.model.Product

class electronic_category_data : AppCompatActivity() {

    private var database = Firebase.database
    private var auth = Firebase.auth
    private lateinit var sharedPreferences: SharedPreferences;
    var orderList: MutableList<Orders> = mutableListOf()
    private lateinit var mProductDatabase: DatabaseReference
    private lateinit var context: Context
    private lateinit var activity: Activity
    private lateinit var vAdapter: RecyclerView
    var orderNo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.electronic_category)

        sharedPreferences = getSharedPreferences(Utils.AUTH_FILE, Context.MODE_PRIVATE)

//        to change the action bar title
        val action_bar =supportActionBar
        action_bar!!.title = "Electronics accessories"

        mProductDatabase = FirebaseDatabase.getInstance().reference
            .child("Electronics_Data")


        val user = auth.currentUser
        activity = this
        context = this
        vAdapter = findViewById(R.id.rvElectronics)


        val layoutManager = LinearLayoutManager(this)
        vAdapter.layoutManager = layoutManager

        val options: FirebaseRecyclerOptions<Product> = FirebaseRecyclerOptions.Builder<Product>()
            .setQuery(mProductDatabase, Product::class.java)
            .build()

        val adapter = object : FirebaseRecyclerAdapter<Product, VehiclesViewHolder>(options) {
            override fun onCreateViewHolder(
                parent: ViewGroup, viewType: Int): VehiclesViewHolder { // Create a new instance of the ViewHolder, in this case we are using a custom
                val view: View = LayoutInflater.from(this@electronic_category_data)
                    .inflate(R.layout.electronic_category_data, parent, false)
                return VehiclesViewHolder(view) }

            override fun onBindViewHolder(
                holder: VehiclesViewHolder, position: Int, model: Product
            ) {
//                holder.Vmodel.setOnClickListener {
//                    Intent(context, Activity_vehicleDetails::class.java).apply {
//                        putExtra(Utils.VENGINE,model.engine)
//                        putExtra(Utils.VMAKE,model.make)
//                        putExtra(Utils.VTTYPE,model.ttype)
//                        putExtra(Utils.VMODEL,model.model)
//                        putExtra(Utils.VPRICE,model.price)
//                        putExtra(Utils.IMGURL,model.ImageURL)
//                        activity.startActivity(this)
//                    }
//                }

                val refId:String = getRef(position).key.toString()

                mProductDatabase.child(refId).addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.e("Vehicle Reference","VRef: $mProductDatabase")
                        holder.setIsRecyclable(false)

                        holder.title.setText(model.Title)
                        holder.discount.setText(model.Discount)
                        holder.price.setText("Rs "+model.Price)
                        holder.quantity.setText("Quantity available "+model.Quantity)
                        Glide.with(activity as electronic_category_data)
                            .load(model.Image)
                            .into(holder.image)
                    }

                })
                holder.itemView.btn_add.setOnClickListener {
                    orderNo++
                    val order = Orders(

                        orderNo,model.Image, model.Title, model.Discount,
                        model.Price, model.Quantity
                    )

                    orderList.add(order)

                    saveOrder(orderList)
                    showToast("Order saved, order no: $orderNo")

                }
            }

        }

        vAdapter.adapter = adapter
        adapter.startListening()

    }

    class VehiclesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val price = itemView.price
        val discount = itemView.discount
        val image = itemView.image
        val quantity = itemView.quantity

    }

    private fun saveOrder(orderList:MutableList<Orders>){
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(orderList)//converting list to Json
        editor.putString(Utils.ORDER_SET,json)

        editor.apply()

    }

    override fun onBackPressed() {
        Intent(this,HomepageActivity::class.java).apply {

            startActivity(this)
            finish()
        }


    }

    }