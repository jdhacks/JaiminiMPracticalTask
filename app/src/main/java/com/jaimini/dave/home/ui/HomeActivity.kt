package com.jaimini.dave.home.ui

/*
import com.bumptech.glide.load.engine.GlideException
*/
/*
import com.bumptech.glide.request.RequestOptions
*/

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jaimini.dave.R
import com.jaimini.dave.databinding.ActivityHomeBinding
import com.jaimini.dave.databinding.UserItemLayBinding
import com.jaimini.dave.loginnewuser.ui.LoginNew
import com.jaimini.dave.retrofit.pojo.ResponseData


class HomeActivity : AppCompatActivity() {
    lateinit var USERLIST : ArrayList<ResponseData>

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userViewModel: HomeViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    lateinit var retroFitView: View
    lateinit var listViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        userViewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        setContentView(binding.root)
        binding.btnlogout.setOnClickListener(View.OnClickListener {
            val sharedPref = getSharedPreferences(
                "userPrefs",
                Context.MODE_PRIVATE
            )
            val editor = sharedPref.edit()
            editor.putBoolean("signin", false)
            editor.apply()
            this.finish()
            startActivity(Intent(this@HomeActivity,LoginNew::class.java))
        })
        listViewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)

            listViewModel.getProjectRetroListObservable().observe(this, object :
                Observer<ArrayList<ResponseData>> {
                override fun onChanged(t: ArrayList<ResponseData>) {
                    Log.e("JSONDATA", "listsize" + t);
USERLIST = ArrayList()
                      USERLIST.addAll(t)
                    if (USERLIST.size > 0) {
                       // txtloading.setText("Some Error Occured")
                       binding.recusers.visibility= View.VISIBLE
                        //txtloading.visibility = View.GONE
                        adapter.setList(USERLIST)
                    } else {
                        //txtloading.setText("Some Error Occured")
                        //txtloading.visibility = View.VISIBLE
                    }
                }

            })


        initRecyclerView()
}

private fun initRecyclerView() {
    binding.recusers.layoutManager = LinearLayoutManager(this)
    adapter = MyRecyclerViewAdapter({ selectedItem: ResponseData -> listItemClicked(selectedItem) },applicationContext)
    binding.recusers.adapter = adapter
    displayUsersList()
}

private fun displayUsersList() {
    userViewModel.getProjectRetroListObservable().observe(this, Observer {


        adapter.setList(it)
        adapter.notifyDataSetChanged()
    })
}

private fun listItemClicked(user: ResponseData) {
     val newFragment = ShowDetailsDialog.newInstance(user.url)

    newFragment.show(supportFragmentManager, "UserDetails")

   // Toast.makeText(this, "selected item is ${user.title}", Toast.LENGTH_LONG).show()
}

    private fun showDetailsDialog(url :String) {
      }
class MyRecyclerViewAdapter(private val clickListener: (ResponseData) -> Unit, val mctx : Context) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    private val usersList = ArrayList<ResponseData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyRecyclerViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
       //UserItemLayBinding.inflate(layoutInflater)
        /*DataBindingUtil.inflate(
            layoutInflater,
            R.layout.user_item_lay,
            parent,
            false

        )*/
        return MyRecyclerViewAdapter.MyViewHolder(
            UserItemLayBinding.inflate(layoutInflater),parent.context
        )
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

    fun setList(users: List<ResponseData>) {
        usersList.clear()
        usersList.addAll(users)

    }


    class MyViewHolder(val binding: UserItemLayBinding ,val ctx : Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ResponseData, clickListener: (ResponseData) -> Unit) {
            binding.txttitlename.text = user.title
            /*Glide.with(ctx)
                .load(user.thumbnailUrl)
                .asBitmap()
                .listener(object : RequestListener<String, Bitmap> {

                    override fun onException(
                        e: Exception?,
                        model: String?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false;
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: String?,
                        target: Target<Bitmap>?,
                        isFromMemoryCache: Boolean,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false;
                    }

                })
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .thumbnail(0.5f)
                .into(binding.imglayuser);
           */
            val cR: ContentResolver = ctx.getContentResolver()
            val mime = MimeTypeMap.getSingleton()
            val type = mime.getExtensionFromMimeType(cR.getType(Uri.parse(user.thumbnailUrl)))
            val murl=user.thumbnailUrl+"."+type;
            Log.e("TAG","url"+murl)
            Glide.with(ctx)
                .load(user.thumbnailUrl).thumbnail(0.5f)
                  .listener(object :  RequestListener<Drawable>
                  {
                      override fun onLoadFailed(
                          e: GlideException?,
                          model: Any?,
                          target: Target<Drawable>?,
                          isFirstResource: Boolean
                      ): Boolean {

                          binding.imglayuser.setImageResource(R.color.black)
                          return false
                      }

                      override fun onResourceReady(
                          resource: Drawable?,
                          model: Any?,
                          target: Target<Drawable>?,
                          dataSource: DataSource?,
                          isFirstResource: Boolean
                      ): Boolean {
                          binding.imglayuser.setImageDrawable(resource)
                          return true
                      }
                  }).into(binding.imglayuser)
            //.into(binding.imglayuser)
            binding.uselistlay.setOnClickListener {
                clickListener(user)
            }
        }

    }
}


}