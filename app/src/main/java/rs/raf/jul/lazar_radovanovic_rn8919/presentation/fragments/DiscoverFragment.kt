package rs.raf.jul.lazar_radovanovic_rn8919.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.contracts.StoreContract
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.adapter.ProductAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.recycler.diff.ProductDiffItemCallback
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.state.ProductsState
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel
import timber.log.Timber


class DiscoverFragment : Fragment(){
    private val storeViewModel: StoreContract.ViewModel by sharedViewModel<StoreViewModel>()
    private lateinit var s:Spinner
    private lateinit var rv :RecyclerView
    private lateinit var productAdapter:ProductAdapter
    private lateinit var input:EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(
            R.layout.fragment_discover,
            container, false
        )
        s = view.findViewById(R.id.spinner)
        var rv = view.findViewById<RecyclerView>(R.id.recyclerView)
        input = view.findViewById(R.id.inputEt)

        initObservers()

        makeCallsCat()

        s?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=20){
                    input.setText("")
                    makeCallsCatName(s.selectedItem.toString())
                }
                if(position==20){
                    if(input.text.toString().equals("")){
                        makeCallsAll()
                    }
                }
            }

        }

        input.doAfterTextChanged {
            if(it.toString().equals("")){
                if(s.selectedItem.toString().equals("-")){
                    makeCallsAll()
                }
            }else{
                s.setSelection(20)
                val new=it.toString()
                makeCallsProd(new)
            }
        }



        if (rv != null) {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
            rv.layoutManager = layoutManager
        }

        productAdapter=ProductAdapter(ProductDiffItemCallback()){
            Timber.e("${it.title}")

        }
        if (rv != null) {
            rv.adapter=productAdapter
        }


        return view
    }
    private fun makeCallsCat() = with(storeViewModel){
        getCategories()
    }
    private fun makeCallsAll() = with(storeViewModel){
        getAll()
    }
    private fun makeCallsCatName(s:String) = with(storeViewModel){
        getCategoryByName(s)
    }
    private fun makeCallsProd(s:String) = with(storeViewModel){
        //getProductKw(s)
        fetchQuery(s)
        getResults()
    }
    private fun initObservers() = with(storeViewModel) {
        categories.observe(viewLifecycleOwner) {

            val a: ArrayList<String> = ArrayList<String>()

            val siz = storeViewModel.categories.value?.size
            for (i in 0..(siz!!-1)){
                storeViewModel.categories.value?.let { a.add(it.get(i)) }
            }

            a.add("-")



            val adapter = this@DiscoverFragment.context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, a
                )
            }
            s?.adapter =adapter

            s?.setSelection(20)

        }
        allProducts.observe(viewLifecycleOwner){
            productAdapter.submitList(it)
        }
        activeCategory.observe(viewLifecycleOwner){
            productAdapter.submitList(it)
        }
        psLiveData.observe(viewLifecycleOwner){
            renderState(it)
        }

        //storeViewModel.fetchAll()
    }
    private fun renderState(state: ProductsState) {
        when (state) {
            is ProductsState.Success -> {
                productAdapter.submitList(state.movies)

            }
            is ProductsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ProductsState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is ProductsState.Loading -> {
            }
        }
    }
}