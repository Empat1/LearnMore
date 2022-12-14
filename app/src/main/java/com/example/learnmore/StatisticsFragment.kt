package com.example.learnmore

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Word
import com.example.learnmore.ui.readCard.MyWordsRecyclerViewAdapter
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.security.auth.callback.Callback

class StatisticsFragment : Fragment() {

    val FILE_NAME = "myDoc.docx"
    var rv: RecyclerView? = null;
    var tvSize: TextView? = null;
    var words: List<Word> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        //todo build
        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)
        val button = view.findViewById<Button>(R.id.show_word_learn)

        tvSize = view.findViewById(R.id.learn_count)

        rv = view.findViewById<RecyclerView>(R.id.rv_learn_word)
        rv!!.layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)

        val btWord = view.findViewById<Button>(R.id.btn_save_word)
        btWord.setOnClickListener(View.OnClickListener {
            var targetDoc = createWordDoc()
            addParagraph(targetDoc)
            saveOurDoc(targetDoc)
        })


        rv!!.visibility = View.GONE

        var flag = false;
        button.setOnClickListener(View.OnClickListener {
            flag = !flag
            if(flag) {
                button.text = "Скрыть\n выученные слова"
                rv!!.visibility = View.VISIBLE
            }else{
                button.text = "Показать\n выученные слова"
                rv!!.visibility = View.GONE
            }

        })

        getWordLearn()
        return view;
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1 && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                try {
////                    copyFile()
//                    println()
//                } catch (e: Exception) {
//                    e.printStackTrace()
////                    backupFail()
//                }
//
////            } else backupFail()
//            }
//        }
//    }

    private fun createWordDoc(): XWPFDocument {
        val ourWordDoc = XWPFDocument()
        return ourWordDoc
    }

    private fun addParagraph(targetDoc:XWPFDocument){
        //creating a paragraph in our document and setting its alignment
        val paragraph1 = targetDoc.createParagraph()
        paragraph1.alignment = ParagraphAlignment.LEFT

        //creating a run for adding text
        val sentenceRun1 = paragraph1.createRun()

        //format the text
        sentenceRun1.isBold = true
        sentenceRun1.fontSize = 12
        sentenceRun1.fontFamily = "Comic Sans MS"
        sentenceRun1.setText("Выучено слов " + words.size)
        //add a sentence break
        sentenceRun1.addBreak()

        var out = "";
        for(word in words){
            out += word.word_text + " " + word.word_translate + " \n"
        }

        //add another run
        val sentenceRun2 = paragraph1.createRun()
        sentenceRun2.fontSize = 12
        sentenceRun2.fontFamily = "Comic Sans MS"
        sentenceRun2.setText(out)
        sentenceRun2.addBreak()

    }

    private fun getWordLearn(){


        val retrofitService = RetrofitService()
        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
        productApi.getWordLearn( Model.user!!.user_id!!).enqueue(object : Callback, retrofit2.Callback<List<Word>>{
            override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
                if(response.body() != null) {
                    words = response.body()!!;
                    rv!!.adapter = MyWordsRecyclerViewAdapter(response.body()!!, findNavController())
                    tvSize!!.text = "Выучено слов " + response.body()!!.size

                }
            }

            override fun onFailure(call: Call<List<Word>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun saveOurDoc(targetDoc: XWPFDocument){

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {
            // разрешение есть

            val ourAppFileDirectory = Environment.getExternalStorageDirectory()

            val wordFile = File(ourAppFileDirectory, FILE_NAME)
            try {
                val fileOut = FileOutputStream(wordFile)
                targetDoc.write(fileOut)
                fileOut.close()
                openIntentWordFole("$ourAppFileDirectory/$FILE_NAME")


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }


        } else {
            // разрешения нет

            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        }

    }

    fun openIntentWordFole(name: String){
        val intent = Intent(Intent.ACTION_VIEW)
        val file = File(name)
        val extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString())
        val mimetype = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        if (extension.equals("", ignoreCase = true) || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*")
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype)
        }
        // custom message for the intent
        // custom message for the intent
        startActivity(Intent.createChooser(intent, "Choose an Application:"))
    }
}