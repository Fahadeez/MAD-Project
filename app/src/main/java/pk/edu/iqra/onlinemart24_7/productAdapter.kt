package pk.edu.iqra.onlinemart24_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.electronic_category_data.view.*
import pk.edu.iqra.onlinemart24_7.model.Product

//class extends RecyclerView Adapter
// manages and displays our data

class productAdapter(val productList: ArrayList<Product>):
    Adapter<productAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.ssd_image?.setImageBitmap(productList[position].image)
//        holder.ssd_title?.text = productList[position].title
//        holder.ssd_desc?.text = productList[position].description
//        holder.ssd_price?.text = productList[position].price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.electronic_category_data, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        val ssd_image = itemView.image
        val ssd_title = itemView.title
        val ssd_desc = itemView.discount
        val ssd_price = itemView.price
    }
}