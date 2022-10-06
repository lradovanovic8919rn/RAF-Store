package rs.raf.jul.lazar_radovanovic_rn8919.presentation.fragments

import android.content.Context
import android.content.Context.MODE_APPEND
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities.LoginActivity
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel


class ProfileFragment : Fragment(){

    private val storeViewModel: StoreContract.ViewModel by sharedViewModel<StoreViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(
            R.layout.fragment_profile,
            container, false)

        val p = activity?.getSharedPreferences("P1", Context.MODE_PRIVATE)
        val e = p?.edit()
        var username = view.findViewById<TextView>(R.id.Username)
        var email = view.findViewById<TextView>(R.id.emailfp)
        var logout = view.findViewById<Button>(R.id.logoutfp)
        var fn = view.findViewById<TextView>(R.id.fn)
        var ln = view.findViewById<TextView>(R.id.ln)
        var gender = view.findViewById<TextView>(R.id.gender)
        var img = view.findViewById<ImageView>(R.id.imageView3)



        val un= p?.getString("username","")
        println(un)
        val lns= p?.getString("ln","")
        println(lns+"111111111111111111111111111111111111122222222222222222222222222222222222")

        val fns= p?.getString("fn","")
        val imgs= p?.getString("img","")
        val gs= p?.getString("gender","")
        val ems= p?.getString("email","")




        fn.setText(fns)

        ln.setText(lns)

        gender.setText(gs)

        username.setText(un)

        email.setText(ems)

        Picasso.with(context).load(imgs).into(img)

        view.findViewById<View>(R.id.logoutfp).setOnClickListener { v: View? ->
            e!!.putBoolean("logged", true)
            e!!.commit()
            val intent = Intent(activity, LoginActivity::class.java)
            makeCalls()
            startActivity(intent)
            activity?.finish()

        }
        return view
    }

    private fun makeCalls() = with(storeViewModel){
        userLogout()
    }
}