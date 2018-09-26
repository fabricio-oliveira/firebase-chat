package com.tio.bicio.chat.feature.message

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tio.bicio.chat.App
import com.tio.bicio.chat.R
import com.tio.bicio.chat.R.id
import com.tio.bicio.chat.domain.Message
import kotlinx.android.synthetic.main.activity_message_list.*
import org.koin.android.ext.android.inject


class MessageListActivity : AppCompatActivity(), MessageListView {

    override val presenter: MessageListPresenter by inject()

    private var messageAdapter: MessageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        presenter.view = this

        configRecycleView()
        configActions()
        presenter.subscribeReceiveMessege()
    }

    override fun showItem(message: Message) {
        runOnUiThread {
            messageAdapter?.addItem(message)
            messageAdapter?.notifyItemInserted()

            recycleViewMessages?.postDelayed({
                recycleViewMessages.smoothScrollToPosition(messageAdapter?.itemCount ?: 0)
            }, 200)

            messageAdapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_chat, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showError(error: String?) {
        runOnUiThread {
            Toast.makeText(App.context, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun showSuccess(message: String?) {
        runOnUiThread {
            Toast.makeText(App.context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun configActions() {
        buttonChatBoxSend.setOnClickListener {
            presenter.sendMessage(edTxChatbox.text.toString())
            { showSuccess("Messagem send with success $it") }
            edTxChatbox.text.clear()
        }
    }

    private fun configRecycleView() {
        messageAdapter = MessageListAdapter(presenter.currentUser)

        recycleViewMessages.apply {
            adapter = messageAdapter
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }

    }
}
