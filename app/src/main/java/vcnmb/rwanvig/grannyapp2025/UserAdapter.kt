package vcnmb.rwanvig.grannyapp2025

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors
import kotlin.math.log


class UserAdapter :ListAdapter<User, UserAdapter.UserAdapter>(UserViewHolder())
{
    class UserAdapter(view: View) :RecyclerView.ViewHolder(view)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter {
        val inflater = LayoutInflater.from(parent.context)
        return UserAdapter(inflater.inflate(
            R.layout.userlayout,parent,false
        ))
    }

    override fun onBindViewHolder(holder: UserAdapter, position: Int) {
        val user = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.txtNameUser).text = user.Name
        holder.itemView.findViewById<TextView>(R.id.txtPasswordUser).text = user.Password
        //declaring executor to parse the url
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        //setting up the image
        var image :Bitmap?=null
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imPP)
        executor.execute {
            val imageUrl = user.imageURL
            try {
                val `in` = java.net.URL(imageUrl).openStream()
                image=BitmapFactory.decodeStream(`in`)
                Log.d("AddednewUser","Image in text "+imageUrl.toString())
                handler.post{
                    Log.d("AddednewUser","Image added ")
                    imageView.setImageBitmap(image)
                }
            }
            catch (e:java.lang.Exception)
            {
                Log.d("AddednewUser","Error happened..."+e.toString())
            }


        }
    }


}
class UserViewHolder :DiffUtil.ItemCallback<User>()
{
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.Name==newItem.Name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}