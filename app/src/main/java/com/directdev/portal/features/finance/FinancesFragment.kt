package com.directdev.portal.features.finance

import android.app.Fragment
import android.os.Bundle
import android.view.*
import com.directdev.portal.R
import com.directdev.portal.features.MainActivity
import com.directdev.portal.models.FinanceModel
import com.google.firebase.analytics.FirebaseAnalytics
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_finances.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.ctx
import kotlin.properties.Delegates

class FinancesFragment : Fragment(), AnkoLogger {
    private var realm: Realm by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_finances, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(ctx)
        val bundle = Bundle()
        bundle.putString("content", "finances")
        mFirebaseAnalytics.logEvent("content_opened", bundle)

        realm = Realm.getDefaultInstance()
        val data = realm.where(FinanceModel::class.java).findAll()
        val layout = androidx.recyclerview.widget.LinearLayoutManager(ctx)
        financeToolbar.title = "Billing"
        if (data.size > 0) {
            layout.stackFromEnd = true
            layout.reverseLayout = true
            financesRecycler.layoutManager = layout
            financesRecycler.adapter = FinancesRecyclerAdapter(realm, data, true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        (ctx as MainActivity).menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onStop() {
        super.onStop()
        realm.close()
    }
}